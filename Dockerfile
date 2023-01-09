# RedHat Linux distro with Java 11 runtime
# FROM adoptopenjdk/openjdk11:alpine-jre
FROM registry.access.redhat.com/ubi8/openjdk-11-runtime:1.14-6.1666624577

USER root

RUN microdnf install yum

# Copy war file
COPY docker/fopengine-1.0.0-SNAPSHOT.jar /api.war
# COPY docker/fontconfig-2.14.1-2.fc38.x86_64.rpm /src/fontconfig-2.14.1-2.fc38.x86_64.rpm
# RUN rpm -i /src/fontconfig-2.14.1-2.fc38.x86_64.rpm

RUN yum install -y fontconfig 

USER jboss

COPY src/main/resources/fop.xconf.xml /src/main/resources/fop.xconf.xml
COPY src/main/resources/templates/* /src/main/resources/templates/

# run the app
CMD ["java", "-jar", "/api.war"]
