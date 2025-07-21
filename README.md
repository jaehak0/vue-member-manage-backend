# Vue Member Manage Backend

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

## API 예시
- 회원 등록: `POST /member/createMember`
- 회원 수정: `PUT /member/updateMember`
- 회원 삭제: `DELETE /member/deleteMember`
- 회원 상세조회: `POST /member/getMemberDetail`
- 회원 목록조회: `POST /member/getMemberList`

## 참고 자료
- [Spring Boot 공식 문서](https://spring.io/projects/spring-boot)
- [Spring Data JPA 가이드](https://spring.io/guides/gs/accessing-data-jpa/)

---
문의: [프로젝트 관리자에게 문의]
