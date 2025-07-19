package com.saeum.vuemembermanagebackend.model.dto.response;

import com.saeum.vuemembermanagebackend.model.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResponse {

    private Long userKey;
    private String nick;
    private String phone;
    private String email;
    private Integer age;
    private String gender;

    // Entity -> DTO 변환 메서드
    public static MemberResponse from(Member member) {
        return MemberResponse.builder()
            .userKey(member.getUserKey())
            .nick(member.getNick())
            .phone(member.getPhone())
            .email(member.getEmail())
            .age(member.getAge())
            .gender(member.getGender())
            .build();
    }
}