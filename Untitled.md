Yes, **migrate before Phase 5**.

Because Phase 5 will start using the real UI against the real tables and flows. If you only generated migrations but did not apply them, the app can compile while the database is still behind, and then you get confusing runtime failures.

## Run this now

If your `package.json` already has:

```json
"db:migrate": "drizzle-kit migrate"
```

then run:

```bash
pnpm db:migrate
```

After that, good safety checks are:

```bash
pnpm exec tsc --noEmit
pnpm test
pnpm test:e2e
```

If this is only your local dev database, migrate now without hesitation.  
If this were production with live client data, then I’d say review migration SQL first and back up before applying. But for your current stage, **yes, migrate now**.

---

# Phase 5 goal

Phase 5 should turn the backend foundation into a **usable minimal product UI**.

By the end of Phase 5, you should have:

- dashboard auth for internal users
    
- protected dashboard routes
    
- app layout and navigation
    
- products screen
    
- inventory screen
    
- stock movements screen
    
- API keys screen
    
- forms and tables wired to the real services
    
- loading, empty, and error states
    
- e2e coverage for the main UI flows
    
- docs and maps updated
    

This phase should still stay boring. No analytics, no fancy charts, no decorative complexity.

---

# Important change from Phase 4

For Phase 5 and after, use a **smaller context prompt style**.

Instead of making the agent read the whole universe every time, use this compact global prefix.

---

# Global prefix prompt for Phase 5

```text
Read only the minimum required context before making changes.

Always read:
- docs/agent-context/core.md

Then read only the task-relevant module context file:
- docs/agent-context/products.md for product tasks
- docs/agent-context/inventory.md for inventory tasks
- docs/agent-context/stock-movements.md for movement tasks
- docs/agent-context/api-keys.md for API key tasks
- docs/agent-context/auth.md for auth tasks
- docs/ui/dashboard.md and docs/ui/screens.md for UI tasks

Read only the source files, route files, page files, and tests directly related to the task.

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

# Phase 5 prompt pack

Use these in order, one by one.

---

## Task 1 (Done), implement dashboard auth foundation

```text
Task: Implement dashboard authentication foundation using Supabase Auth.

Requirements:
- set up the minimal auth integration needed for internal dashboard access
- keep external API key auth separate from dashboard auth
- create the smallest possible auth utilities for dashboard session handling
- do not build screens yet
- do not mix API key auth into dashboard session logic
- keep files modular and small
- update auth docs, UI docs, and index maps
- add tests where appropriate for auth helpers

Expected outcome:
- dashboard auth foundation exists
- dashboard auth is clearly separated from external API key auth
- docs updated
- tests added where appropriate
```

---

## Task 2, protect dashboard routes

```text
Task: Protect dashboard routes so only authenticated dashboard users can access them.

