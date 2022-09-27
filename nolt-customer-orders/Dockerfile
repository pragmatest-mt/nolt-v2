FROM maven:3.6.3-jdk-11 as builder
WORKDIR /app
COPY ./pom.xml ./pom.xml
WORKDIR /app/nolt-customer-orders
COPY ./nolt-customer-orders/pom.xml ./pom.xml
COPY ./nolt-customer-orders/src/ ./src/
WORKDIR /app
RUN mvn clean package

FROM openjdk:11-ea-17-jre-slim
ARG JAR_NAME="nolt-customer-orders-0.0.1-SNAPSHOT"
WORKDIR /usr/src/app
EXPOSE 9001
COPY --from=builder /app/nolt-customer-orders/target/${JAR_NAME}.jar ./app.jar
CMD ["java","-jar", "./app.jar"]