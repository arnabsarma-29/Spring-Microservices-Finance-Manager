# Finance Manager Application

A backend-focused microservices-based finance management system built using Spring Boot for managing authentication, transactions, budgets, and notifications.

---

## Features

- User registration and login with JWT authentication
- Microservices-based architecture
- User profile management
- Transaction tracking (income & expenses)
- Monthly budget management
- Email notification logging
- Centralized exception handling
- DTO & Mapper pattern implementation
- Structured API responses
- Stateless authentication using JWT

---

## How It Works

Users authenticate via the Auth Service. After successful registration, user identity (userId, email) is propagated to the User Service.
Each request carries a JWT token. Other services validate the token and extract the userId to process requests securely.

**Auth Service** handles authentication & JWT
**User Service** stores profile data
**Transaction Service** manages financial records
**Budget Service** manages budgets
**Email Service** logs notifications

Each microservice has its own database, communicates via REST APIs, and handles its own exceptions.

---

## Tech Stack

- **Backend:** Java, Spring Boot, Spring MVC
- **Security:** Spring Security, JWT
- **Database:** PostgreSQL
- **Build Tool:** Maven
- **Architecture:** Microservices
- **Communication:** REST APIs
- **JWT Library:** jjwt

---

## Project Structure

```
src/main/java/com/project/finance_manager/
    config/
    controller/
    service/
    repository/
    dao/
    entity/
    model/
    dto/
    exception/

Main Classes:
    FinanceManagerApplication.java
    ServletInitializer.java
```

---

## Authentication Endpoints

```
POST    /auth/register                  - Register user
POST    /auth/login                     - Login user and receive JWT
```

---

## User & Profile Endpoints

```
GET     /users/profile                  - Get user profile
POST    /users/updateProfile            - Update user profile
POST    /users/deleteUser               - Delete user
```

---

## Transaction Endpoints

```
GET     /transactions/getTransactions   - Get all transactions
GET     /transactions/saveTransaction   - Show transaction form
POST    /transactions/saveTransaction   - Save new transaction
POST    /transactions/deleteTransaction - Delete transaction
```

---

## Budget Endpoints

```
GET     /budgets/getBudgets             - Get all budgets
GET     /budgets/saveBudget             - Show budget form
POST    /budgets/saveBudget             - Save new budget
POST    /budgets/deleteBudget           - Delete budget
```

---

## Email / Notification Endpoints

```
POST    /email/sendNotification         - Log and send email notification
GET     /email/getLogs                  - View notification logs
```

---

## Installation

```bash
git clone https://github.com/arnabsarma-29/finance-manager.git
cd finance-manager
mvn clean install
```

---

## Run Application

```bash
mvn spring-boot:run
```

Open in browser:
http://localhost:8080/

---

## Key Concepts

- Microservices architecture
- Stateless JWT authentication
- Spring Security
- DTO & Mapper pattern
- Centralized exception handling
- REST API communication
- Layered architecture

---

## Notes

- Configure PostgreSQL in each service's application.properties
- JWT secret key must be set before running
- Each microservice runs independently on its own port
- All protected routes require a valid Bearer token
- User identity is propagated across services via JWT claims

---

## Author

https://github.com/arnabsarma-29
