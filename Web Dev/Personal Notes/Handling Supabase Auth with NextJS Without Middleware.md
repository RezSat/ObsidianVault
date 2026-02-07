So I was just starting out to code an backend of a tutor system and want to experiment with new features from NextJS and it turns out from NextJS 16 or something they renamed `middleware.ts` to `proxy.ts`.  The documentation also mentioned the fact that it is actually a last resort and there are alternatives (which I didn't knew until I turn into chatgpt). So basically you don't actually need a middleware (or proxy) or at least not in my case. In my application there is just admin and just make the login page for admin to enter to admin dashboard and that's it.

Start off with NextJS project:

```
bun create next-app@latest my-app --yes
cd my-app
bun dev
```

Alright so now create a new supabase project ( save the database password somewhere )

We need these (inside .env):
```

NEXT_PUBLIC_SUPABASE_URL=fill-here
NEXT_PUBLIC_SUPABASE_PUBLISHABLE_DEFAULT_KEY=fill-here

```

Follow the documentation coming when clicked "connect" in the project overview section and select "App framework" and select "Next.js" using "App Router" with "supabase-js", once selected they show you some file to fill in.  In my case the structure looks like this:
![[Pasted image 20260207235909.png]]
in the utils folder according to the docs, I created these files:
![[Pasted image 20260207235943.png]]

![[Pasted image 20260208000000.png]]
Now then, we first need these libraries installed

`bun add @supabase/supabase-js`
`bun add @supabase/ssr`

Now with these done locally, I created some tables in the supabase with SQL editor with the code mentioned in here: [[Tutor Management Core Schema with RLS - Started (there's  issues)]]

Then I went to on to Authentication panel and created a new user using "add user" button, copied the user id created and use the code here in the new SQL Editor window: [[Tutor Management Insert Admin]] essentially making the newly created user an admin.

Then coming back to local folder, install shadcn in order to create the login form.

`bunx --bun shadcn@latest init`

I choose the `login-3` block (https://ui.shadcn.com/blocks/login) I tried using bunx and bun commands to actually add the login-3 but all of them gave me errors so for that installation I simply used `npx shadcn@latest add login-03` and it worked (There is still a missing piece of why bun commands failed)

so I modified the login code a bit as shown in here: [[Tutor Management Login Form Code]]

Then my Structure for the admin look like this, as I was not using the middleware/proxy I used `(proteced)` folder to keep the admin pages and use redirects if not authenticated. This is why I think I didn't want a middleware besides this is actually good, as our goal is simply **“protect `/admin` and redirect to `/admin/login` when not logged in”**, the _best_ approach (and the one that avoids running code for every request)
Create:
```
app/
  admin/
	layout.tsx
    login/
      actions.ts
      page.tsx
    (protected)/
      layout.tsx
      page.tsx
      ...other admin pages...

```
This keeps `/admin/login` public, while everything else under `(protected)` is locked.

The Login action code then looks like this : [[Tutor Management Login Action Code]]
and so the protected admin code therefore looks like this : [[Tutor Management Protect Admin Layout Code]]

Basically once hit the /admin if not authenticated then redirects to Login, however there were few issues while doing this, Issues arose from the original SQL code I ran to create everything in the supabase.

`54001 stack depth limit exceeded` on `rpc("is_admin")` is a **Postgres recursion / deep call stack** problem, and it’s coming from my RLS + function interaction.
This happened because I wrote:
```
create or replace function public.is_admin()
returns boolean
stable
as $$
  select exists (
    select 1
    from public.admin_users au
    where au.user_id = auth.uid()
  );
$$;

```

…and my RLS policy on `admin_users` is:
```
create policy "Admins can read admin_users"
on public.admin_users for select
to authenticated
using (public.is_admin());

```
So when `is_admin()` runs, it queries `admin_users` → but **reading `admin_users` triggers RLS** → which calls `is_admin()` again → which queries `admin_users` again → … recursion until stack depth is exceeded.

To fix this I ran then rant this code: [[Tutor Management Fixing Recursive Error Code]]
Because it’s `SECURITY DEFINER`, the function runs with the privileges of its owner and **does not recurse through RLS** the same way.

> Important: Supabase best practice is also setting `search_path` like above, to avoid security issues.
 
 Also fix my `admin_users` policies (as I don’t need “admins can read admin_users”)

Even with above fix, it’s not very useful to let admins manage the allowlist via normal client queries unless I'm building a UI for that, which I don't.

A safer policy setup:
- Allow **no direct access** to `admin_users` from the client
- Use only `is_admin()` to gate other tables

This was then achieved through this code: [[Tutor Management Drop admin privileges]]
And rely on the `is_admin()` function (security definer) for checks.

That's it now we add something to `(protected)/page.tsx` and add `admin/logout/actions.ts` for logging out.

And Now finally the code at this point looks like this structure:
![[Pasted image 20260208003324.png]]
This way is to creating the authentication very easily.