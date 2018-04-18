# Transformation Service
Transforms and persists text

* [ApiDocs](https://xxx.xxx/swagger-ui.html)
* [TravisCi](https://travis-ci.org/johananders/transformation-service)

## Deployment

### Local
#### Prerequisites
Docker, Oracle jdk 8, maven 3
#### Run
* Start postgresql: `$ docker run -e POSTGRES_PASSWORD=password -e POSTGRES_DB=wordsmith -p 5432:5432 postgres:10`
* 
* Build jar: `cd <project_root> && mvn clean install` 
* Run application: `java -jar -Dspring.profiles.active=dev target/transformation-{version}.jar` 

### Production
#### Prerequisites

* Heroku account with access to production environment (contact IT), heroku cli `https://devcenter.heroku.com/articles/heroku-cli`
