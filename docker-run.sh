#--detach exits the console, but leaves the instance
#--rm removes the docker instance after exit
#on the mac ip of local machine is from ifconfig
docker run -e spring.profiles.active=aws  \
-e DATABASE_URL=postgres://test:test@<hostname>:5432/jdatabase \
--name=spring-boot-birt -p 9000:9000 -d  spring-boot-birt 