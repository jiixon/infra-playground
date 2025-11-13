# infra-playground
## 01. 빠른 시작
### 01-1. 전제 조건
- Docker & Docker Compose 설치

### 01-2. 실행
```bash
# 1. 클론
git clone https://github.com/jiixon/infra-playground.git
cd infra-playground

# 2. 실행
docker compose up

# 3. 헬스체크
curl http://localhost:8080/health
# 응답: OK

# 4. 중지
docker compose down

# 5. 전체 재시작
docker compose restart
```

## 02. 테스트 실행
```bash
./gradlew test
```

Testcontainers가 자동으로 MySQL, Redis, Kafka를 띄워서 테스트합니다.

## 03. 구성 요소

- **App**: Spring Boot 3.2 (Java 17)
- **DB**: MySQL 8.0
- **Cache**: Redis 7
- **Broker**: Apache Kafka 3.7
