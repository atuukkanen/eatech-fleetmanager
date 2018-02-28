# fleetmanager
Backend for a fleet manager application. Supports adding, fetching, modifying and removing cars.

## Running The Application
Easiest way is `docker-compose up`, which takes care of required environmental variables and deploying a mySQL database.

When running without the given `docker-compose.yml`, one should have following environmental variables defined (with example values):
- DATABASE_URL (jdbc:mysql://localhost:3306/fleetmanager)
- DATABASE_USERNAME (fleetuser)
- DATABASE_PASSWORD (fleetpassword)

In IntelliJ IDEA one can specify environmental variables via Run -> Edit configurations...

## How To Test

### Swagger Documentation
The application has Swagger documentation available at `host:8080/swagger-ui.html` and https://fleet.segfault.fi/swagger-ui.html

### Postman
Postman collection of sample requests is available via following link: https://www.getpostman.com/collections/22b0179bc94e610f5752
