package com.saeum.vuemembermanagebackend.model.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberUpdateRequest {

    @NotNull(message = "회원 고유키는 필수입니다.")
    private Long userKey;

    @NotBlank(message = "이름은 필수입니다.")
    @Size(max = 50, message = "이름은 50자 이하여야 합니다.")
    private String nick;

    @NotBlank(message = "전화번호는 필수입니다.")
    @Pattern(regexp = "^(010|011|016|017|018|019)-?\\d{3,4}-?\\d{4}$",
        message = "전화번호 형식이 올바르지 않습니다. (예: 010-1234-5678, 01012345678)")
    private String phone;

    @NotBlank(message = "이메일은 필수입니다.")
    @Pattern(regexp = "^[\\w._%+-]+@[\\w.-]+\\.[A-Za-z]{2,}$",
        message = "이메일 형식이 올바르지 않습니다.")
    @Size(max = 100, message = "이메일은 100자 이하여야 합니다.")
    private String email;

    @NotNull(message = "나이는 필수입니다.")
    @Min(value = 1, message = "나이는 1 이상이어야 합니다.")
    @Max(value = 150, message = "나이는 150 이하여야 합니다.")
    private Integer age;

    @NotBlank(message = "성별은 필수입니다.")
    @Pattern(regexp = "^[MF]$", message = "성별은 M(남성) 또는 F(여성)이어야 합니다.")
    private String gender;
}