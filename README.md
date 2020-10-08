# Microservice OAuth

Microservice that allows users to authenticate from the authorization protocol OAuth2.

## Running Development

```sh
$ mvn spring-boot:run
```

### How to disable eureka client?

Modify the **enabled** property in st-microservice-oauth/src/main/resources/**application.yml** file:

```yml
eureka:
  client:
    enabled: false
```

### How to disable config client?

Modify the **enabled** property in st-microservice-oauth/src/main/resources/**bootstrap.yml** file:

```yml
spring:
  application:
    name: st-microservice-oauth
  cloud:
    config:
      enabled: false
```

## License

[Agencia de Implementación - BSF Swissphoto - INCIGE](https://github.com/AgenciaImplementacion/st-microservice-oauth/blob/master/LICENSE)