# Decrypto Challenge [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=santimanuelr_decryptoChallenge&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=santimanuelr_decryptoChallenge)
Challenge tecnico para decrypto 

## Objetivo:
El principal objetivo es construir una API REST con las operaciones CRUD
necesarias para gestionar el recurso Comitente, Mercado, País, y su
relacionamiento.

Como objetivos secundarios tenemos algunas otras consignas desafiantes para que
puedas demostrar tu experiencia y conocimientos.

## Consigna principal:
* No puede haber Comitentes repetidos.
* Un Comitente tiene Id y Descripción.
* Cada Comitente está relacionado con 1 o más Mercados, y un Mercado tiene
muchos comitentes.
* Un Mercado tiene Id, Código, Descripción, País.
* Los países admitidos son Argentina y Uruguay.
* De existir entidades complementarias deben estar pre-cargadas al
momento de usar la API.

## Domain

![Image of domain](https://github.com/santimanuelr/decryptoChallenge/blob/main/docs/diagram.jpg)

## [Development]
You can run the project with the command

```
mvn spring-boot:run
```

Run the test

```
mvn test
```

Once you start the project, you can visit [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) for see api documentation

### Nice to have

* Dockerize the app in order to be easier the deploys
* Improve errors message
* Add DTOs objets in order to avoid using the entities into the controllers
* Move some common code to Utils class
* Improve validations