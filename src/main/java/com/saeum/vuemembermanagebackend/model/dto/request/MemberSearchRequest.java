package com.saeum.vuemembermanagebackend.model.dto.request;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberSearchRequest {

    private String nick;    // 이름으로 검색
    private String phone;   // 전화번호로 검색
    private String email;   // 이메일로 검색

    private Integer page = 1;    // 페이지 번호 (기본값: 1)
    private Integer size = 10;   // 페이지 크기 (기본값: 10)

    // 검색 조건이 있는지 확인하는 메서드
    public boolean hasSearchCondition() {
        return (nick != null && !nick.trim().isEmpty()) ||
            (phone != null && !phone.trim().isEmpty()) ||
            (email != null && !email.trim().isEmpty());
    }

    // 통합 검색 키워드 반환 (이름, 전화번호, 이메일 중 첫 번째 값)
    public String getSearchKeyword() {
        if (nick != null && !nick.trim().isEmpty()) {
            return nick.trim();
        }
        if (phone != null && !phone.trim().isEmpty()) {
            return phone.trim();
        }
        if (email != null && !email.trim().isEmpty()) {
            return email.trim();
        }
        return null;
    }
}
