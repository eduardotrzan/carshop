# CarShop

This is a CarShop Restful Service.

## 1. For Running Application Locally ONLY

Pre-requirement: Have Postgres 12 or higher installed and make it accessible in port 5436.

### 1.1 Database

#### Creating a database

Run in terminal: 
```bash
createdb -h localhost -p 5436 -U root carshop
```

Run as SQL
```sql
CREATE DATABASE carshop;
```

#### Create a User

Run as SQL
```sql
CREATE USER carshop WITH PASSWORD 'carshop';
```

#### Grant Permissions

Run as SQL
```sql
ALTER USER carshop WITH SUPERUSER;
ALTER DATABASE carshop OWNER TO carshop;
GRANT ALL PRIVILEGES ON DATABASE carshop TO carshop;
GRANT ALL PRIVILEGES ON SCHEMA public TO carshop;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO carshop;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO carshop;
```

### 1.2 Application

#### Pre-requirement: 
- Have Java 13+ installed;
- Have maven 3.6.1+ installed;

#### Lombok

This is a plugin to help avoid boiler plate in the code. 

Site: https://projectlombok.org/

Git: https://github.com/mplushnikov/lombok-intellij-plugin#installation

##### Installation

Make sure to have lombok properly installed as showed in the github. There are some configurations to be done in the IDE.

#### Run Options
1 - As java application from the maven sub-module `carshop-server`: 
```
Application.java
```
The default profile is `local`


2 - As maven sub-module `server` run: 

```bash
mvn spring-boot:run
```

3 - As java jar:
```bash
java -jar -Dspring.profiles.active=local server.jar
``` 


## 2. For Running Docker ONLY

### 2.1 Database
Imagine can be found at `doc/scripts/database`

#### Run Docker Dockerfile
```bash
docker image build -t carshop-db .
```

#### Run Docker compose 
```bash
docker-compose up
```

#### Accessing Container's bash
```bash
docker exec -ti carshop-server /bin/bash
```


### 2.2 Application
Make sure to be in the root folder
Always make sure to build application before creating image `mvn clean install`

#### Run Docker Dockerfile
```bash
docker image build -t carshop-server .
```

#### Run Docker compose 
```bash
docker-compose up
```


## 3. Using the Application
- Download [Postman](https://www.getpostman.com/);
- Import Postman collections from `~/doc/api/carshop.postman_collection.json`;
- Import Postman environment from `~/doc/api/CarShop.postman_environment.json`;
- Use the Valid and Invalid calls to use the system.


## 4. Tips
### Lazy Full Docker version
Navigate to root folder first (i.e. **< path >/carshop**).

Run compact docker command on terminal:
```bash
cd doc/scripts/database && docker image build -t carshop-db . && cd ../../../ && mvn clean -T5C install -DskipTests=true -DskipITs -Dcheckstyle.skip=true -Dmaven.test.skip=true && docker image build -t carshop-server . && docker-compose up
``` 
The command above will build DB docker, build app and build server docker


### Cache Issues
Case images get cached and run the following command:
```bash
docker stop $(docker ps -a -q) && docker rm $(docker ps -a -q) && docker rmi $(docker images -a -q)
```
The command above will stop all running dockers and wipe clean all images you current have.

Note: if you don't wanna loose some image, don't run the comment, but do it for the image/container names above.

### Accessing Container's bash
```bash
docker exec -ti carshop-server /bin/bash
```
