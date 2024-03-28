FROM maven:3.8.1-openjdk-17-slim AS MAVEN_BUILD
MAINTAINER github.com/bzdgn
COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package


FROM openjdk:17.0.1-jdk-slim
MAINTAINER github.com/bzdgn
RUN mkdir /app
COPY --from=MAVEN_BUILD /build/target/camel-boot-polling-example-1.0-SNAPSHOT.jar /app/application.jar
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/application.jar"]

# docker build --tag=oep-poller:latest .
# docker run -p8080:8080 oep-poller:latest