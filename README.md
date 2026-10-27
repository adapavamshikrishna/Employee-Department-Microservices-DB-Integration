# Employee-Department-Microservices

## Project Description
A Java Spring Boot backend that provides REST APIs to manage employees and departments.

## Technologies
- Java 17
- Spring Boot
- Spring Web (REST APIs)
- Spring Data JPA and Hibernate
- Bean Validation
- MySQL or H2
- Maven
- Postman for API testing

## Modules
- **Employee:** Create, view, update, and delete employees. Each employee belongs to a department.
- **Department:** Create, view, update, and delete departments.
- **Exception handling:** Returns clear responses for missing records and invalid input.

## Database
The project has two profiles:
- `mysql`: persistent local MySQL database.
- `h2`: in-memory database for testing.

By default, `application.properties` activates the `mysql` profile. Create the database first:
```sql
CREATE DATABASE company_db;
```
Configure the MySQL user and password using environment variables `DB_USERNAME` and `DB_PASSWORD`. For local testing with H2, change `spring.profiles.active=mysql` to `spring.profiles.active=h2` in `application.properties`.

## Run
1. Install JDK 17+ and ensure MySQL is running if using MySQL.
2. Create `company_db` in MySQL.
3. Set `DB_USERNAME` and `DB_PASSWORD` environment variables, or edit the local configuration (do not commit passwords).
4. In STS, choose **Maven → Update Project**.
5. Run `CompanyApplication.java` as a Spring Boot App.
6. Use Postman with base URL `http://localhost:8080`.

## API Endpoints

### Departments
- `GET /api/departments`
- `GET /api/departments/{id}`
- `POST /api/departments`
- `PUT /api/departments/{id}`
- `DELETE /api/departments/{id}`

### Employees
- `GET /api/employees`
- `GET /api/employees/{id}`
- `POST /api/employees`
- `PUT /api/employees/{id}`
- `DELETE /api/employees/{id}`

### Example request bodies

Create a department:
```json
{
  "name": "Engineering",
  "description": "Software development"
}
```

Create an employee (create the department first and use its ID):
```json
{
  "firstName": "Asha",
  "lastName": "Rao",
  "email": "asha@example.com",
  "salary": 50000,
  "department": {
    "id": 1
  }
}
```

## Note
This project uses a single Spring Boot application with separate Employee and Department modules. They are not independently deployed microservices.
