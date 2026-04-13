

---

# `docs/api/stock-movements.md`


# Stock Movements API

## Purpose
Exposes stock movement history for dashboard and external integrations.

## Endpoints

### GET /api/stock-movements
Returns stock movement history.

#### Query support
- optional productId filter later
- optional type filter later

#### Success response
```json
{
  "success": true,
  "data": {
    "items": [
      {
        "id": "movement_id",
        "productId": "product_id",
        "type": "in",
        "quantity": 10,
        "note": "Initial stock",
        "source": "dashboard",
        "createdAt": "2026-04-14T10:00:00.000Z"
      }
    ]
  },
  "error": null
}
````

## Validation rules

- filter values must be valid when added
    
- type filter must match allowed movement types
    

## Dependencies

- stock-movements.service
    
- stock-movements.schema
    
- stock-movements.repo
    

## Forbidden

- direct database access in route handler
    
- movement classification logic in route handler
    

## Edge Cases

- empty history
    
- invalid filter
    
- missing product id filter target
    
- malformed query parameters
    

## Tests

- integration: tests/integration/api
    
- e2e: tests/e2e
    

---

# Also add this one too, it will help later  
`docs/api/api-keys.md`

```md
# API Keys API

## Purpose
Exposes API key management endpoints for external integration access.

## Endpoints

### POST /api/api-keys
Creates a new API key.

#### Request body
```json
{
  "name": "POS Integration"
}
````

#### Success response

```json
{
  "success": true,
  "data": {
    "id": "key_id",
    "name": "POS Integration",
    "key": "raw_key_only_once"
  },
  "error": null
}
```

### GET /api/api-keys

Returns existing API key metadata without exposing raw keys.

#### Success response

```json
{
  "success": true,
  "data": {
    "items": [
      {
        "id": "key_id",
        "name": "POS Integration",
        "lastUsedAt": null,
        "revokedAt": null,
        "createdAt": "2026-04-14T10:00:00.000Z"
      }
    ]
  },
  "error": null
}
```

### POST /api/api-keys/revoke

Revokes an API key.

#### Request body

```json
{
  "id": "key_id"
}
```

## Validation rules

- name is required on create
    
- id is required on revoke
    
- raw keys are never returned again after creation
    
- revoked keys must fail authorization
    

## Dependencies

- api-keys.service
    
- api-keys.schema
    
- api-keys.repo
    
- auth.service
    

## Forbidden

- storing raw key in database
    
- returning hashed key to clients
    
- route-level business logic
    

## Edge Cases

- empty key name
    
- revoke non-existing key
    
- reuse revoked key
    
- malformed request body
    

## Tests

- integration: tests/integration/api
    
- e2e: tests/e2e
    

---

# And update this file too  
`index/doc-map.md`

Add these lines under API docs:

```md
## API docs
- docs/api/products.md → product endpoint contract
- docs/api/inventory.md → inventory endpoint contract
- docs/api/stock-movements.md → stock movement endpoint contract
- docs/api/api-keys.md → API key endpoint contract
````

---

# And create this missing file

PowerShell:

```powershell
ni docs\api\api-keys.md -ItemType File -Force
```

---

# Tiny cleanup

Also update `index/module-map.md` for api-keys:

```md
## api-keys
Code:
- src/modules/api-keys
Docs:
- docs/modules/api-keys
API:
- docs/api/api-keys.md
Tests:
- tests/unit/api-keys
- tests/integration/api
```

And for auth:

```md
## auth
Code:
- src/modules/auth
Docs:
- docs/modules/auth
Tests:
- tests/unit/auth
- tests/integration/api
```

---

Phase 2 will be in very good shape after this.

Next best step is **Phase 3**, meaning:

- finalize actual DB schema
    
- create module schema files
    
- create first service and repo contracts
    
- set up API key hashing properly