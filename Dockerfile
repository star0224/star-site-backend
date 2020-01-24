FROM openjdk:13

COPY target/star-site-backend-1.3.0-SNAPSHOT.jar star-site-backend-1.3.0-SNAPSHOT.jar

RUN bash -c "touch /star-site-backend-1.3.0-SNAPSHOT.jar"

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "star-site-backend-1.3.0-SNAPSHOT.jar"]