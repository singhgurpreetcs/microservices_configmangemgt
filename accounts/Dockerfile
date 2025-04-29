#Start with a base image containing Java runtime
FROM openjdk:21-jdk-slim

#Information about who maintains the image
MAINTAINER gurpreet.cs.tsys@gmail.com

#Add the application jar to the image
COPY build/libs/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#Run the application
ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]



