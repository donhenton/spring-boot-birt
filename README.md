# Spring Boot Birt

Application using Spring Boot to expose a rest service for the BIRT
database. This application is deployed to heroku at http://donhenton-spring-boot.herokuapp.com/

## Netbeans Specific

If built from the plugin a specific run time associate will be built see the 
nbactions.xml file. Select all the checkboxes when using the wizard.

## Building the App

This command builds the app and creates the react javascript app This is what
would be used to create the jar for heroku deploying

```
mvn clean package -DskipTests -Preact 
```


## Running Locally 

```
mvn clean spring-boot:run -DskipTests=true
```
see also the debugit.sh file

## Launching and Overriding properties

```
java  -Dspring.profiles.active=prod -Dserver.port=7777 -jar target/spring-boot-birt-0.0-SNAPSHOT.jar
```
Note that the order of the parameters is important
On heroku this command will be in the Procfile and the port number will be $PORT

In dev, DATABASE_URL in netbeans is set in the application-dev properites file,
and on heroku, its set as a system property by Heroku

With the advent of npm gulp build. runit.sh will run the dev and allow for 
reloading of the app via live reload

## Deploying to Heroku

For this app you must use the Heroku fat jar deploy. 

```
heroku deploy:jar target/spring-boot-birt-0.0.1-SNAPSHOT.jar --app donhenton-spring-boot
```

Or you can use the maven deploy:
* login onto heroku via the command line
* answer prompt on browser (requires upgrade to CLI > 22)
* mvn clean heroku:deploy -Preact -DskipTests


see https://devcenter.heroku.com/articles/deploying-executable-jar-files

## Database loading

Database instructions 
can be found at https://github.com/donhenton/dbscripts/blob/master/loading_to_heroku.md. For aws you can use
the procedure here: https://github.com/donhenton/postgres-sandbox/tree/master/jdatabase_backup

## Webjar project

see https://github.com/donhenton/webjar-app


## Application Location (Locally)

http://localhost:9000

## AWS Notes

### Docker Compose File
```yml
version: '3.1'
services:
    boot:
        container_name: boot
        ports:
            - "9000:9000"
        image: 235926060045.dkr.ecr.us-east-2.amazonaws.com/spring-boot-birt:latest
        environment:
            - DATABASE_URL=postgres://user:password@databaseDNS:5432/jdatabase
            - spring.profiles.active=aws
```
Unlike heroku the props files are in charge of specifying the port
