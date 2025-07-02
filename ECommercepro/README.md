#  E-Commerce Backend System

A scalable, modular, and secure backend service built using **Java 21, Spring Boot 3.5.3** and **MongoDB**. 
This RESTful API powers core functionality of an online store, including user registration, authentication, 
product management, shopping cart, and order processing — with **Kafka-based event streaming**, **DTO validation**, 
and **robust exception handling**.

---

##  Tech Stack

| Layer               | Tech Used                              |
|---------------------|-----------------------------------------|
| Language            | Java 21                                 |
| Framework           | Spring Boot 3.5.3                       |
| Database            | MongoDB (NoSQL)                         |
| Security            | Spring Security (role-based)            |
| Messaging           | Apache Kafka (Basic producer setup)     |
| Validation          | Jakarta Bean Validation (DTO-level)     |
| Testing             | JUnit 5 + Mockito (service layer)       |
| Build Tool          | Maven                                   |
| Deployment          | Spring Boot JAR                         |

---

##  Core Modules

###  1. User Service
- Secure registration with email uniqueness check
- Role-based access (`ROLE_USER`)
- Auto timestamping using `LocalDateTime`
- Kafka event published on registration (`USER_REGISTERED`)

###  2. Product Service
- Add & fetch products (admin-oriented)
- Fields: name, description, price, stock, category
- Clean separation using DTOs

###  3. Cart Service
- Create/update a cart based on user ID
- Add/remove multiple items with quantity
- Automatic price calculation
- Clear cart functionality

###  4. Order Service
- Place orders from cart contents
- Deduct stock on order placement
- Handles insufficient stock & missing products
- Emits Kafka event (`ORDER_PLACED`)
- Supports cancel & view orders by user

---

##  Authentication & Security

- **Spring Security** integrated for authentication & role-based access
- Default role: `ROLE_USER`
- Password encryption (you should add `BCryptPasswordEncoder`)
- Extendable for `ROLE_ADMIN` features like product management

---

##  Kafka Integration

Basic Kafka publisher setup for:
- `USER_REGISTERED` topic
- `ORDER_PLACED` topic

➡ Kafka events include JSON payload with metadata like email, orderId, timestamps.

---

##  Testing

- **Unit Testing** for service layer using JUnit + Mockito
- Tests focus on:
    - Business logic (order placement, cart updates)
    - Repository interaction
    - Edge cases (e.g. insufficient stock, user not found)

---

##  API Overview

| Method | Endpoint                      | Description                       |
|--------|-------------------------------|-----------------------------------|
| POST   | `/api/users/register`         | Register new user                 |
| GET    | `/api/users/email/{email}`    | Fetch user by email               |
| GET    | `/api/products/`              | Get all products                  |
| POST   | `/api/products/`              | Add new product                   |
| POST   | `/api/cart/`                  | Create or update cart             |
| GET    | `/api/cart/{userId}`          | Get cart by user ID               |
| POST   | `/api/orders/`                | Place new order                   |
| GET    | `/api/orders/{userId}`        | Get orders by user                |
| PUT    | `/api/orders/cancel/{orderId}`| Cancel an order                   |

➡ All endpoints use clean DTOs for input validation.

---

##  DTO Validations

All user/product/cart/order DTOs are validated using Jakarta annotations:


* @NotBlank(message = "Name is required")
* @Min(value = 1, message = "Quantity must be at least 1")
* @Email(message = "Invalid email format")

---
# Folder Structure

src/main/java/com/example/ECommerce/
├── Controller/
├── Service/
├── Repository/
├── Model/
├── Dto/
├── Security/
├── Exception/
└── Config/
---

#  Configuration

### MongoDB

spring.data.mongodb.uri=mongodb://localhost:27017/ecommerce

### Kafka

* spring.kafka.bootstrap-servers=localhost:9092


* spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer


* spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer
---
## Run Locally

### Clone the repo
git clone https://github.com/<your-username>/ECommerceProject.git
cd ECommerceProject

### Build & run
mvn clean install
mvn spring-boot:run
---

# Author
### Hima Varsha
###### Backend Developer | Java | Spring Boot | MongoDB 
###### GitHub: @HimaVarsha01

---
# License

* This project is licensed under the MIT License — see the LICENSE file.
---
# Hire Me

* If you're hiring for a Backend Developer (Java, Spring Boot) role, I bring:

* 3 years of hands-on experience

* Production-grade API design

* Clean architecture & thorough documentation

* Expertise with Kafka, MongoDB, Security, and Unit Testing

##### Feel free to reach out via GitHub or email at hmodgula@gmail.com — I’d love to connect!

