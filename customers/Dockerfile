FROM maven:3.6.3-jdk-11

WORKDIR /app/customers

COPY ./customers/pom.xml ./pom.xml
RUN mvn dependency:go-offline

COPY ./customers/src/ ./src/

RUN mvn test

CMD ["mvn","spring-boot:run"]