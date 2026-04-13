Yes, here are the ready-to-paste **API docs files** in the same strict style.
---

# `docs/api/products.md`

```md
# Products API

## Purpose
Exposes product-related endpoints for dashboard and external integrations.

## Endpoints

### GET /api/products
Returns a list of products.

#### Query support
- optional search later
- optional active filter later

#### Success response
```json
{
  "success": true,
  "data": {
    "items": []
  },
  "error": null
}
```

#### Error response

```json
{
  "success": false,
  "data": null,
  "error": {
    "code": "UNAUTHORIZED",
    "message": "Invalid API key"
  }
}
```

### POST /api/products

Creates a new product.

#### Request body

```json
{
  "sku": "SKU-001",
  "name": "Sample Product",
  "description": "Optional description",
  "lowStockThreshold": 10,
  "isActive": true
}
```

#### Success response

```json
{
  "success": true,
  "data": {
    "id": "product_id",
    "sku": "SKU-001",
    "name": "Sample Product",
    "description": "Optional description",
    "lowStockThreshold": 10,
    "isActive": true
  },
  "error": null
}
```

## Validation rules

- sku is required
    
- name is required
    
- lowStockThreshold must be zero or greater
    
- sku must be unique
    

## Dependencies

- products.service
    
- products.schema
    
- products.repo
    

## Forbidden

- business logic in route handler
    
- direct database access in route handler
    

## Edge Cases

- duplicate sku
    
- empty product name
    
- invalid threshold
    
- malformed request body
    

## Tests

- integration: tests/integration/api
    
- e2e: tests/e2e
    

````

---

# `docs/api/inventory.md`

```md
# Inventory API

## Purpose
Exposes inventory status and stock adjustment endpoints.

## Endpoints

### GET /api/inventory
Returns inventory items and their current stock state.

#### Success response
```json
{
  "success": true,
  "data": {
    "items": [
      {
        "productId": "product_id",
        "quantity": 25,
        "isLowStock": false,
        "isOutOfStock": false
      }
    ]
  },
  "error": null
}
````

### POST /api/inventory/adjust

Adjusts stock for a product.

#### Request body

```json
{
  "productId": "product_id",
  "type": "adjustment",
  "quantity": 5,
  "note": "Manual correction",
  "source": "dashboard"
}
```

#### Supported types

- in
    
- out
    
- adjustment
    

#### Success response

```json
{
  "success": true,
  "data": {
    "productId": "product_id",
    "quantity": 30,
    "isLowStock": false,
    "isOutOfStock": false
  },
  "error": null
}
```

### GET /api/inventory/low-stock

Returns items at or below their low stock threshold.

#### Success response

```json
{
  "success": true,
  "data": {
    "items": []
  },
  "error": null
}
```

## Validation rules

- productId is required
    
- quantity must be greater than zero
    
- type must be one of the allowed values
    
- stock cannot go below zero
    

## Transaction rules

- stock change and movement log must happen together
    
- failed movement log means failed stock update
    
- no partial write allowed
    

## Dependencies

- inventory.service
    
- inventory.schema
    
- inventory.repo
    
- stock-movements.repo
    

## Forbidden

- direct database access in route handler
    
- direct UI logic in API layer
    

## Edge Cases

- missing product
    
- zero quantity
    
- negative quantity
    
- stock below zero attempt
    
- low stock threshold exact match
    
- concurrent update attempt
    

## Tests

- integration: tests/integration/api
    
- e2e: tests/e2e
    

````

---

# `docs/api/stock-movements.md`

```md
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
    

````

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
    

````

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