#Build Stage
FROM maven:3.8.7 as Build
WORKDIR /usr/src/app
COPY . .
RUN mvn clean install

#Run Stage
FROM openjdk:17
WORKDIR /usr/src/app
COPY . .
COPY --from=Build /usr/src/app/target/cloudbeestrainreservation-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080:8080
CMD ["java","-jar","app.jar"]
