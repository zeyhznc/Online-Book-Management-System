FROM openjdk:8-jdk-alpine
ADD target/my-online-book-management-project-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]