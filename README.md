# PDF Generation API with Spring Boot

## Overview
This project is a **Spring Boot REST API** that generates **PDF documents** based on student information. It uses **Thymeleaf** for HTML templates and **Flying Saucer (iTextRenderer)** to convert them into PDFs. The generated PDFs are stored in the `D:/GeneratedPDFs/` directory and can be downloaded through the API.

## Features
- Fetch student data from the database
- Generate PDF reports using Thymeleaf templates
- Store PDFs in `D:/GeneratedPDFs/`
- Serve PDFs via REST API for download

## Technologies Used
- **Spring Boot** (REST API framework)
- **Spring Data JPA** (Database access)
- **Thymeleaf** (Template engine for HTML to PDF conversion)
- **Flying Saucer (iTextRenderer)** (PDF generation)
- **H2 / MySQL / PostgreSQL** (Database, configurable)

## Installation & Setup

### Prerequisites
- **Java 17+**
- **Maven**
- **Spring Boot**
- **Database** (H2, MySQL, or PostgreSQL)

### Steps to Run
1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-repo/pdf-demo.git
   cd pdf-demo
   ```
2. **Configure Database:**
   - Update `application.properties` in `src/main/resources/` with your database credentials.
   - Example for MySQL:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/pdf_demo
     spring.datasource.username=root
     spring.datasource.password=password
     spring.jpa.hibernate.ddl-auto=update
     ```
3. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

## API Endpoints
### Generate and Download PDF
- **Endpoint:** `GET /student/{studentId}`
- **Description:** Fetches student details and generates a downloadable PDF.
- **Response:** A PDF file as an attachment.
- **Example Request:**
  ```bash
  curl -X GET http://localhost:8080/student/1 -o Student_1.pdf
  ```

## File Storage Location
- All PDFs are stored in: `D:/GeneratedPDFs/`
- Ensure this directory exists and has **write permissions**.

## Dependencies
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.thymeleaf</groupId>
        <artifactId>thymeleaf</artifactId>
    </dependency>
    <dependency>
        <groupId>org.xhtmlrenderer</groupId>
        <artifactId>flying-saucer-pdf</artifactId>
        <version>9.1.22</version>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

## Notes
- If running in Windows, ensure your application has permission to access `D:/`.
- If facing issues with PDF generation, check if the **Thymeleaf templates** exist in `src/main/resources/templates/`.

## Author
- **Your Name**  
- **Email:** your-email@example.com

## License
This project is licensed under the **MIT License**. Feel free to modify and distribute.

