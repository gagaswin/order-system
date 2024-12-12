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
  "id": 1,
  "name": "Product 1",
  "type": "Product Type 1",
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
      "id": 1,
      "name": "Product 1",
      "type": "Product Type 1",
      "stock": 100,
      "price": 50.0
    }
  ],
  "size": 10,
  "totalElements": 100,
  "totalPages": 10
}
```

#### 1.3 Get Product by ID
**GET** `/api/products/{id}`
- **Response:**
```json
{
  "id": 1,
  "name": "Product 1",
  "type": "Product Type 1",
  "stock": 100,
  "price": 50.0
}
```

#### 1.4 Update Product
**PUT** `/api/products/{id}`
- **Request Body:**
```json
{
  "name": "Updated 1",
  "type": "Updated Type 1",
  "stock": 50,
  "price": 75.0
}
```
- **Response:**
```json
{
  "id": 1,
  "name": "Updated 1",
  "type": "Updated Type 1",
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
  "userName": "John Doe",
  "address": "123 Main St"
}
```
- **Response:**
```json
{
  "orderId": 1,
  "userName": "John Doe",
  "address": "123 Main St",
  "orderItems": [
    {
      "productId": 1,
      "quantity": 2,
      "price": 50.0
    }
  ],
  "totalPrice": 100.0,
  "status": "PLACED"
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

