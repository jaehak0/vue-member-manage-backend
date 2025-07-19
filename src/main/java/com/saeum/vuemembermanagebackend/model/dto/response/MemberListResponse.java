package com.saeum.vuemembermanagebackend.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberListResponse {

    private List<MemberResponse> members;
    private Long totalCount;
    private Integer page;
    private Integer size;
    private Integer totalPages;

    // Page<Member> -> MemberListResponse 변환 메서드
    public static MemberListResponse from(Page<MemberResponse> memberPage) {
        return MemberListResponse.builder()
            .members(memberPage.getContent())
            .totalCount(memberPage.getTotalElements())
            .page(memberPage.getNumber() + 1) // 0-based -> 1-based
            .size(memberPage.getSize())
            .totalPages(memberPage.getTotalPages())
            .build();
    }
}
