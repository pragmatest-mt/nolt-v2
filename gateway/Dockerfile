FROM maven:3.6.3-jdk-11

WORKDIR /app/gateway

COPY ./gateway/pom.xml ./pom.xml
RUN mvn dependency:go-offline

COPY ./gateway/src/ ./src/
CMD ["mvn","spring-boot:run"]