#빌드 환경
FROM eclipse-temurin:17-jdk AS builder

WORKDIR /app

# Gradle Wrapper 복사
COPY gradlew .
COPY gradle gradle

# Gradle 파일 복사 (의존성 캐싱)
COPY build.gradle settings.gradle ./

# 의존성 다운로드 (레이어 캐싱)
RUN ./gradlew dependencies --no-daemon

# 소스 코드 복사
COPY src src

# 빌드
RUN ./gradlew bootJar --no-daemon

# 실행 환경
FROM eclipse-temurin:17-jre

WORKDIR /app

# 빌드된 JAR 파일만 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 포트 노출
EXPOSE 8080

# 실행
ENTRYPOINT ["java", "-jar", "app.jar"]