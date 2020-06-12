# kaffeeplan-backend project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `kaffeeplan-backend-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/kaffeeplan-backend-1.0.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/kaffeeplan-backend-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.


## Endpoints
### Application specific endpoints
All application specific endpoints support `Content-Type: application/json` and `Content-Type: application/hal+json` headers.
* GET http://localhost:8080/weeks
* POST http://localhost:8080/weeks/
* GET http://localhost:8080/weeks/{id}
* PUT http://localhost:8080/weeks/{id}
* DELETE http://localhost:8080/weeks/{id}

### OpenAPI Specification:

http://localhost:8080/openapi

### Swagger-UI
If you run this application in dev mode, the Swagger-UI is available under:

http://localhost:8080/swagger-ui


