# CarShop

This is a CarShop Restful Service.

## Local use

Pre-requirement: Have Postgres 12 or higher installed and make it accessible in port 5436.

### Database

#### Local

##### Creating a database
Run in terminal: `createdb -h localhost -p 5436 -U root carshop`

Run as SQL
```sql
CREATE DATABASE carshop;
```

##### Create a User
Run as SQL
```sql
CREATE USER carshop WITH PASSWORD 'carshop';
```

##### Grant Permissions
Run in SQL
```sql
ALTER DATABASE carshop OWNER TO carshop;
GRANT ALL PRIVILEGES ON DATABASE carshop TO carshop;
GRANT ALL PRIVILEGES ON SCHEMA public TO carshop;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO carshop;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO carshop;
```

#### Docker
Imagine can be found at `doc/scripts/database`

###### Run Docker Dockerfile
docker image build -t carshop-db .

###### Run Docker compose 
docker-compose up

###### Accessing Container's bash
docker exec -ti carshop-server /bin/bash

### Running application (one of the following)

#### Pre-requirement: 
- Have Java 13+ installed;

#### Run Options
1 - in the maven sub-module `server` run: mvn spring-boot:run
    
2 - run as java application from the maven sub-module `server`: Application.java

##### Lombok

This is a plugin to help avoid boiler plate in the code. 

Site: https://projectlombok.org/

Git: https://github.com/mplushnikov/lombok-intellij-plugin#installation

###### Installation

Make sure to have lombok properly installed as showed in the github. There are some configurations to be done in the IDE.

##### Docker

###### Local

####### Run Docker Dockerfile
docker image build -t carshop-server .

####### Run Docker compose 
docker-compose up

####### Accessing Container's bash
docker exec -ti carshop-server /bin/bash

## Using the Application
- Download [Postman](https://www.getpostman.com/);
- Import Postman collections from `~/doc/api/RES-DOC-ApiCalls.postman_collection.json`;
- Configure your hostname for where the service is running;
- Use the Valid and Invalid calls to use the system.

## Testing the system
- In the root folder run `mvn test` and it will run all available tests;

### Maven 

#### Running all tests
mvn test

#### Running integration tests
mvn test-compile failsafe:integration-test failsafe:verify

#### Running carshop with Java
In the server folder run:
- `mvn clean install`

In the same server folder run:
- `java -jar -Dspring.profiles.active=local target/server-1.0-SNAPSHOT.jar`

#### Running carshop with Maven
In the server folder run:
- `mvn clean spring-boot:run -Dspring-boot.run.profiles=local`
