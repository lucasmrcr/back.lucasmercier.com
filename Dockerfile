FROM gradle:7.5.1-jdk17 AS BUILDER
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM amazoncorretto:17

WORKDIR /home/build
COPY --from=BUILDER /home/gradle/src/build/libs/**SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/home/build/app.jar"]