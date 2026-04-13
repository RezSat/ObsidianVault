
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