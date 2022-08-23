FROM maven:3.5-jdk-11 AS build


COPY src /usr/src/app/src
COPY pom.xml /usr/src/app

RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests=true



FROM openjdk:11-jre-slim

EXPOSE 8080

RUN mkdir /app

COPY --from=build /usr/src/app/target/spring-base2022-0.0.1-SNAPSHOT.jar /app/spring-boot-application.jar

ENTRYPOINT ["java",  "-Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-boot-application.jar"]

#docker build -t maxiplux/io.api.base .


#docker tag  3501cab4af42 maxiplux/livemarket.business.b2bcart:1.0.5
#docker tag  39d440f82330 maxiplux/livemarket.business.b2bcart:kuerbernetes
#docker push maxiplux/livemarket.business.b2bcart:kuerbernetes
#docker push maxiplux/io.api.base:1.0.0
#docker push maxiplux/io.api.base:master .
#docker buildx build --platform linux/amd64,linux/arm64 maxiplux/io.api.base:1.0.0 --push -t maxiplux/io.api.base:1.0.0
#aws lightsail create-container-service --service-name api-server-demo --power micro --scale 1
#aws lightsail push-container-image --region us-east-1 --service-name api-server  --label  api-server   --image maxiplux/io.api.base:2022-04-03--40-22
