```
/-- ============================================

-- Tutor Management: Core Schema + RLS (Fixed order)

-- ============================================

  

begin;

  

-- Extensions (safe to run even if already enabled)

create extension if not exists pgcrypto;

create extension if not exists "uuid-ossp";

  

-- ---------------------------------------

-- 1) updated_at trigger utility function

-- ---------------------------------------

create or replace function public.set_updated_at()

returns trigger

language plpgsql

as $$

begin

  new.updated_at = now();

  return new;

end;

$$;

  

-- ----------------------------

-- 2) Tables

-- ----------------------------

  

-- 2.1 Admin allowlist (create BEFORE is_admin())

create table if not exists public.admin_users (

  user_id uuid primary key references auth.users(id) on delete cascade,

  created_at timestamptz not null default now()

);

  

comment on table public.admin_users is 'Allowlist of users permitted to access admin operations.';

  

-- 2.2 Classes / Batches

create table if not exists public.classes (

  id uuid primary key default gen_random_uuid(),

  code text not null unique,

  name text not null,

  description text,

  active boolean not null default true,

  created_at timestamptz not null default now(),

  updated_at timestamptz not null default now()

);

  

-- 2.3 Students

create table if not exists public.students (

  id uuid primary key default gen_random_uuid(),

  class_id uuid not null references public.classes(id) on delete restrict,

  full_name text not null,

  guardian_name text,

  phone text,

  email text,

  notes text,

  active boolean not null default true,

  joined_at date,

  created_at timestamptz not null default now(),

  updated_at timestamptz not null default now()

);

  

-- Same phone/email shouldn't repeat inside the same class (if present)

create unique index if not exists students_class_phone_uniq

  on public.students(class_id, phone)

  where phone is not null and phone <> '';

  

create unique index if not exists students_class_email_uniq

  on public.students(class_id, email)

  where email is not null and email <> '';

  

-- 2.4 Attendance

create table if not exists public.attendance (

  id uuid primary key default gen_random_uuid(),

  class_id uuid not null references public.classes(id) on delete cascade,

  student_id uuid not null references public.students(id) on delete cascade,

  session_date date not null,

  status text not null default 'present'

    check (status in ('present','absent','late','excused')),

  note text,

  created_at timestamptz not null default now(),

  updated_at timestamptz not null default now(),

  constraint attendance_unique_per_student_per_date

    unique (student_id, session_date)

);

  

-- 2.5 Payments (monthly)

create table if not exists public.payments (

  id uuid primary key default gen_random_uuid(),

  class_id uuid not null references public.classes(id) on delete cascade,

  student_id uuid not null references public.students(id) on delete cascade,

  month date not null, -- store as first day of month (e.g., 2026-02-01)

  amount_cents integer not null check (amount_cents >= 0),

  currency text not null default 'LKR',

  paid_at timestamptz,

  method text check (method in ('cash','bank_transfer','card','other')),

  note text,

  created_at timestamptz not null default now(),

  updated_at timestamptz not null default now(),

  constraint payments_unique_per_student_per_month

    unique (student_id, month)

);

  

-- 2.6 Assignments (metadata; file is in Supabase Storage)

create table if not exists public.assignments (

  id uuid primary key default gen_random_uuid(),

  class_id uuid not null references public.classes(id) on delete cascade,

  title text not null,

  description text,

  due_date date,

  bucket text not null default 'assignments',

  object_path text not null,

  file_name text,

  file_size_bytes bigint check (file_size_bytes is null or file_size_bytes >= 0),

  mime_type text,

  published boolean not null default true,

  created_at timestamptz not null default now(),

  updated_at timestamptz not null default now()

);

  

-- ----------------------------

-- 3) Helper function: is_admin (AFTER admin_users exists)

-- ----------------------------

create or replace function public.is_admin()

returns boolean

language sql

stable

as $$

  select exists (

    select 1

    from public.admin_users au

    where au.user_id = auth.uid()

  );

$$;

  

-- ----------------------------

-- 4) Indexes for performance

-- ----------------------------

create index if not exists students_class_id_idx on public.students(class_id);

create index if not exists students_active_idx on public.students(active);

  

create index if not exists attendance_class_date_idx on public.attendance(class_id, session_date);

create index if not exists attendance_student_date_idx on public.attendance(student_id, session_date);

  

create index if not exists payments_class_month_idx on public.payments(class_id, month);

create index if not exists payments_student_month_idx on public.payments(student_id, month);

create index if not exists payments_paid_at_idx on public.payments(paid_at);

  

create index if not exists assignments_class_due_idx on public.assignments(class_id, due_date);

create index if not exists assignments_published_idx on public.assignments(published);

  

-- ----------------------------

-- 5) updated_at triggers

-- ----------------------------

do $$

begin

  if not exists (select 1 from pg_trigger where tgname = 'classes_set_updated_at') then

    create trigger classes_set_updated_at

    before update on public.classes

    for each row execute function public.set_updated_at();

  end if;

  

  if not exists (select 1 from pg_trigger where tgname = 'students_set_updated_at') then

    create trigger students_set_updated_at

    before update on public.students

    for each row execute function public.set_updated_at();

  end if;

  

  if not exists (select 1 from pg_trigger where tgname = 'attendance_set_updated_at') then

    create trigger attendance_set_updated_at

    before update on public.attendance

    for each row execute function public.set_updated_at();

  end if;

  

  if not exists (select 1 from pg_trigger where tgname = 'payments_set_updated_at') then

    create trigger payments_set_updated_at

    before update on public.payments

    for each row execute function public.set_updated_at();

  end if;

  

  if not exists (select 1 from pg_trigger where tgname = 'assignments_set_updated_at') then

    create trigger assignments_set_updated_at

    before update on public.assignments

    for each row execute function public.set_updated_at();

  end if;

end $$;

  

-- ----------------------------

-- 6) RLS + Policies

-- ----------------------------

alter table public.admin_users enable row level security;

alter table public.classes enable row level security;

alter table public.students enable row level security;

alter table public.attendance enable row level security;

alter table public.payments enable row level security;

alter table public.assignments enable row level security;

  

-- admin_users

drop policy if exists "Admins can read admin_users" on public.admin_users;

create policy "Admins can read admin_users"

on public.admin_users for select

to authenticated

using (public.is_admin());

  

drop policy if exists "Admins can manage admin_users" on public.admin_users;

create policy "Admins can manage admin_users"

on public.admin_users for all

to authenticated

using (public.is_admin())

with check (public.is_admin());

  

-- classes

drop policy if exists "Admins can manage classes" on public.classes;

create policy "Admins can manage classes"

on public.classes for all

to authenticated

using (public.is_admin())

with check (public.is_admin());

  

-- students

drop policy if exists "Admins can manage students" on public.students;

create policy "Admins can manage students"

on public.students for all

to authenticated

using (public.is_admin())

with check (public.is_admin());

  

-- attendance

drop policy if exists "Admins can manage attendance" on public.attendance;

create policy "Admins can manage attendance"

on public.attendance for all

to authenticated

using (public.is_admin())

with check (public.is_admin());

  

-- payments

drop policy if exists "Admins can manage payments" on public.payments;

create policy "Admins can manage payments"

on public.payments for all

to authenticated

using (public.is_admin())

with check (public.is_admin());

  

-- assignments (admin manage)

drop policy if exists "Admins can manage assignments" on public.assignments;

create policy "Admins can manage assignments"

on public.assignments for all

to authenticated

using (public.is_admin())

with check (public.is_admin());

  

-- OPTIONAL: public read-only access for published assignments (metadata only).

drop policy if exists "Public can read published assignments" on public.assignments;

create policy "Public can read published assignments"

on public.assignments for select

to anon, authenticated

using (published = true);

  

commit;
```