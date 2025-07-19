package com.saeum.vuemembermanagebackend.service;


import com.saeum.vuemembermanagebackend.model.dto.request.MemberCreateRequest;
import com.saeum.vuemembermanagebackend.model.dto.request.MemberSearchRequest;
import com.saeum.vuemembermanagebackend.model.dto.request.MemberUpdateRequest;
import com.saeum.vuemembermanagebackend.model.dto.response.MemberListResponse;
import com.saeum.vuemembermanagebackend.model.dto.response.MemberResponse;

public interface MemberService {

    /**
     * 회원 생성
     *
     * @param request 회원 생성 요청 정보
     */
    void createMember(MemberCreateRequest request);

    /**
     * 회원 수정
     *
     * @param request 회원 수정 요청 정보
     */
    void updateMember(MemberUpdateRequest request);

    /**
     * 회원 삭제 (논리 삭제)
     *
     * @param userKey 회원 고유키
     */
    void deleteMember(Long userKey);

    /**
     * 회원 상세 조회
     *
     * @param userKey 회원 고유키
     * @return 회원 상세 정보
     */
    MemberResponse getMemberDetail(Long userKey);

    /**
     * 회원 목록 조회 (페이징, 검색)
     *
     * @param request 검색 조건 및 페이징 정보
     * @return 회원 목록 및 페이징 정보
     */
    MemberListResponse getMemberList(MemberSearchRequest request);
}
