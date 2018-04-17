# Transformation Service

Transforms text

## Architecture

### Dev (local)

* http://localhost:8080
* postgres 9.6

### Production

* https//xxx.xxx (heroku)
* postgres 9.6 (heroku)
* api documentation https//xxx.xxx/swagger-ui.html 

### Local

* http://localhost:8080
* api documentation http://localhost:8080/swagger-ui.html

## Deployment

### Local

#### Prerequisites

* Docker
* Oracle jdk 8
* maven 3

- Start postgresql: `$ docker run -e POSTGRES_PASSWORD=password -e POSTGRES_DB=wordsmith -p 5432:5432 postgres:10`
* 
* Build jar: `cd <project_root> && mvn clean install` 
* Run application: `java -jar -Dspring.profiles.active=dev target/transformation-{version}.jar`
* 

## Releasing

