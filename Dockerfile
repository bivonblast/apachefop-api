# Alpine Linux with OpenJDK JRE
FROM adoptopenjdk/openjdk11:alpine-jre

# Copy war file
COPY docker/fopengine-1.0.0-SNAPSHOT.jar /api.war
COPY src/main/resources/* /src/main/resources/

# run the app
CMD ["java", "-jar", "/api.war"]
