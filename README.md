# Finance Manager Application

A full-stack microservices ecosystem for comprehensive personal finance management. Built with Spring Boot on the backend and Thymeleaf on the frontend, the system provides a secure, scalable architecture for tracking user identities, transactions, budgets, and automated notifications.

---

## Features

- Stateless authentication using JWT
- Microservices-based architecture with service-specific databases
- Real-time transaction tracking for income and expenses
- Monthly budget allocation and monitoring
- Automated email notifications for security and reporting
- Centralized exception handling and standardized API responses
- DTO & Mapper pattern implementation for secure data transfer
- Shared `common-lib` module for reusable components across services
- Server-side rendered UI with Thymeleaf

---

## How It Works

Users authenticate via the Auth Service. After successful registration, user identity (userId, email) is propagated to the User Service.

Each request carries a JWT token. Other services validate the token and extract the userId to process requests securely.

| Service              | Responsibility                                              |
|----------------------|-------------------------------------------------------------|
| Auth Service         | Handles authentication & JWT issuance (security gateway)   |
| User Service         | Stores and manages user profile metadata                    |
| Transaction Service  | Manages all financial records (income & expenses)           |
| Budget Service       | Calculates and maintains monthly budget constraints         |
| Email Service        | Handles SMTP integration for notifications and reports      |
| Frontend Service     | Consumer-facing UI using server-side rendering (Thymeleaf)  |
| Common Lib           | Shared DTOs, security, exceptions, and utilities            |

Each microservice has its own dedicated PostgreSQL instance, communicates via REST APIs, and handles its own exceptions — preventing cross-service data contamination.

---

## Tech Stack

| Layer          | Technology                        |
|----------------|-----------------------------------|
| Backend        | Java, Spring Boot, Spring MVC     |
| Frontend       | Thymeleaf (server-side rendering) |
| Security       | Spring Security, JWT (jjwt)       |
| Database       | PostgreSQL                        |
| Build Tool     | Maven                             |
| Architecture   | Microservices                     |
| Communication  | REST APIs                         |
| Containerization | Docker, Docker Compose          |

---

## Project Structure

```
finance-manager/
|
+-- common-lib/
|   \-- src/main/java/com/finance_manager/
|       +-- config/
|       +-- dto/
|       +-- exception/
|       +-- mapper/
|       +-- model/
|       +-- response/
|       +-- security/
|       +-- service/
|       \-- util/
|
+-- auth-service/
|   \-- src/main/java/com/finance_manager/auth_service/
|       +-- client/
|       +-- config/
|       +-- controller/
|       +-- dao/
|       +-- entity/
|       +-- mapper/
|       +-- repository/
|       \-- service/
|
+-- user-service/
|   \-- src/main/java/com/finance_manager/user_service/
|       +-- client/
|       +-- config/
|       +-- controller/
|       +-- dao/
|       +-- entity/
|       +-- mapper/
|       +-- repository/
|       \-- service/
|
+-- transaction-service/
|   \-- src/main/java/com/finance_manager/transaction_service/
|       +-- client/
|       +-- config/
|       +-- controller/
|       +-- dao/
|       +-- entity/
|       +-- mapper/
|       +-- repository/
|       \-- service/
|
+-- budget-service/
|   \-- src/main/java/com/finance_manager/budget_service/
|       +-- config/
|       +-- controller/
|       +-- dao/
|       +-- entity/
|       +-- mapper/
|       +-- repository/
|       \-- service/
|
+-- email-service/
|   \-- src/main/java/com/finance_manager/email_service/
|       +-- config/
|       +-- controller/
|       +-- dao/
|       +-- entity/
|       +-- mapper/
|       +-- repository/
|       \-- service/
|
\-- frontend-service/
    \-- src/main/
        +-- java/com/finance_manager/frontend_service/
        |   +-- client/
        |   +-- config/
        |   +-- controller/
        |   +-- exception/
        |   \-- service/
        \-- resources/
            \-- templates/
```

---

## API Endpoints

### Authentication
```
POST    /auth/signup                         Register a new user
POST    /auth/login                          Authenticate and retrieve a JWT
PUT     /auth/updatePassword                 Modify existing credentials
DELETE  /auth/deleteAuthUser                 Remove authentication identity
```

### User Management
```
GET     /user/getUser                        Retrieve user profile details
PUT     /user/editName                       Update profile information
DELETE  /user/deleteUser                     Remove user data
```

### Transactions
```
POST    /transaction/saveTransaction         Record a new financial entry
GET     /transaction/getAllTransactions      Retrieve full transaction history
GET     /transaction/getMonthlyTransactions  Fetch current month's transactions
DELETE  /transaction/deleteTransaction/{id}  Remove a specific transaction
```

### Budgets
```
POST    /budget/saveBudget                   Define a new budget
PATCH   /budget/editBudget                   Update existing budget parameters
GET     /budget/getAllBudgets                View all active budgets
DELETE  /budget/deleteBudget/{id}            Remove a specific budget
```

### Email Notifications
```
POST    /email/sendRegisterEmail             Trigger registration confirmation
POST    /email/sendLoginEmail                Issue login security alerts
POST    /email/sendMonthlySummaryEmail       Dispatch monthly financial reports
```

---

## Installation & Deployment

### Prerequisites
- Docker and Docker Compose
- Maven (for local builds)

### Automated Deployment (Docker) — Recommended

The system is configured for multi-container orchestration. To perform a clean installation and start all services:

```bash
docker compose down --rmi all --volumes --remove-orphans
docker compose up -d --build
```

### Manual Build & Run

```bash
git clone https://github.com/arnabsarma-29/finance-manager.git
cd finance-manager
mvn clean install
mvn spring-boot:run
```

Open in browser: `http://localhost:8080/`

---

## Key Concepts

- Microservices architecture with isolated databases per service
- Stateless JWT authentication propagated via token claims
- Shared `common-lib` for DTOs, security filters, exceptions, and utilities
- DTO & Mapper pattern for safe data transfer between layers
- Centralized exception handling with structured API responses
- REST API inter-service communication
- Server-side rendering via Thymeleaf

---

## Development Notes

- **Health Monitoring:** Spring Actuator endpoints are used for health checks. The Auth Service includes a pre-configured check to ensure availability within the container network.
- **Security:** All protected routes require a valid Bearer token. Security filters permit internal health checks while enforcing strict authorization on user-facing endpoints.
- **Data Isolation:** Each microservice uses its own dedicated PostgreSQL instance to prevent cross-service data contamination.
- **Configuration:** Set PostgreSQL credentials in each service's `application.properties`. JWT secret key must be configured before running.
- **Ports:** Each microservice runs independently on its own port.

---

## Author

[github.com/arnabsarma-29](https://github.com/arnabsarma-29)
