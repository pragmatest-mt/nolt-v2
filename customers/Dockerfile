FROM maven:3.6.3-jdk-11

WORKDIR /app/customers

COPY ./customers/pom.xml ./pom.xml
RUN mvn dependency:go-offline

RUN mvn test

COPY ./customers/src/ ./src/
CMD ["mvn","spring-boot:run"]