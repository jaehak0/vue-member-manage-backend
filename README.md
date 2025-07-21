# Vue Member Manage Backend

> **이 프로젝트는 과제용 프런트엔드(Vue 등) 개발/테스트를 위한 백엔드 서버입니다.**

## 프로젝트 개요
Vue Member Manage Backend는 회원 관리 기능을 제공하는 Spring Boot 기반의 RESTful API 서버입니다. 회원의 등록, 수정, 삭제, 상세조회, 목록조회 등의 기능을 제공합니다. 프론트엔드(Vue.js 등)와 연동하여 사용할 수 있습니다.

## 주요 기능
- 회원 등록 (Create)
- 회원 정보 수정 (Update)
- 회원 삭제 (Delete)
- 회원 상세 조회 (Read)
- 회원 목록/검색 (List/Search)

## 기술 스택
- Java 17
- Spring Boot 3.5.3
- Spring Data JPA
- H2 Database (개발/테스트용)
- Lombok
- Gradle

## 폴더 구조
```
├── src
│   └── main
│       ├── java
│       │   └── com.saeum.vuemembermanagebackend
│       │       ├── controller         # REST API 컨트롤러
│       │       ├── service            # 서비스 레이어 (비즈니스 로직)
│       │       ├── repository         # JPA 리포지토리
│       │       ├── model              # 엔티티 및 DTO
│       │       └── exception          # 커스텀 예외
│       └── resources                  # 설정 및 리소스 파일
├── build.gradle                       # Gradle 빌드 스크립트
├── settings.gradle                    # Gradle 설정
└── README.md                          # 프로젝트 설명 파일
```

## 주요 클래스 및 설명
- `MemberController`: 회원 관련 REST API 엔드포인트 제공
- `MemberService`, `MemberServiceImpl`: 회원 비즈니스 로직 처리
- `MemberRepository`: 회원 엔티티 JPA 접근
- `Member`: 회원 엔티티 (userKey, nick, phone, email, age, gender, deleteYn 등)

## 환경설정(application.example)
- 환경설정 예시는 `application.example` 파일에 있습니다.
- 실제 실행 시에는 해당 파일을 `src/main/resources/application.yml`로 복사하여 사용하세요.
- 예시 파일에는 DB 비밀번호 등 민감 정보가 포함되어 있지 않으므로 git에 올라가도 안전합니다.

```bash
cp application.example src/main/resources/application.yml
```

## 실행 방법
1. **의존성 설치**
   ```bash
   ./gradlew build
   ```
2. **서버 실행**
   ```bash
   ./gradlew bootRun
   ```
3. **API 테스트**
   - Swagger, Postman 등으로 `/member` 하위 엔드포인트 호출

## API 명세 및 예시

### 1. 회원 등록
- **URL**: `POST /member/createMember`
- **Request Body**
```json
{
  "nick": "홍길동",
  "phone": "010-1234-5678",
  "email": "hong@example.com",
  "age": 25,
  "gender": "M"
}
```
- **Response**
```json
{
  "success": true,
  "message": "회원이 성공적으로 추가되었습니다."
}
```

### 2. 회원 수정
- **URL**: `PUT /member/updateMember`
- **Request Body**
```json
{
  "user_key": 1,
  "nick": "홍길순",
  "phone": "010-5678-1234",
  "email": "hongsoon@example.com",
  "age": 26,
  "gender": "F"
}
```
- **Response**
```json
{
  "success": true,
  "message": "회원 정보가 성공적으로 수정되었습니다."
}
```

### 3. 회원 삭제
- **URL**: `DELETE /member/deleteMember`
- **Request Body**
```json
{
  "user_key": 1
}
```
- **Response**
```json
{
  "success": true,
  "message": "회원이 성공적으로 삭제되었습니다."
}
```

### 4. 회원 상세 조회
- **URL**: `POST /member/getMemberDetail`
- **Request Body**
```json
{
  "user_key": 1
}
```
- **Response**
```json
{
  "userKey": 1,
  "nick": "홍길동",
  "phone": "010-1234-5678",
  "email": "hong@example.com",
  "age": 25,
  "gender": "M"
}
```

### 5. 회원 목록/검색 (페이징)
- **URL**: `POST /member/getMemberList`
- **Request Body**
```json
{
  "nick": "홍",
  "phone": null,
  "email": null,
  "page": 1,
  "size": 10
}
```
- **Response**
```json
{
  "members": [
    {
      "userKey": 1,
      "nick": "홍길동",
      "phone": "010-1234-5678",
      "email": "hong@example.com",
      "age": 25,
      "gender": "M"
    }
    // ...
  ],
  "totalCount": 1,
  "page": 1,
  "size": 10,
  "totalPages": 1
}
```

## 참고 자료
- [Spring Boot 공식 문서](https://spring.io/projects/spring-boot)
- [Spring Data JPA 가이드](https://spring.io/guides/gs/accessing-data-jpa/)

---
문의: [프로젝트 관리자에게 문의]
