# Course Cluster

A course management system built with Spring Boot and Java.

## Prerequisites

- Java 17 or higher
- Maven 3.6.0 or higher
- MySQL 8.0 or higher (or your preferred database)
- Git (for cloning the repository)

## Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/Course-Cluster.git
cd Course-Cluster
```

### 2. Configure Database
1. Create a MySQL database named `course_cluster`
2. Update the database configuration in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/course_cluster?useSSL=false&serverTimezone=UTC
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

### 3. Build the Application
```bash
mvn clean install
```

### 4. Run the Application
```bash
mvn spring-boot:run
```

The application will be available at: http://localhost:8080

## API Documentation
Once the application is running, you can access the API documentation at:
- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/example/CourseCluster/
│   │       ├── controller/    # REST controllers
│   │       ├── service/       # Business logic
│   │       ├── repository/    # Data access
│   │       ├── entity/        # JPA entities
│   │       └── CourseClusterApplication.java
│   └── resources/
│       ├── static/           # Static resources
│       ├── templates/        # Template files
│       └── application.properties
└── test/                     # Test files
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
