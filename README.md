# Simple Mobile Place Order System

## API Endpoints

### 1. Product API
#### 1.1 Create Product
**POST** `/api/products`
- **Request Body:**
```json
{
  "name": "Product 1",
  "type": "Product Type 1",
  "stock": 100,
  "price": 50.0
}
```
- **Response:**
```json
{
  "id": 13,
  "name": "Product 100",
  "type": "Product Type 100",
  "stock": 100,
  "price": 50.0
}
```

#### 1.2 Get All Products with Pagination
**GET** `/api/products`
- **Query Parameters:**
  - `page`: Page number (default: 0)
  - `size`: Page size (default: 10)
- **Response:**
```json
{
  "page": 0,
  "data": [
    {
      "id": 2,
      "name": "Product 2",
      "type": "Type 2",
      "stock": 15,
      "price": 200.0
    },
    {
      "id": 3,
      "name": "Product 3",
      "type": "Type 3",
      "stock": 5,
      "price": 50.0
    },
    {
      "id": 4,
      "name": "Product 4",
      "type": "Type 4",
      "stock": 5,
      "price": 50.0
    },
    {
      "id": 5,
      "name": "Product 5",
      "type": "Type 1",
      "stock": 5,
      "price": 50.0
    },
    {
      "id": 6,
      "name": "Product 6",
      "type": "Type 1",
      "stock": 5,
      "price": 50.0
    },
    {
      "id": 7,
      "name": "Product 7",
      "type": "Type 2",
      "stock": 5,
      "price": 50.0
    },
    {
      "id": 8,
      "name": "Product 8",
      "type": "Type 2",
      "stock": 5,
      "price": 50.0
    },
    {
      "id": 9,
      "name": "Product 9",
      "type": "Type 3",
      "stock": 5,
      "price": 50.0
    },
    {
      "id": 10,
      "name": "Product 10",
      "type": "Type 3",
      "stock": 5,
      "price": 50.0
    },
    {
      "id": 11,
      "name": "Product 11",
      "type": "Type 4",
      "stock": 5,
      "price": 50.0
    }
  ],
  "totalElements": 13,
  "totalPages": 2,
  "size": 10
}
```

#### 1.3 Get Product by ID
**GET** `/api/products/{id}`
- **Response:**
```json
{
  "id": 1,
  "name": "Product 1",
  "type": "Type 1",
  "stock": 8,
  "price": 100.0
}
```

#### 1.4 Update Product
**PUT** `/api/products/{id}`
- **Request Body:**
```json
{
  "name": "Updated Name 100",
  "type": "Updated Type 100",
  "stock": 50,
  "price": 75.0
}
```
- **Response:**
```json
{
  "id": 13,
  "name": "Updated Name 100",
  "type": "Updated Type 100",
  "stock": 50,
  "price": 75.0
}
```

#### 1.5 Delete Product
**DELETE** `/api/products/{id}`
- **Response:** 204 No Content

---

### 2. Add Product to Order Cart
**POST** `/api/order-cart`
- **Request Body:**
```json
{
  "productId": 1,
  "quantity": 2,
  "userName": "john",
  "address": "Jamaica"
}
```
- **Response:**
```json
{
  "id": 1,
  "userName": "john",
  "address": "Jamaica",
  "status": "DRAFT",
  "orderItems": [
    {
      "productId": 1,
      "bookName": "Product 1",
      "type": "Type 1",
      "pricePerQty": 100.0,
      "quantity": 2,
      "price": 200.0
    }
  ]
}
```

---

### 3. Order Cart API
#### 3.1 Get Order Cart
**GET** `/api/order-cart`
- **Query Parameters:**
  - `userName`: john
  
- **Response:**
```json
{
  "id": 1,
  "userName": "john",
  "address": "Jamaica",
  "status": "DRAFT",
  "orderItems": [
    {
      "productId": 1,
      "bookName": "Product 1",
      "type": "Type 1",
      "pricePerQty": 100.0,
      "quantity": 2,
      "price": 200.0
    }
  ]
}
```

#### 3.2 Place Order
**POST** `/api/orders`
- **Request Body:**
```json
{
  "cartId": 1,
  "itemsId": [1]
}
```
- **Response:**
```json
{
  "id": 1,
  "userName": "john",
  "address": "Jamaica",
  "status": "PLACED",
  "orderItems": [
    {
      "productId": 1,
      "bookName": "Product 1",
      "type": "Type 1",
      "pricePerQty": 100.0,
      "quantity": 2,
      "price": 200.0
    }
  ]
}
```

---

## Database Relationships

### ER Diagram Explanation
1. **Product**:
- Primary Key: `id`
- Attributes: `name`, `type`, `stock`, `price`
- No direct relationship, but referenced in `OrderItem`.

2. **OrderCart**:
- Primary Key: `id`
- Attributes: `userName`, `address`, `status`
- One-to-Many Relationship with `OrderItem`.

3. **OrderItem**:
- Primary Key: `id`
- Foreign Key to `Product` (`productId`).
- Foreign Key to `OrderCart` (`orderCartId`).
- Attributes: `quantity`, `price`.

### Relationships
- **One-to-Many**:
  - One `OrderCart` can contain many `OrderItems`.
- **Foreign Key**:
  - `OrderItem.productId` references `Product.id`.
  - `OrderItem.orderCartId` references `OrderCart.id`.

## Notes
- Use HTTP status codes appropriately:
  - 200 for successful retrievals
  - 201 for successful creation
  - 204 for successful deletion
  - 400 for invalid input
  - 404 for resource not found
  - 500 for server errors
- Validate input data and ensure proper error handling.

