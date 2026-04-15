

You are fixing two real bugs in my inventory management system. Work like a careful senior full-stack engineer. Do root-cause debugging, then implement a clean fix.

---

# Bug 1, Inventory Adjustment, "Set Quantity (=)" fails for zero

## Current behavior
In inventory item adjustment:

- If I choose adjustment type `Set Quantity (=)`
- And enter quantity `0`
- Then submit

It fails with:

- `Invalid request body`

## Expected behavior
Setting quantity to `0` must be valid.
Zero is a legitimate stock quantity and should mark the item as out of stock.

## Strong suspicion
This is probably caused by validation or request parsing incorrectly treating `0` as missing / falsy.

Common causes:
- `if (!quantity)` type checks
- schema using truthy validation instead of numeric validation
- coercion issue from form input
- server action / API body parser rejecting zero
- frontend omitting zero value from payload
- DTO transform converting `0` to `undefined`
- Zod / custom validator mismatch between string and number
- conditional logic like `quantity || undefined`

## Your task for Bug 1
Trace the full request path:

1. UI form input for adjustment quantity
2. payload creation
3. client submit handler
4. server action / API route
5. validation schema
6. service / repository logic
7. DB update logic

Find exactly where `0` becomes invalid.

### Fix requirements
- `Set Quantity (=)` must accept `0`
- validation must reject invalid values, but not zero
- form and backend must consistently treat quantity as a number
- stock status should update correctly after setting quantity to zero
- no hacks, no special-case UI patching without fixing validation properly

### Specifically inspect for
- `if (!quantity)`
- `quantity ||`
- `quantity ?`
- `.min(1)` where `.min(0)` is intended
- string-to-number parsing
- optional field transforms
- JSON serialization of zero
- request schemas for adjustment type specific rules

---

# Bug 2, Product cannot be made inactive

## Current behavior
On the products page:

- edit an existing product
- untick the `active` checkbox / toggle
- click save

Then:
- it gets stuck, or
- takes too long, or
- never actually saves the inactive state

I am effectively unable to make a product inactive.

## Expected behavior
Toggling active from true to false must save quickly and correctly.

## Strong suspicion
This is likely one of:
- boolean `false` being dropped from payload
- `if (active)` logic only sending true values
- backend partial update ignoring `false`
- form dirty-state logic not detecting false as changed
- schema or transformer turning false into undefined
- save request hanging due to validation or revalidation issue
- optimistic UI / mutation state never resolves on false updates
- checkbox component wiring issue, like sending `"on"` or missing field
- update query only persisting truthy values

## Your task for Bug 2
Trace the full update path:

1. edit product form state
2. active checkbox / switch component
3. payload generation
4. submit handler
5. server action / API route
6. validation schema
7. product update service
8. DB write
9. post-save UI refresh / invalidation

Find exactly why `active = false` is not being saved.

### Fix requirements
- product active status must save both true and false
- no hanging submits
- save should finish quickly
- UI should reflect updated state after save
- payload must include false explicitly when false is selected
- backend update logic must persist false values, not skip them

### Specifically inspect for
- `if (data.active)` patterns
- spread patterns that drop false
- partial update helpers that only include truthy fields
- checkbox `checked` vs `value`
- form libs handling booleans incorrectly
- schema coercion for booleans
- `defaultValues` and dirty tracking
- update object builders like:`...(active && { active })`

which would fail for false

---

# Required debugging process

Do not guess.

For both bugs, do this:

## Step 1

Find the exact root cause in code.

For each bug, report:

- exact file
    
- function
    
- line or code block
    
- why the bad behavior happens
    

## Step 2

Implement the clean fix at the correct architectural level.

## Step 3

Audit nearby code for the same mistake pattern.  
Examples:

- other numeric fields where zero may fail
    
- other boolean fields where false may fail
    

## Step 4

Verify behavior end-to-end.

---

# Common anti-patterns to search for across the codebase

Search for all of these and inspect them carefully:

```ts
if (!quantity)
if (!value)
if (active)
if (data.active)
value || undefined
quantity || 0
quantity || undefined
active || true
...(active && { active })
...(quantity && { quantity })
Boolean(value)
Number(value) || undefined
z.number().min(1)
```

Also inspect:

- Zod schemas
    
- React Hook Form or form library transforms
    
- server actions
    
- route handlers
    
- DTO mappers
    
- repository update builders
    
- cache invalidation and refresh logic
    

---

# Implementation standards

- Keep fixes small, clear, and maintainable
    
- Do not introduce hacks
    
- Do not hardcode around symptoms
    
- Preserve architecture consistency
    
- Reuse shared parsing / validation helpers where appropriate
    
- Make code readable enough for weaker models to maintain later
    

---

# Deliverables

Return all of the following:

## 1. Root cause report

For Bug 1 and Bug 2 separately:

- exact file(s)
    
- exact bad logic
    
- why it fails specifically for `0` or `false`
    

## 2. Fix plan

- what you will change
    
- why that solves the issue correctly
    

## 3. Code edits

- make the actual changes
    

## 4. Audit summary

- list other similar risky spots found and fixed
    

## 5. Verification checklist

Confirm all of these:

### Bug 1

- choose `Set Quantity (=)`
    
- enter `0`
    
- save succeeds
    
- item quantity becomes 0
    
- stock status updates correctly
    
- refresh still shows correct result
    

### Bug 2

- edit product
    
- untick active
    
- save succeeds
    
- request completes normally
    
- product becomes inactive
    
- refresh still shows inactive state
    

## 6. Regression protection

- add/update tests if test framework exists
    
- if not, add at least minimal validation/unit coverage for:
    
    - zero quantity
        
    - false boolean update
        

---

# Important

Do not stop at “likely caused by”.  
Trace the exact flow for both bugs, then fix them properly.

```

A very likely cause here is the classic pair:
- Bug 1: some `if (!quantity)` or schema rejecting `0`
- Bug 2: some update object like `...(active && { active })`, which silently drops `false` and makes inactive impossible.
```