Requirements:
- add route protection for dashboard pages
- unauthenticated users should be redirected to login or the selected auth entry page
- keep protection logic explicit and minimal
- do not add business logic into middleware beyond access control
- update docs/ui/dashboard.md, docs/modules/auth/* if needed, and index maps
- add integration or e2e coverage for protected route behavior

Expected outcome:
- dashboard routes are protected
- unauthenticated users cannot access dashboard pages
- docs updated
- tests added and passing
```

---

## Task 3, create minimal dashboard shell and navigation

```text
Task: Build the minimal dashboard shell and navigation.

Requirements:
- create a simple dashboard layout
- include navigation for:
  - Overview
  - Products
  - Inventory
  - Stock Movements
  - API Keys
- keep UI very minimal and operational
- use Tailwind and shadcn components only where useful
- do not add charts or decorative widgets
- update docs/ui/dashboard.md and docs/ui/screens.md
- update maps if new files are created

Expected outcome:
- dashboard shell exists
- navigation exists
- docs updated
```

---

## Task 4, create overview screen

```text
Task: Implement the minimal Overview screen.

Requirements:
- create a simple overview page for the dashboard
- show only minimal operational summary
- keep implementation simple and server-driven
- do not add charts
- if counts are shown, use existing services or clean service-level queries
- support loading, empty, and error-safe rendering
- update docs/ui/screens.md and related maps
- add tests if applicable

Expected outcome:
- overview screen exists
- overview stays simple and operational
- docs updated
```

---

## Task 5, build products list screen

```text
Task: Implement the Products screen list view.

Requirements:
- create a Products page in the dashboard
- show product list using a simple table
- include basic fields such as sku, name, threshold, and active state
- keep data access aligned with the architecture
- avoid client-side business logic
- support loading, empty, and error states
- update UI docs and index maps
- add e2e coverage for viewing the Products screen

Expected outcome:
- products list screen exists
- products can be viewed in dashboard
- docs updated
- tests added and passing
```

---

## Task 6, add create product UI flow

```text
Task: Implement the create product UI flow.

Requirements:
- add a create product action from the Products screen
- use a simple form, page, or dialog, whichever keeps complexity lower
- validate inputs correctly
- submit through the clean application boundary
- after success, reflect the new product in the list
- keep files below size limits
- update docs/ui/screens.md, docs/api/products.md if needed, and index maps
- add e2e coverage for successful product creation and invalid submission behavior

Expected outcome:
- products can be created from the dashboard
- invalid input is handled properly
- docs updated
- tests added and passing
```

---

## Task 7, add edit product UI flow

```text
Task: Implement the minimal edit product UI flow.

Requirements:
- allow editing basic product fields
- keep the edit flow simple
- do not add advanced multi-step editing
- ensure updated values are reflected correctly
- preserve architecture boundaries
- update relevant docs and maps
- add e2e coverage for editing a product

Expected outcome:
- product edit flow exists
- docs updated
- tests added and passing
```

---

## Task 8, build inventory screen

```text
Task: Implement the Inventory screen.

Requirements:
- create an Inventory page in the dashboard
- show current stock quantity per product
- show low stock and out of stock indicators using simple badges
- keep rendering simple and readable
- support loading, empty, and error states
- update UI docs and index maps
- add e2e coverage for viewing inventory states

Expected outcome:
- inventory screen exists
- stock states are visible
- docs updated
- tests added and passing
```

---

## Task 9, add adjust stock UI flow

```text
Task: Implement the stock adjustment UI flow.

Requirements:
- add an adjust stock action from the Inventory screen
- allow adjustment types: in, out, adjustment
- validate quantity and required fields
- surface business errors clearly, including below-zero rejection
- after success, refresh inventory state and movement history where appropriate
- keep the flow minimal and operational
- update inventory docs, UI docs, and maps
- add e2e coverage for:
  - successful stock increase
  - successful stock decrease
  - rejection when resulting stock would go below zero

Expected outcome:
- stock adjustment can be performed from the dashboard
- errors are shown clearly
- docs updated
- tests added and passing
```

---

## Task 10, build stock movements screen

```text
Task: Implement the Stock Movements screen.

Requirements:
- create a Stock Movements page in the dashboard
- show movement history in a simple table
- include product, type, quantity, note, source, and timestamp
- keep it operational and readable
- support empty and error states
- do not add advanced filtering unless it remains very small and clean
- update docs and maps
- add e2e coverage for viewing movement history after stock changes

Expected outcome:
- stock movements screen exists
- movement history is visible
- docs updated
- tests added and passing
```

---

## Task 11, build API keys screen list view

```text
Task: Implement the API Keys screen.

Requirements:
- create an API Keys page in the dashboard
- show existing API key metadata without exposing raw keys
- include name, created date, last used date, and revoked state
- keep it simple and operational
- support empty and error states
- update docs and maps
- add e2e coverage for viewing API keys

Expected outcome:
- API keys list screen exists
- raw keys are never exposed in list view
- docs updated
- tests added and passing
```

---

## Task 12, add create API key UI flow

```text
Task: Implement the create API key UI flow.

Requirements:
- allow a dashboard user to create a new API key
- show the raw key exactly once after creation
- clearly indicate that the raw key will not be shown again
- keep the UI minimal
- ensure the list view does not reveal raw keys later
- update API key docs, UI docs, and maps
- add e2e coverage for API key creation and one-time raw key display

Expected outcome:
- API keys can be created from the dashboard
- raw key is shown once only
- docs updated
- tests added and passing
```

---

## Task 13, add revoke API key UI flow

```text
Task: Implement the revoke API key UI flow.

Requirements:
- allow revoking an API key from the dashboard if revoke endpoint and logic already exist
- show revoked state clearly
- do not physically delete keys
- keep the UI and logic simple
- update docs and maps
- add e2e coverage for revoking a key and confirming it is no longer active

Expected outcome:
- API key revoke flow exists
- revoked state is visible
- docs updated
- tests added and passing
```

---

## Task 14, add reusable UI state components

```text
Task: Add reusable UI state components for loading, empty, and error states.

Requirements:
- create very small reusable components or patterns for:
  - loading state
  - empty state
  - error state
- use them across Products, Inventory, Stock Movements, and API Keys screens
- keep them minimal and not overabstracted
- update UI docs and maps
- ensure file size rules are respected

Expected outcome:
- consistent minimal UI states exist across dashboard screens
- docs updated
```

---

## Task 15, add notifications and form feedback

```text
Task: Implement minimal user feedback for dashboard actions.

Requirements:
- use sonner or the current approved notification pattern
- provide clear success and error feedback for:
  - product creation
  - product update
  - stock adjustment
  - API key creation
  - API key revoke
- keep feedback concise and operational
- update UI docs if necessary
- add tests only where practical and valuable

Expected outcome:
- dashboard actions provide clear feedback
- docs updated
```

---

## Task 16, harden dashboard UX states

```text
Task: Harden dashboard UX for operational use.

Requirements:
- ensure forms disable or guard against duplicate submission where appropriate
- ensure buttons and actions have sensible pending states
- ensure screens behave correctly with empty datasets
- ensure business errors are surfaced clearly
- keep implementation simple
- update docs/ui/screens.md and maps
- add e2e coverage for key empty-state and failure-state behaviors

Expected outcome:
- dashboard behaves safely under common operational scenarios
- docs updated
- tests added and passing
```

---

## Task 17, add end-to-end Phase 5 coverage

```text
Task: Add end-to-end coverage for the main dashboard flows.

Requirements:
- cover authenticated dashboard access
- cover product creation
- cover product editing
- cover stock adjustment
- cover movement history visibility
- cover API key creation
- cover API key revoke if implemented
- keep tests focused and readable
- update index/test-map.md and testing docs if needed

Expected outcome:
- core Phase 5 flows are covered by e2e tests
- docs updated
- tests added and passing
```

---

## Task 18, Phase 5 cleanup and consistency pass

```text
Task: Perform a full Phase 5 cleanup and consistency pass.

Requirements:
- check file sizes and split oversized files
- ensure all UI flows respect architecture boundaries
- ensure no client UI files access repo or db directly
- ensure docs, maps, and tests are complete
- ensure loading, empty, and error states exist on all main screens
- run lint, typecheck, unit tests, integration tests, and e2e tests
- prepare a short summary of completed Phase 5 work
- commit and push all completed logical changes if not already done

Expected outcome:
- Phase 5 is complete, consistent, and reviewable
- docs and maps are synchronized
- tests are passing
- project is ready for Phase 6
```

---

# Best order to run them

Run exactly in this order:

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
    
11. Task 11
    
12. Task 12
    
13. Task 13
    
14. Task 14
    
15. Task 15
    
16. Task 16
    
17. Task 17
    
18. Task 18
    

---

# What you should review after each task

Check these only:

- did it touch unrelated files
    
- did it break file size rules
    
- did it push logic into pages or client UI where it should not live
    
- did it skip docs
    
- did it skip maps
    
- did it add weird abstractions
    
- did it add heavy UI you never asked for
    
- did tests really cover the new behavior
    

---

# One small thing before starting Phase 5

Create and commit the migration first:

```bash
pnpm db:migrate
git add .
git commit -m "chore: apply v1 database migrations"
git push
```

Then start Task 1.

If you want, next I can generate the **`docs/agent-context/*.md` files** so your smaller models stop reading half the repo for every task.