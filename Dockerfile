FROM openjdk:11
MAINTAINER qnbefinans.com
COPY /target/customerapp-0.0.1-SNAPSHOT.jar customer-app.jar
ENTRYPOINT ["java","-jar","customer-app.jar"]