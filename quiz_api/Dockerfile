FROM maty/gradle74-jdk17-temurin as builder
WORKDIR /app
COPY . .
RUN ./gradlew build --stacktrace

FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
EXPOSE 8085
COPY --from=builder app/build/libs/quiz_api-0.0.1.jar .
CMD java -jar app/build/libs/quiz_api-0.0.1.jar
# -Dloader.main=com.tim..quiz_api.QuizApiApplicationKt
