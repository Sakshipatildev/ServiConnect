# ServiConnect
Java full-stack service booking starter project.

Stack: HTML, CSS, JavaScript, Java 21+, Spring Boot 3.5.7, Maven, Microsoft SQL Server.

## Setup
1. Run `database/schema.sql` in SSMS.
2. Edit `backend/src/main/resources/application.properties` with your SQL Server credentials.
3. Open a terminal in `backend` and run `mvn spring-boot:run` (or `mvnw.cmd spring-boot:run` if you add/use Maven Wrapper).
4. Open `frontend/index.html` using VS Code Live Server.

Backend URL: http://localhost:8080
API health check: http://localhost:8080/api/health

This is a learning MVP, not production-ready. Add Spring Security/JWT and authorization before public deployment.
