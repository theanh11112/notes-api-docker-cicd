# spring-docker-cicd-practice

Project thực hành Docker + CI/CD cho Fresh Java Developer, theo giáo trình Tuần 9-12: Docker multi-stage build, GitHub Actions CI/CD, Deploy Cloud, Portfolio.

## Stack
- Spring Boot 4.1.1, Java 17
- PostgreSQL (JPA) + Redis (cache, `@Cacheable`)
- Docker multi-stage build (`maven:3.9-eclipse-temurin-17` → `eclipse-temurin:17-jre`)
- `docker-compose.yml`: app + postgres + redis, healthcheck + restart:always

## Ghi nhớ
- Context hội thoại tự động compact (nén) khi gần chạm giới hạn context window — đã bật `autoCompactEnabled` trong `.claude/settings.json`, không cần tự nhắc lại mỗi phiên.
- Build/test luôn qua Docker (`docker compose up -d --build`) để khớp đúng môi trường JDK 17 mô tả trong giáo trình, không phụ thuộc JDK cài trên máy host.
