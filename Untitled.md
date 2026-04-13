# Task 1 (DONE), implement product creation repo and service

```text
Task: Implement product creation domain logic.

Requirements:
- build product repository insert logic
- build product service create logic
- validate input with existing product schema
- reject duplicate SKU
- keep route handlers untouched in this task
- keep files small and modular
- update relevant docs and index maps
- add unit tests for product validation and duplicate SKU behavior where possible

Expected outcome:
- product create service exists
- product insert repo exists
- service can validate and create product safely
- docs and index files are updated
- tests are added and passing

Do not implement inventory initialization yet.
```

---

# Task 2 (DONE), initialize inventory record when product is created

```text
Task: Extend product creation workflow to also initialize an inventory row.

Requirements:
- after product creation, create a matching inventory_items row with quantity 0
- perform product creation and inventory initialization safely
- if both operations are part of the same flow, keep the workflow consistent
- keep logic in service and repo layers only
- do not implement API routes in this task
- update docs/architecture/database.md if needed
- update module docs and index maps
- add unit or integration tests covering product + inventory initialization behavior

Expected outcome:
- creating a product also creates an inventory record with quantity 0
- docs updated
- tests added and passing
```

---

# Task 3(DONE), implement stock movement creation repo and service

```text
Task: Implement stock movement creation logic.

Requirements:
- add repository function to insert stock movement records
- add service-level validated creation entry point
- use existing movement schema and types
- keep logic simple and explicit
- do not yet connect this to stock adjustment flow
- update docs for stock-movements module and code maps
- add unit tests for validation and integration-level test coverage if appropriate

Expected outcome:
- movement repo insert exists
- movement service create entry exists
- docs updated
- tests added and passing
```

---

# Task 4 (DONE), implement stock adjustment transaction logic

```text
Task: Implement inventory stock adjustment logic with correct business rules.

Requirements:
- add inventory service logic to adjust stock for type: in, out, adjustment
- validate payload with existing inventory schema
- read current inventory state
- prevent resulting stock from going below zero
- use transaction logic so stock change is safe
- do not create route handlers in this task
- keep files below line limits
- update docs and index files
- add unit tests for:
  - increase stock
  - decrease stock
  - adjustment
  - invalid zero quantity
  - negative resulting stock rejection

Expected outcome:
- inventory adjustment service exists
- negative stock is blocked
- docs updated
- tests added and passing
```

---

# Task 5, connect stock adjustment with movement logging

```text
Task: Connect stock adjustment flow with stock movement logging.

Requirements:
- when stock is adjusted, create a corresponding stock movement record
- stock update and movement insert must succeed together or fail together
- keep orchestration in service layer
- use repo functions for persistence
- keep logic explicit and easy to review
- update docs/architecture/database.md and inventory / stock-movements module docs
- update index maps
- add integration tests that verify:
  - stock change writes movement
  - failed stock change does not write movement
  - failed movement insert does not leave partial stock update

Expected outcome:
- stock adjustment and movement logging are linked
- transactional behavior is enforced
- docs updated
- tests added and passing
```

---

# Task 6, implement low stock and out of stock query logic

```text
Task: Implement low stock and out of stock query logic.

Requirements:
- create service and repo logic to list inventory items that are:
  - low stock
  - out of stock
- low stock means quantity is less than or equal to lowStockThreshold
- out of stock means quantity equals 0
- keep logic explicit and easy to test
- do not build routes yet
- update inventory docs and index maps
- add unit and integration tests for:
  - exact threshold match
  - below threshold
  - zero quantity
  - non-low-stock items excluded

Expected outcome:
- low stock query exists
- out of stock query exists
- docs updated
- tests added and passing
```

---

# Task 7, implement API key creation logic

```text
Task: Implement API key creation logic for external integrations.

Requirements:
- generate raw API key
- hash raw API key before storage
- store only hashed key
- return raw key only once at creation
- add repo and service support as needed
- do not implement revoke yet unless needed for file design
- keep logic explicit and small
- update api-keys docs, auth docs, and index maps
- add unit tests for:
  - key generation
  - key hashing
  - no raw key persistence
- add integration test coverage if appropriate

Expected outcome:
- API key creation service exists
- hashed key is stored
- raw key is returned once
- docs updated
- tests added and passing
```

---

# Task 8, implement API key authorization flow

