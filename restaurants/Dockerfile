FROM maven:3.6.3-jdk-11

WORKDIR /app/restaurants

COPY ./restaurants/pom.xml ./pom.xml
RUN mvn dependency:go-offline

COPY ./restaurants/src/ ./src/
CMD ["mvn","spring-boot:run"]