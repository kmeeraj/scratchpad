
FROM openjdk:11-jdk
EXPOSE 80
COPY  target/AppServer-0.0.1-SNAPSHOT.jar scratchpad.jar
CMD ["java","-jar", "scratchpad.jar"]
