FROM openjdk:13

COPY target/star-site-backend-1.1.0-SNAPSHOT.jar star-site-backend-1.1.0-SNAPSHOT.jar

RUN bash -c "touch /star-site-backend-1.0.0-SNAPSHOT.jar"

EXPOSE 8088

ENTRYPOINT ["java", "-jar", "star-site-backend-1.1.0-SNAPSHOT.jar"]