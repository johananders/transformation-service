# Transformation Service
Transforms and persists text input

* [Api Documentation](https://transformation-service.herokuapp.com/swagger-ui.html)
* [Travis Ci](https://travis-ci.org/johananders/transformation-service)

## Architecture

* Application: Spring boot
* Db: Postgresql

## Deployment

### Local

#### Prerequisites

Docker, Oracle jdk 8, maven 3

#### Deploy

* Start postgresql: `$ docker run -e POSTGRES_PASSWORD=password -e POSTGRES_DB=wordsmith -p 5432:5432 postgres:10` 
* Build jar: `$ mvn clean install` 
* Run application: `$ java -jar -Dspring.profiles.active=dev target/transformation-{version}.jar` 
* Api docs <http://localhost:8080/swagger-ui.html>

### Production

#### Prerequisites

* Heroku account with app access
* heroku cli <https://devcenter.heroku.com/articles/heroku-cli?

#### Deploy

* Add heroku git remote ``
* Commit changes
* Push app (triggers deploy) `$ git push heroku master`
* More info <https://devcenter.heroku.com/categories/java>
* Logs can be viewed with `$ heroku logs`

### Testing

#### Prerequisites

Oracle jdk 8, maven 3

* Integration tests uses a h2 in-memory db
* Run all tests `$ mvn clean test`