# 첫 번째 스테이지: 빌드 스테이지
FROM gradle:jdk21-graal-jammy as builder

WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Gradle 래퍼에 실행 권한 부여
RUN chmod +x ./gradlew

# 종속성 설치
RUN ./gradlew dependencies --no-daemon

COPY src src

RUN ./gradlew build --no-daemon

FROM ghcr.io/graalvm/jdk-community:21

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

# 실행할 JAR 파일 지정
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]