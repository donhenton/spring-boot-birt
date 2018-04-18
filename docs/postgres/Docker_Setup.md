
https://hub.docker.com/r/sameersbn/postgresql/

The dblaunch script creates a jdatabase entry on port 
port is 5432

to shell in to psql on the docker instance:

docker exec -it postgresql sudo -u postgres psql
q to exit a commnand
\l to list
\q to exit
\du to list users
\d tables

psql is on the mac so logging into the docker instance using docker for the mac

to login from the outside of the container
psql -h localhost -U postgres as the superuser (defaults to the postgres db use -d to use another)


```
CREATE ROLE test with LOGIN PASSWORD 'test';
ALTER ROLE test CREATEDB;
CREATE DATABASE jdatabase;
GRANT ALL PRIVILEGES ON DATABASE jdatabase to test;
```


## LOADING THE BIRT DATABASE FROM FILES


the csv files need to be inside the docker container
-f will need to refer to a folder INSIDE the docker instance.

copy datafiles to docker instance: 


* docker exec -t -i postgresql /bin/bash (enter the docker instance)
* create directory /var/loaddata/datafiles set permissions to 777
* docker cp datafiles/. postgresql:/var/loaddata/datafiles (copy from outside to docker be positioned in this folder)

outside of the docker instance in this folder run this

psql -h localhost -d jdatabase -U postgres -a -q -f /Users/&lt;username&gt;/NetBeansProjects/spring-boot-birt/docs/postgres/birtload.sql
