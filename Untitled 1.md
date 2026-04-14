Yes, pause Phase 5 and do **Phase 5A, Auth first**.

This is the right move because the dashboard should not keep growing before login, password reset, and route protection are solid.

---

# What Phase 5A should finish

By the end of Phase 5A, you should have:

- Supabase auth wired correctly
    
- login page
    
- logout flow
    
- forgot password page
    
- reset password page
    
- protected dashboard routes
    
- one clear internal admin access flow
    
- docs and maps updated
    
- tests for auth-critical flows
    

Do **not** continue product, inventory, API key screens until this is done.

---

# Use this compact global prefix before every auth task

```text
Read only the minimum required context before making changes.

Always read:
- docs/agent-context/core.md
- docs/agent-context/auth.md
- docs/ui/dashboard.md
- docs/ui/screens.md

Then read only the source files, page files, route files, and tests directly related to the task.

Strict rules:
- no file may exceed 150 lines
- target under 100 lines
- one file, one responsibility
- no business logic in route handlers
- no direct database access from UI client components
- no hidden optimizations
- no unnecessary abstractions
- update docs, index files, and tests for every meaningful change
- run lint, typecheck, and tests before finishing
- commit and push after each completed logical unit

Architecture:
- UI → service → repo → db
- API → service → repo → db

Do only the requested task. Do not implement unrelated features.
Do the smallest complete change that satisfies the task, then update docs, maps, tests, commit, and push.
```

---

# Phase 5A prompt pack, auth-first

Use these in order.

---

## Task 1, verify and finalize Supabase auth foundation

```text
Task: Verify and finalize the Supabase dashboard auth foundation.

Requirements:
- inspect the current auth foundation built in Phase 5 Task 1
- complete any missing minimal auth client setup needed for dashboard auth
- clearly separate dashboard auth from external API key auth
- keep the implementation small and explicit
- do not build dashboard screens yet beyond auth pages
- update auth docs and index maps
- add or update tests for auth helper behavior where practical

Expected outcome:
- Supabase dashboard auth foundation is complete and reviewable
- dashboard auth is clearly separated from external API key auth
- docs updated
- tests added or updated where valuable
```

---

## Task 2, implement login screen

```text
Task: Implement the dashboard login screen.

Requirements:
- create a minimal login page for internal dashboard users
- support email and password login with Supabase Auth
- keep UI simple and operational
- show clear validation and failure messages
- do not add signup flow
- keep files modular and small
- update docs/ui/screens.md, docs/ui/dashboard.md, auth docs, and maps
- add e2e coverage for successful login and invalid login behavior

Expected outcome:
- login page exists and works
- invalid credentials are handled clearly
- docs updated
- tests added and passing
```

---

## Task 3, implement logout flow

```text
Task: Implement the dashboard logout flow.

Requirements:
- add a minimal logout action for authenticated dashboard users
- ensure session is cleared properly
- redirect the user to the login page or selected public entry page after logout
- keep implementation explicit and small
- update auth docs, UI docs, and maps
- add e2e coverage for logout behavior

Expected outcome:
- authenticated users can log out cleanly
- logout clears access to protected dashboard routes
- docs updated
- tests added and passing
```

---

## Task 4, implement forgot password screen

```text
Task: Implement the forgot password screen.

Requirements:
- create a minimal forgot password page
- allow dashboard users to request a password reset email
- use Supabase password reset flow correctly
- show clear success and failure states
- do not add unrelated account recovery features
- update auth docs, UI docs, and maps
- add tests where practical and valuable

Expected outcome:
- forgot password page exists
- reset email request flow works
- docs updated
- tests added where practical
```

---

## Task 5, implement reset password screen

```text
Task: Implement the reset password screen.

Requirements:
- create a minimal reset password page
- handle the Supabase password reset redirect flow correctly
- allow user to set a new password
- show clear success and failure states
- keep the implementation simple and operational
- update auth docs, UI docs, and maps
- add e2e coverage for the reset password flow where practical

Expected outcome:
- reset password page exists
- password reset completion works
- docs updated
- tests added where practical and passing
```

