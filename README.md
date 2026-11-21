# Spring Boot Global Validation Error Handling

## Aim
Implement a `@ControllerAdvice` class to handle validation errors globally in a Spring Boot REST API.

## Technologies
Java 17+, Spring Boot 3.5.7, Spring Web, Spring Validation, Maven.

## Features
- Global exception handling using `@RestControllerAdvice`.
- Handles `MethodArgumentNotValidException`.
- Returns structured JSON with timestamp, status, message and field errors.
- Uses `ResponseEntity` with HTTP 400 for invalid input.
- Includes valid and invalid request tests.

## Project structure
```text
src/main/java/com/example/validationhandling/
  ValidationHandlingApplication.java
  controller/UserController.java
  dto/UserRequest.java
  dto/ApiErrorResponse.java
  exception/GlobalExceptionHandler.java
src/main/resources/application.properties
src/test/java/com/example/validationhandling/GlobalValidationTest.java
pom.xml
README.md
```

## Run in IntelliJ IDEA
1. Extract the ZIP.
2. Open the folder containing `pom.xml` in IntelliJ IDEA.
3. Set Project SDK to Java 17 or later and wait for Maven dependencies.
4. Run `ValidationHandlingApplication.java`.
5. The server starts at `http://localhost:8080`.

## Run using terminal
```bash
mvn clean install
mvn spring-boot:run
```

## API
POST `http://localhost:8080/api/users`
Content-Type: `application/json`

### Valid JSON (expect HTTP 201 Created)
```json
{
  "name": "Prajwal",
  "email": "prajwal@example.com",
  "age": 22,
  "password": "password123"
}
```

### Invalid JSON (expect HTTP 400 Bad Request)
```json
{
  "name": "",
  "email": "wrong-email",
  "age": 15,
  "password": "123"
}
```

Example error response (timestamp varies):
```json
{
  "timestamp": "2026-11-21T10:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed. Please check the field errors.",
  "fieldErrors": {
    "name": "Name is required",
    "email": "Please provide a valid email address",
    "age": "Age must be at least 18",
    "password": "Password must contain at least 8 characters"
  }
}
```

## Test with curl
```bash
curl -X POST http://localhost:8080/api/users -H "Content-Type: application/json" -d "{\"name\":\"\",\"email\":\"wrong-email\",\"age\":15,\"password\":\"123\"}"
```

## Run automated tests
```bash
mvn test
```

## Important annotations
- `@RestControllerAdvice`: handles exceptions across REST controllers.
- `@ExceptionHandler`: defines which exception a method handles.
- `@Valid`: triggers validation of the request body.
- `@NotBlank`, `@Email`, `@Min`, `@Max`, `@Size`: validation rules.
- `ResponseEntity`: returns response body with HTTP status.

## GitHub commands
Create an empty GitHub repository, then execute in the project directory:
```bash
git init
git add .
git commit -m "Implement global validation error handling"
git branch -M main
git remote add origin https://github.com/YOUR-USERNAME/YOUR-REPOSITORY.git
git push -u origin main
```
If origin already exists:
```bash
git remote set-url origin https://github.com/YOUR-USERNAME/YOUR-REPOSITORY.git
git push -u origin main
```

## Result
Global validation error handling is implemented. Invalid input returns structured field-wise errors with HTTP 400; valid input returns HTTP 201.
