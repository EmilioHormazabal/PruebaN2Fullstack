# Hospital V&M

Bienvenido al proyecto **Hospital V&M**, una API REST desarrollada en Java con Spring Boot y Maven para la gestión hospitalaria. Este sistema permite administrar pacientes, médicos, atenciones, especialidades, previsiones y estados, facilitando la organización y consulta de información relevante en un entorno hospitalario.

## Tecnologías utilizadas

- Java 17+
- Spring Boot
- Maven
- JPA/Hibernate
- SQL Server

## Configuración inicial

1. Clona este repositorio en tu equipo.
2. Configura la conexión a la base de datos en `src/main/resources/application.properties`:
   - Ajusta el usuario, contraseña y URL según tu entorno.
3. Ejecuta el proyecto:
   - Desde terminal:
     ```
     ./mvnw spring-boot:run
     ```
     o
     ```
     mvn spring-boot:run
     ```
   - Desde IntelliJ IDEA: ejecuta la clase `HospitalApplication`.

## Endpoints principales

- `/api/v1/atenciones`  
  CRUD y consultas de atenciones médicas.
- `/api/v1/especialidades`  
  Gestión de especialidades médicas.
- Otros endpoints disponibles para pacientes, médicos, previsiones y estados.

### Ejemplo de uso

Para consumir la API desde Postman, recuerda agregar el siguiente header en cada petición: