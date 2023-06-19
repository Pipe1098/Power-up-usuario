<br />
<div align="center">
<h3 align="center">🔐PRAGMA POWER-UP_USERS👥 </h3>
  <p align="center">
    🌟 In this challenge you are going to design the backend of a system that centralizes the services and orders of a restaurant chain that has different branches in the city. This is the microservice in charge of managing the user database 🗄️.
  </p>
</div>

### Built With

* ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
* ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
* ![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
* ![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)


<!-- GETTING STARTED -->
## Getting Started 🚀

To get a local copy up and running follow these steps.

### Prerequisites 📋

* JDK 17 [https://jdk.java.net/java-se-ri/17](https://jdk.java.net/java-se-ri/17)
* Gradle [https://gradle.org/install/](https://gradle.org/install/)
* MySQL [https://dev.mysql.com/downloads/installer/](https://dev.mysql.com/downloads/installer/)

### Recommended Tools 🛠️
* IntelliJ Community [https://www.jetbrains.com/idea/download/](https://www.jetbrains.com/idea/download/)
* Postman [https://www.postman.com/downloads/](https://www.postman.com/downloads/)

### Installation 💻

1. Clone the repository
2. Change directory
   ```sh
   cd power-up-arquetipo-v3
   ```
3. Create a new database in MySQL called powerup
4. Update the database connection settings
   ```yml
   # src/main/resources/application-dev.yml
   spring:
      datasource:
          url: jdbc:mysql://localhost/powerup
          username: root
          password: <your-password>
   ```
5. After the tables are created execute src/main/resources/data.sql content to populate the database
6. Open Swagger UI and search the /auth/login endpoint and login with userDni: 123, password: 1234

<!-- USAGE -->
## Usage 📝🔧

1. Right-click the class PowerUpApplication and choose Run
2. Open [http://localhost:8090/swagger-ui/index.html](http://localhost:8090/swagger-ui/index.html) in your web browser

<!-- ROADMAP -->
## Tests 🧪🔬

- Right-click the test folder and choose Run tests with coverage

## How to Test the API
You can test the API using a tool like Postman or any REST client of your choice.

### Refresh Token: POST/auth/refresh 🔑🔄

Description: This endpoint is used to refresh an authentication token. When a user's token is about to expire, they can send a request to this endpoint with their current token to obtain a new token with an extended expiration time.

Example Request:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqb2huX2RvZSIsImlhdCI6MTYzMzI1NDkzMCwiZXhwIjoxNjMzMTQxMzMwfQ.2_5te1vQjDG-yTeBEOsmBAPdQHzCmCtojmYHltWkWbI"
}
```
Example Response:

```json
{
  "newToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqb2huX2RvZSIsImlhdCI6MTYzMzI1NTAwMCwiZXhwIjoxNjMzMTQzNjAwfQ.kLfJ8XdBRjN0W1iDJX6uH_hU6k7b1PmVy4QQD9HvQz4"
}
```
Explanation: The user includes their current token in the request. The server verifies the token's validity and issues a new token with an extended expiration time. The new token is then returned in the response.

###  User Login: POST /auth/login 🔐🔑

Description: This endpoint is used for user authentication and login. Users provide their credentials, such as username and password, to obtain an authentication token that they can use for subsequent requests.

Example Request:

```json
{
  "mail": "john_doe@gmail.com",
  "password": "my_password123"
}
```
Example Response:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqb2huX2RvZSIsImlhdCI6MTYzMzI1NTAwMCwiZXhwIjoxNjMzMTQzNjAwfQ.kLfJ8XdBRjN0W1iDJX6uH_hU6k7b1PmVy4QQD9HvQz4"
}
```
Explanation: The user provides their username and password in the request. The server verifies the credentials and, if they are valid, generates an authentication token. This token is then returned in the response and can be used for subsequent authenticated requests.

### Get role from token: GET /auth/role/{token}  🔐🔑👤

Description: This endpoint is used to retrieve the role of a user based on their authentication token. The server validates the token and returns the associated role information.

Example Request:

GET
/auth/role/eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqb2huX2RvZSIsImlhdCI6MTYzMzI1NTAwMCwiZXhwIjoxNjMzMTQzNjAwfQ.kLfJ8XdBRjN0W1iDJX6uH_hU6k7b1PmVy4QQD9Hv

Example Response:

```json
{
  "role": "ADMIN_ROLE"
}
```
### Get mail from token: GET /auth/mail/{token} 📧👤

Description: This endpoint is used to retrieve the email associated with a user's authentication token. The server validates the token and returns the email address linked to the token.

Example Request:

GET
/auth/mail/eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqb2huX2RvZSIsImlhdCI6MTYzMzI1NTAwMCwiZXhwIjoxNjMzMTQzNjAwfQ.kLfJ8XdBRjN0W1iDJX6uH_hU6k7b1PmVy4QQD9Hv

Example Response:

```json
{
  "mail": "john.doe@example.com"
}
```
Explanation: The server validates the provided authentication token and retrieves the email address associated with the user. The email address is then returned in the response.

### Get idRestaurant from token: GET /auth/idRestaurant/{token} 🏢🔑👨‍🍳

Description: This endpoint is used to retrieve the ID of a restaurant associated with a user's authentication token. The server validates the token and returns the restaurant ID linked to the token.

Example Request:

GET /auth/idRestaurant/eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqb2huX2RvZSIsImlhdCI6MTYzMzI1NTAwMCwiZXhwIjoxNjMzMTQzNjAwfQ.kLfJ8XdBRjN0W1iDJX6uH_hU6k7b1PmVy4QQD9HvQz4
Example Response:

```json
{
  "restaurantId": "123456"
}
```
Explanation: The server validates the provided authentication token and retrieves the ID of the restaurant associated with the user. The restaurant ID is then returned in the response.

### Add a New Owner 👤➕👨‍💼
Method: POST
Path: /api/v1/user/owner
Adds a new owner to the system.

Example Request:

```json
{
 "dniNumber": "4256901794",
  "name": "string",
  "surname": "string",
  "mail": "string",
  "phone": "+7701289",
  "birthdate": "2023-06-19",
  "password": "string",
  "role": "2",
  "idRestaurant": "string"
}
```
Example Response:

```json
{
  "message": "Owner added successfully"
}
```
### Add a New Employee 👤➕👨‍💼
Method: POST
Path: /api/v1/user/employee
Adds a new employee to the system.
Example Request:

```json
{
 "dniNumber": "2226901794",
  "name": "string",
  "surname": "string",
  "mail": "string",
  "phone": "+7701289",
  "birthdate": "2023-06-19",
  "password": "string",
  "role": "3",
  "idRestaurant": "1"
}
```
Example Response:

```json
{
  "message": "Employee added successfully"
}
```
### Add a New Client 👤➕👩‍💼
Method: POST
Path: /api/v1/user/client
Adds a new client to the system.

Example Request:

```json
{
 "dniNumber": "2226901794",
  "name": "string",
  "surname": "string",
  "mail": "string",
  "phone": "+7701289",
  "birthdate": "2023-06-19",
  "password": "string",
  "role": "4",
  "idRestaurant": "1"
}
```
Example Response:

```json
{
  "message": "Client added successfully"
}
```
### Get User by DNI Number 🔍📝
Method: GET
Path: /api/v1/user/{dniNumber}
Retrieves user information by the DNI number.

### Validate Owner ✅👤
Method: GET
Path: /api/v1/user/validate-owner
Validates if a user is an owner based on the provided token.

Example Request:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqb2huX2RvZSIsImlhdCI6MTYzMzI1NDkzMCwiZXhwIjoxNjMzMTQxMzMwfQ.2_5te1vQjDG-yTeBEOsmBAPdQHzCmCtojmYHltWkWbI"
}
```
Example Response:

```json
{
  "isOwner": true
}
```
### Get All Roles 📚🌟
Method: GET
Path: /role
Retrieves all the roles available in the system.

Example Response:

```json
[
  {
    "name": "admin",
    "description": "Administrator role"
  },
  {
    "name": "employee",
    "description": "Employee role"
  },
  {
    "name": "client",
    "description": "Client role"
  }
]
```
