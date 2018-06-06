# JEE-API-Sample

My sample Java EE 7 template

 * Java EE 7 Api
 * Liquibase
 * Swagger-UI
 * Wildfly
 * MariaDB

### Prerequisites

1. MariaDB 10.1.31 (I used XAMP) - make sure login is `root` and password is `empty` yes i know i am lazy
2. Apache Maven

### Installing

1. Clone repo.
2. Go to cloned directory.
3. Run command.

```
$ mvn clean install
```

4. Wait until process finish.
5. Open browser and go to http://localhost:8080
6. Login as `admin` password is `admin.1234`
7. Click 'Deployments'
    7a. Click 'Add' add select 'Upload a new deployment' and click 'Next'
    7b. Select builded our war file (javaeeapi-sample.war) and click 'Next'
    7c. Click 'Finish'

Right now we deployed aplication.

8. To check if aplication is working go to http://localhost:8080/javaeeapi-sample/swagger-ui/
