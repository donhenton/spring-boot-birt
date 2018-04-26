# Spring Boot Birt

Application using Spring Boot to expose a rest service for the BIRT
database. This application is deployed to heroku at http://donhenton-spring-boot.herokuapp.com/

## Netbeans Specific

If built from the plugin a specific run time associate will be built see the nbactions.xml file. Select all the checkboxes when using the wizard.

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

## Deploying to Heroku

For this app you must use the Heroku fat jar deploy. 

```
heroku deploy:jar target/spring-boot-birt-0.0.1-SNAPSHOT.jar --app donhenton-spring-boot
```

see https://devcenter.heroku.com/articles/deploying-executable-jar-files

## Database loading

Database instructions 
can be found at https://github.com/donhenton/dbscripts/blob/master/loading_to_heroku.md

## Webjar project

see https://github.com/donhenton/webjar-app

## React-compile branch

This branch contains code for using this Spring Boot for react js development

## Application Location (Locally)

http://localhost:9000
