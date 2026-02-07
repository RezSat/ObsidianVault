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