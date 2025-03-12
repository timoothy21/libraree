FROM openjdk:21-jdk-slim

RUN RUN apt-get update && apt-get install -yq tzdata && ln -fs /usr/share/zoneinfo/Asia/Jakarta /etc/localtime && dpkg-reconfigure -f noninteractive tzdata

RUN rm -rf /app
WORKDIR /app

COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
COPY src src/

RUN chmod +x mvnw

RUN ./mvnw package -DskipTests

RUN cp target/*.jar app.jar

EXPOSE 3000

# run jar file
CMD ["java", "-jar", "app.jar"]