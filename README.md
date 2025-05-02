# ☕ Coffee API

A Java Spring Boot REST API for managing users, coffees, and coffee purchases. This project uses PostgreSQL as the database and includes business rules such as stock control and balance verification.

## 🛠 Technologies

* Java 17+
* Spring Boot 3
* Spring Data JPA
* PostgreSQL
* Maven
* Lombok
* MapStruct

## 📦 Features

### Users

* Register a user with name, email, and balance.
* Get all users.
* Get user by ID or email.
* Delete a user by ID.

### Coffees

* Pre-loaded coffee data using `CommandLineRunner`.
* List all available coffee types.
* **Users cannot register new coffees** — only view them.

### Purchases

* Purchase one or more coffee types.
* System checks:

  * User must exist.
  * User must have sufficient balance.
  * Stock must be sufficient.
* Deducts the total cost from user balance.
* Reduces coffee stock after purchase.
* Records total quantity and purchase time.
* Throws appropriate exceptions if rules are violated.

## 📐 Architecture

The project follows a layered architecture with clear separation of responsibilities:

### 📁 `controllers`

* Expose REST endpoints to handle HTTP requests and return responses.
* Receive data via DTOs and delegate logic to services.
* Example: `UserController`, `CoffeeController`, `PurchaseController`.

### 📁 `dtos` (Data Transfer Objects)

* Represent external data structures used to receive or send data in HTTP requests and responses.
* Help avoid exposing internal models directly.
* Example: `UserCreateDto`, `PurchaseCreateDto`, `PurchaseResponseDto`.

### 📁 `exceptions`

* Contain custom exception classes for handling business rule violations and meaningful error responses.
* Example: `InsufficientStockException`, `InsufficientMoneyException`, `EmailAlreadyExistsException`.

### 📁 `initializers`

* Responsible for seeding initial data (like coffee types) when the application starts.
* Implemented using Spring Boot's `CommandLineRunner`.

### 📁 `mappers`

* Convert between `models` (entities) and `dtos`.
* Implemented with [MapStruct](https://mapstruct.org/) to reduce boilerplate code.
* Example: `UserMapper`, `PurchaseMapper`.

### 📁 `models`

* JPA Entities that represent the database tables.
* Used internally for persistence and business logic.
* Example: `User`, `Coffee`, `Purchase`.

### 📁 `repositories`

* Interfaces that extend `JpaRepository` to provide CRUD operations for entities.
* Example: `UserRepository`, `CoffeeRepository`, `PurchaseRepository`.

### 📁 `services`

* Contain the core business logic.
* Orchestrate data manipulation, validation, and coordination between repository and controller layers.
* Example: `UserService`, `CoffeeService`, `PurchaseService`.

## 🧪 Example Coffees (Seed Data)

Loaded automatically at startup:

* **Expresso** – strong and bold (R\$5.00)
* **Latte** – with steamed milk (R\$6.50)
* **Cappuccino** – with milk foam (R\$7.00)

## 💾 Database Configuration (PostgreSQL)

In `application.properties` or `application.yml`, configure like so:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/coffee-db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

## 📡 Endpoints

### Users

| Method | Endpoint                        | Description       |
| ------ | ------------------------------- | ----------------- |
| POST   | `/users`                        | Register new user |
| GET    | `/users`                        | List all users    |
| GET    | `/users/getUserById/{id}`       | Get user by ID    |
| GET    | `/users/getUserByEmail/{email}` | Get user by email |
| DELETE | `/users/deleteFromUser/{id}`    | Delete user by ID |

### Coffees

| Method | Endpoint   | Description           |
| ------ | ---------- | --------------------- |
| GET    | `/coffees` | List all coffee types |

### Purchases

| Method | Endpoint     | Description        |
| ------ | ------------ | ------------------ |
| POST   | `/purchases` | Make a purchase    |
| GET    | `/purchases` | List all purchases |

## 📑 DTOs

### UserCreateDto

```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "123",
  "balance": 100.00
}
```

### PurchaseCreateDto

```json
{
  "emailUser": "john@example.com",
  "coffeQuantities": {
    "Latte": 2,
    "Cappuccino": 1
  }
}
```

## ❗ Exceptions

* `EmailAlreadyExistsException`: Email already registered.
* `InsufficientStockException`: Not enough stock for a coffee.
* `InsufficientMoneyException`: User does not have enough balance.

## ✅ How to Run

1. Clone the repository.
2. Configure PostgreSQL and credentials.
3. Run the application using your IDE or with:

```bash
./mvnw spring-boot:run
```

## 📌 Notes

* Data seeding happens only if the coffee table is empty.
* Email is used to identify users during purchase.
* The API ensures transactional consistency in purchases.