```text
Task: Implement API key authorization logic for external API access.

Requirements:
- read x-api-key header using existing helper
- hash provided raw key
- lookup active non-revoked key
- return normalized authorization result
- keep logic in auth service and auth repo layers
- do not add route handlers yet
- update auth docs and index maps
- add unit tests for:
  - valid key
  - missing key
  - revoked key
  - invalid key

Expected outcome:
- API key authorization logic exists
- auth service returns clear result
- docs updated
- tests added and passing
```

---

# Task 9, add products API route handlers

```text
Task: Implement products API routes.

Requirements:
- add GET /api/products
- add POST /api/products
- route handlers must stay thin
- route handlers must call service layer only
- use standard response helpers
- return consistent success and error shapes
- wire POST /api/products to create product and initialize inventory
- update docs/api/products.md and all relevant maps
- add integration tests for:
  - successful create
  - duplicate SKU rejection
  - invalid body rejection
  - list products success

Expected outcome:
- products routes exist
- routes are thin
- docs updated
- integration tests passing
```

---

# Task 10, add inventory API route handlers

```text
Task: Implement inventory API routes.

Requirements:
- add GET /api/inventory
- add POST /api/inventory/adjust
- add GET /api/inventory/low-stock
- route handlers must stay thin
- route handlers must call service layer only
- use standard response helpers
- inventory adjust route must use stock adjustment transaction logic
- update docs/api/inventory.md and relevant maps
- add integration tests for:
  - successful stock increase
  - successful stock decrease
  - rejection when stock would go below zero
  - low stock listing
  - invalid payload rejection

Expected outcome:
- inventory routes exist
- stock adjustment route is working
- low stock route is working
- docs updated
- integration tests passing
```

---

# Task 11, add stock movements API route handler

```text
Task: Implement stock movements API route.

Requirements:
- add GET /api/stock-movements
- keep route thin
- use service layer only
- use standard response shape
- update docs/api/stock-movements.md and maps
- add integration tests for:
  - list movement history
  - empty history case
  - optional product-based query support only if already designed cleanly

Expected outcome:
- stock movements route exists
- docs updated
- integration tests passing
```

---

# Task 12, add API keys API route handlers

```text
Task: Implement API key API routes.

Requirements:
- add POST /api/api-keys
- add GET /api/api-keys
- add POST /api/api-keys/revoke if current design supports it cleanly
- raw key must only be returned on creation
- route handlers must stay thin
- use service layer only
- update docs/api/api-keys.md and maps
- add integration tests for:
  - create key
  - list keys without raw key exposure
  - revoke key if implemented
  - malformed payload rejection

Expected outcome:
- API key routes exist
- raw key exposure rule is enforced
- docs updated
- integration tests passing
```

---

# Task 13, protect external API routes with API key auth where appropriate

```text
Task: Add API key authorization protection to external API routes.

Requirements:
- apply API key auth to the routes intended for external system access
- use existing auth service and API key helper
- keep authorization checks explicit and small
- do not mix business logic into auth checks
- update auth docs, API docs, and index maps
- add integration tests for:
  - missing API key
  - invalid API key
  - revoked API key
  - valid API key success

Expected outcome:
- protected routes reject unauthorized requests
- authorized requests succeed
- docs updated
- integration tests passing
```

---

# Task 14, add final Phase 4 cleanup pass

```text
Task: Perform a Phase 4 cleanup pass.

Requirements:
- check file sizes and split any file over 150 lines
- ensure all new files are documented
- ensure all index maps are updated
- ensure route handlers are thin
- ensure no UI files access repo or db directly
- ensure no route file contains business logic
- add any missing tests for critical domain flows
- run lint, tests, and migration generation
- prepare a summary of completed Phase 4 work
- commit and push all completed logical changes if not already done

Expected outcome:
- Phase 4 codebase is consistent
- docs and maps are complete
- tests are passing
- project is ready for Phase 5
```

---

# Best order to run these prompts

Use this order exactly:

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
    

---

# What you should review after each task

Check only these:

- did it touch unrelated files?
    
- did it exceed file size rules?
    
- did it hide logic in routes?
    
- did it skip docs?
    
- did it skip maps?
    
- did tests actually cover the new logic?
    
- did commit message stay clean?
    

If it violates any of these, reject and rerun narrowly.

---

# The one-line instruction to keep repeating to the AI

```text
Do the smallest complete change that satisfies the task, then update docs, maps, tests, commit, and push.
```

Next, I can turn this into a **single master operational file** like `docs/phase-4-prompt-pack.md` so you can just drop it into the repo and let agents use it directly.