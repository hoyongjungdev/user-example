## 사용 기술
- Java 17
- Spring 3
- MySQL 8

## 실행 방법
- Docker가 설치되어 있는 로컬 환경에서 실행합니다.
- MySQL Docker container를 생성합니다
  - 3306번 포트가 사용 중이 아니어야 합니다.

```bash
$ docker run --name user-example-mysql -e MYSQL_ROOT_PASSWORD=my-secret-code -d -p 3306:3306 mysql:8.0.31
```

- DB 클라이언트를 사용해 연결 후, `migration.sql`를 실행합니다.
  - 위치: `src/main/resources/db/migration.sql`
- Intellij IDEA 등 IDE를 Java 17로 세팅한 후 애플리케이션을 실행합니다.
  - 루트 폴더에서 `./mvnw spring-boot:run`를 통해 실행할 수도 있습니다.
- localhost:8080로 API call을 통해 테스트할 수 있습니다.
  - Postman Collection: https://www.getpostman.com/collections/e28163e0663232301a7f

## 구현 스펙
- OpenAPI Specification으로 문서화되었습니다.
  - 위치: `src/main/resources/document/api.yaml`
- 전화번호 인증 기능의 경우 인증 코드를 항상 `000000`으로 설정하도록 고정했습니다.
- 실제로 SMS를 보내는 기능은 mock으로 처리했습니다.
- 회원 가입 및 비밀번호 재설정 시 이전에 먼저 인증 코드 발송을 하고 해당 코드를 입력하는 순서입니다.
- 로그인 API를 이메일 로그인 / 전화번호 로그인으로 분리했습니다.
  - 로그인 성공 시 사용자 ID를 반환하여, 내 정보 보기 API에서 인자로 사용이 가능합니다.

## 특이 사항
- validation을 위해 value object를 적극적으로 사용했습니다.