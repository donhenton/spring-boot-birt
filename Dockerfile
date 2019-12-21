FROM frolvlad/alpine-oraclejre8:8.202.08-full
VOLUME /tmp
ADD ./target/spring-boot-birt-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9000
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]


 