---

## Task 6, protect dashboard routes

```text
Task: Protect all dashboard routes with authenticated dashboard session checks.

Requirements:
- ensure unauthenticated users cannot access dashboard pages
- redirect unauthenticated users to the login page or selected auth entry page
- keep route protection logic explicit and minimal
- do not mix API key auth into dashboard route protection
- update auth docs, dashboard docs, and maps
- add integration or e2e coverage for protected route behavior

Expected outcome:
- dashboard routes are protected
- unauthenticated users are redirected away from dashboard pages
- docs updated
- tests added and passing
```

---

## Task 7, add session-aware dashboard shell behavior

```text
Task: Add minimal session-aware behavior to the dashboard shell.

Requirements:
- ensure the dashboard shell can safely render authenticated user state where needed
- keep it minimal, for example showing current user email or a simple account area only if useful
- do not add profile systems or settings pages
- keep logic explicit and small
- update UI docs and maps
- add tests where practical

Expected outcome:
- dashboard shell is aware of authenticated user state
- UI remains minimal and operational
- docs updated
```

---

## Task 8, harden auth UX states

```text
Task: Harden auth UX states for operational use.

Requirements:
- ensure login, forgot password, and reset password forms have proper pending states
- ensure duplicate submissions are prevented where appropriate
- ensure clear error and success feedback is shown
- keep the UI simple and small
- update docs/ui/screens.md and auth docs if needed
- add or update tests where valuable

Expected outcome:
- auth screens behave safely and clearly under normal usage
- docs updated
- tests added or updated where practical
```

---

## Task 9, add end-to-end coverage for auth flows

```text
Task: Add end-to-end coverage for the main dashboard auth flows.

Requirements:
- cover unauthenticated redirect from dashboard route
- cover successful login
- cover failed login
- cover logout
- cover forgot password flow as far as practical in test environment
- cover reset password flow as far as practical in test environment
- keep tests focused and readable
- update index/test-map.md and testing docs if needed

Expected outcome:
- core auth flows are covered by e2e tests
- docs updated
- tests passing
```

---

## Task 10, Phase 5A cleanup and consistency pass

```text
Task: Perform a full Phase 5A auth cleanup and consistency pass.

Requirements:
- check file sizes and split oversized files
- ensure auth remains clearly separated from API key authorization
- ensure all auth-related docs and maps are updated
- ensure login, logout, forgot password, reset password, and protected route flows are complete
- run lint, typecheck, unit tests, integration tests, and e2e tests
- prepare a short summary of completed Phase 5A work
- commit and push all completed logical changes if not already done

Expected outcome:
- Phase 5A auth system is complete, consistent, and reviewable
- docs and maps are synchronized
- tests are passing
- project is ready to resume the remaining Phase 5 dashboard work
```

---

# Run order

Use this exact order:

1. Task 1
    
2. Task 2
    
3. Task 3
    
4. Task 4
    
5. Task 5
    
6. Task 6
    
7. Task 7
    
8. Task 8
    
9. Task 9
    
10. Task 10
    

---

# Manual Supabase dashboard setup you should do alongside this

Before or during these tasks, make sure in Supabase dashboard you configure:

- Email auth enabled
    
- Site URL
    
- Redirect URLs for local and production
    
- password reset redirect target
    

For local dev, you will usually need URLs like:

```text
http://localhost:3000/login
http://localhost:3000/reset-password
http://localhost:3000/dashboard
```

And your real production equivalents later.

---

# Important scope limits

During Phase 5A, do **not** add:

- public signup
    
- Google login
    
- GitHub login
    
- roles and permissions matrix
    
- profile page
    
- user management UI
    
- invite system
    

This phase is only about getting **internal dashboard auth** working correctly.

---

# After Phase 5A

Once this auth pack is complete, then continue the original Phase 5 prompt pack from the dashboard shell and screens side, with much less risk.

Next best move is creating the small `docs/agent-context/*.md` files so the weaker agents stop reading half the repo for each task.