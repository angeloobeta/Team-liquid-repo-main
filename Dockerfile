FROM maven:3.8.5-openjdk-17 As build
COPY . .
RUN mvn clean package -DskipTests
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/review_and_rating-0.0.1-SNAPSHOT.jar review_and_rating.jar
EXPOSE 8181
ENTRYPOINT ["java","-jar","review_and_rating.jar"]