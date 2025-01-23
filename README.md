# Coffee Machine

## Description
Coffee Machine is a Spring Boot application designed to manage coffee machine operations. It leverages Spring Boot, Spring Data JPA, Spring Cloud OpenFeign, and PostgreSQL.

## Requirements
- Java 17
- Maven
- PostgreSQL

## Setup

### Database
1. Install PostgreSQL.
2. Create a database named `coffeemachine`.
3. Update the `src/main/resources/application.yml` file with your PostgreSQL credentials if they differ from the default.

### Build and Run
1. Clone the repository:
    ```sh
    git clone https://github.com/danniyeres/coffee-machine.git
    cd coffee-machine
    ```
2. Build the project using Maven:
    ```sh
    mvn clean install
    ```
3. Run the application:
    ```sh
    mvn spring-boot:run
    ```

## Configuration
The application configuration is managed in the `src/main/resources/application.yml` file. Key configurations include:
- Database connection
- JPA settings
- SpringDoc API documentation path
- Server port

## Dependencies
The project uses the following dependencies:
- Spring Boot Starter Data JPA
- Spring Boot Starter Web
- Spring Cloud Starter OpenFeign
- PostgreSQL JDBC Driver
- Lombok
- Spring Boot Starter Test
- SpringDoc OpenAPI Starter WebMVC UI

## License
This project is licensed under the MIT License.

## Authors
- Daniyar Yeleussiz