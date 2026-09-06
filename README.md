# Spring Boot Banking Application

A RESTful banking application built using Spring Boot for managing bank accounts and performing account-related operations.

## Features

- Create a new bank account
- Retrieve all bank accounts
- Retrieve an account using account holder name and account number
- Deposit money into an account
- Withdraw money from an account
- Delete a bank account
- Input validation for account details
- Custom exception handling
- Centralized exception handling
- MySQL database integration
- Swagger UI for API documentation and testing

## Technologies Used

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- REST API
- Swagger UI
- Maven
- Lombok

## Project Structure

```text
src/main/java/com/dinesh/banking/
├── controller
├── exception
├── model
├── repository
├── service
├── serviceimpl
└── BankingApplication.java


## Screenshots

### Swagger UI - All Endpoints

![Swagger UI](screenshots/swagger-ui.png)

### Create Account API

![Create Account](screenshots/create-account.png)

### Deposit API

![Deposit](screenshots/deposit.png)

### Withdraw API

![Withdraw](screenshots/withdraw.png)

### Delete Account API

![Delete Account](screenshots/delete-account.png)


## API Testing

The REST APIs were tested using:

- Postman
- Swagger UI
