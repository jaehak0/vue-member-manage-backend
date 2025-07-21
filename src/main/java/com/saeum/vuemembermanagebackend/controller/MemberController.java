package com.saeum.vuemembermanagebackend.controller;


import com.saeum.vuemembermanagebackend.model.dto.request.MemberCreateRequest;
import com.saeum.vuemembermanagebackend.model.dto.request.MemberDeleteRequest;
import com.saeum.vuemembermanagebackend.model.dto.request.MemberDetailRequest;
import com.saeum.vuemembermanagebackend.model.dto.request.MemberSearchRequest;
import com.saeum.vuemembermanagebackend.model.dto.request.MemberUpdateRequest;
import com.saeum.vuemembermanagebackend.model.dto.response.MemberListResponse;
import com.saeum.vuemembermanagebackend.model.dto.response.MemberResponse;
import com.saeum.vuemembermanagebackend.service.MemberService;
import jakarta.validation.Valid;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 추가 POST /api/member/createMember
     */
    @PostMapping("/createMember")
    public ResponseEntity<Map<String, Object>> createMember(
        @Valid @RequestBody MemberCreateRequest request) {
        log.info("회원 추가 API 호출: {}", request.getNick());

        memberService.createMember(request);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "회원이 성공적으로 추가되었습니다.");

        return ResponseEntity.ok(response);
    }

    /**
     * 회원 수정 PUT /api/member/updateMember
     */
    @PutMapping("/updateMember")
    public ResponseEntity<Map<String, Object>> updateMember(
        @Valid @RequestBody MemberUpdateRequest request) {
        log.info("회원 수정 API 호출: userKey={}", request.getUserKey());

        memberService.updateMember(request);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "회원 정보가 성공적으로 수정되었습니다.");

        return ResponseEntity.ok(response);
    }

    /**
     * 회원 삭제 DELETE /api/member/deleteMember
     */
    @DeleteMapping("/deleteMember")
    public ResponseEntity<Map<String, Object>> deleteMember(
        @Valid @RequestBody MemberDeleteRequest request) {
        log.info("회원 삭제 API 호출: userKey={}", request.getUserKey());

        memberService.deleteMember(request.getUserKey());

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "회원이 성공적으로 삭제되었습니다.");

        return ResponseEntity.ok(response);
    }

    /**
     * 회원 상세 조회 POST /api/member/getMemberDetail
     */
    @PostMapping("/getMemberDetail")
    public ResponseEntity<MemberResponse> getMemberDetail(
        @Valid @RequestBody MemberDetailRequest request) {
        log.info("회원 상세 조회 API 호출: userKey={}", request.getUserKey());

        MemberResponse memberResponse = memberService.getMemberDetail(request.getUserKey());

        return ResponseEntity.ok(memberResponse);
    }

    /**
     * 회원 목록 조회 (검색, 페이징) POST /api/member/getMemberList
     */
    @PostMapping("/getMemberList")
    public ResponseEntity<MemberListResponse> getMemberList(
        @RequestBody MemberSearchRequest request) {
        log.info("회원 목록 조회 API 호출: page={}, size={}, hasSearch={}",
            request.getPage(), request.getSize(), request.hasSearchCondition());

        // 기본값 설정 및 유효성 검사
        if (request.getPage() == null || request.getPage() < 1) {
            request.setPage(1);
        }
        if (request.getSize() == null || request.getSize() < 1) {
            request.setSize(10);
        }

        MemberListResponse memberListResponse = memberService.getMemberList(request);

        return ResponseEntity.ok(memberListResponse);
    }
}

