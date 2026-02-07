```
drop policy if exists "Admins can read admin_users" on public.admin_users;

drop policy if exists "Admins can manage admin_users" on public.admin_users;

  

-- keep RLS enabled, but no policies = no access

alter table public.admin_users enable row level security;
```