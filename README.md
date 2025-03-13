# Libraree - Timothy Theophilus Hartono

Libraree is a RESTful API built with **Spring Boot** and **MySQL**, designed to simplify library book borrowing processes. It includes features such as book and borrower management, book borrowing and returning, and overdue tracking.

## Features

- **Book Management**
    - Add book details (title, ISBN, stock availability).
- **Borrower Management**
    - Register borrowers with ID (KTP), name, and email.
- **Book Borrowing & Returning**
    - Borrow a book if stock is available.
    - Restrict borrowing if the book is out of stock.
    - Borrowers can set a return deadline.
    - Only one active borrowing per borrower at a time.
    - Maximum borrowing period of **30 days**.
    - Admin can track overdue books.

## Tech Stack

- **Java 21**
- **Spring Boot** (Spring Web, Spring Data JPA)
- **MySQL** (Database)
- **Flyway** (Database Migration)
- **Lombok** (Simplified Java code)
- **Docker** (Optional for containerization)

## Installation & Setup

### Prerequisites
Ensure you have the following installed:
- Java 17
- Maven
- MySQL

### Clone the Repository
```sh
 git clone https://github.com/timoothy21/libraree.git
 cd libraree
```

## Database Installation
1. **Create Database (Local)**  
   This Repository used MySQL, create new database for this project. </br>

    - Execute query below:
      ```sql
      CREATE DATABASE libraree;
      ```


## Configure the Database
Update `application.properties` with your MySQL credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/libraree
spring.datasource.username=root
spring.datasource.password=libraree 
```

### Run Database Migration
```sh
mvn flyway:migrate
```
or
```sh
validate flyway:migrate -Dflyway.configFiles=src/main/resources/application.properties
```
### Build & Run the Application
```sh
mvn spring-boot:run
```

## API Endpoints

| Method | Endpoint                     | Description |
|--------|------------------------------|-------------|
| POST   | `/api/v1/book`               | Add a new book |
| GET    | `/api/v1/book`               | Get all books |
| POST   | `/api/v1/user`               | Register a borrower or new user |
| POST   | `/api/v1/loan`               | Borrow a book |
| PUT    | `/api/v1/loan/{loanid}/return`| Return a book |
| GET    | `/api/v1/loan/status`         | Get overdue books |

## 📜 API Contract

### **Book Registration**
**Endpoint:** `POST /api/v1/book`

**Request Body:**
```json
{
  "isbn": "9780135957059",
  "title": "The Pragmatic Programmer",
  "stock": 1
}
```

**Response:**
- `201 Created`: Success
```json
{
  "isbn": "9780135957059",
  "title": "The Pragmatic Programmer",
  "stock": 1
}
```

**Error Responses:**
- `409 Conflic`: Book with ISBN {ISBN} already exists!
```json
{
    "code": 409,
    "message": "Error Message",
    "timestamp": "2025-03-13T11:26:17.002522"
}
```

### **Get All Book**
**Endpoint:** `GET /api/v1/book`

**Request Body:** -

**Response:**
- `200 Success`: Success
```json
{
    "books": [
        {
            "isbn": "9780135957059",
            "title": "The Pragmatic Programmer",
            "stock": 1
        }
    ]
}
```

### **User Registration**
**Endpoint:** `POST /api/v1/user`

**Request Body:**
```json
{
    "NIK": "327102303049001",
    "name": "Timothy",
    "email": "timothy@gmail.com"
}
```

**Response:**
- `201 Created`: Success
```json
{
    "NIK": "327102303049001",
    "name": "Timothy",
    "email": "timothy@gmail.com"
}
```

**Error Responses:**
- `409 Conflic`: User with NIK {NIK} already exists!
```json
{
    "code": 409,
    "message": "Error Message",
    "timestamp": "2025-03-13T11:31:09.768835"
}
```

### **Borrow a Book**
**Endpoint:** `POST /api/v1/loan`

**Request Body:**
```json
{
    "NIK": "327102303049001",
    "isbn": "9780135957059",
    "returnDate": "2025-03-25"
}
```

**Response:**
- `200 OK`: Success
```json
{
    "userName": "Timothy",
    "bookTitle": "The Pragmatic Programmer",
    "returnDate": "2025-03-25T23:59:59",
    "borrowedTime": "2025-03-13T11:32:34.870951",
    "expectedReturnTime": "2025-03-25T23:59:59"
}
```
**Error Responses:**
- `400 Bad Request`: User cannot borrow another book until User return the previous one
```json
{
    "code": 400,
    "message": "User cannot borrow another book until User return the previous one",
    "timestamp": "2025-03-13T11:48:20.573005"
}
```


### **Return Book**
**Endpoint:** `PUT /api/v1/loan/{loanid}/return`

**Request Body:**: -

**Response:**
- `200 OK`: Success
```json
{
    "loanId": "1",
    "bookTitle": "The Pragmatic Programmer",
    "borrowedTime": "2025-03-13T11:32:35",
    "actualReturnTime": "2025-03-13T11:50:06.794192",
    "expectedReturnTime": "2025-03-14T23:59:59",
    "status": "On Time"
}
```
**Error Responses:**
- `404 Not found`: Loan data not found
```json
{
    "code": 404,
    "message": "Loan data not found",
    "timestamp": "2025-03-13T11:50:37.454424"
}
```

### **Get Loan Status**
**Endpoint:** `GET /api/v1/loan/status`

**Request Body:**: -

**Response:**
- `200 OK`: Success
```json
{
    "loans": [
        {
            "loanId": "1",
            "userName": "Timothy",
            "bookTitle": "buku dua",
            "bookTitle": "The Pragmatic Programmer",
            "borrowedTime": "2025-03-13T11:32:35",
            "actualReturnTime": "2025-03-13T11:50:06.794192",
            "expectedReturnTime": "2025-03-14T23:59:59",
            "status": "On Time"
        }
    ]
}
```
**Status:**
- "On Time" → Returned within the due date.
- "Late Returned" → Returned, but after the due date.
- "On Loan" → Still borrowed, not yet returned.
- "Overdue" → Not returned and past the due date.

