package com.saeum.vuemembermanagebackend.controller;


import com.saeum.vuemembermanagebackend.model.dto.request.MemberCreateRequest;
import com.saeum.vuemembermanagebackend.model.dto.request.MemberSearchRequest;
import com.saeum.vuemembermanagebackend.model.dto.request.MemberUpdateRequest;
import com.saeum.vuemembermanagebackend.model.dto.response.MemberListResponse;
import com.saeum.vuemembermanagebackend.model.dto.response.MemberResponse;
import com.saeum.vuemembermanagebackend.service.MemberService;
import jakarta.validation.Valid;
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
     * 회원 추가 POST /member/createMember
     */
    @PostMapping("/createMember")
    public ResponseEntity<Void> createMember(@Valid @RequestBody MemberCreateRequest request) {
        log.info("회원 추가 API 호출: {}", request.getNick());

        memberService.createMember(request);

        return ResponseEntity.ok().build(); // 200 OK with null body
    }

    /**
     * 회원 수정 PUT /member/updateMember
     */
    @PutMapping("/updateMember")
    public ResponseEntity<Void> updateMember(@Valid @RequestBody MemberUpdateRequest request) {
        log.info("회원 수정 API 호출: userKey={}", request.getUserKey());

        memberService.updateMember(request);

        return ResponseEntity.ok().build(); // 200 OK with null body
    }

    /**
     * 회원 삭제 DELETE /member/deleteMember
     */
    @DeleteMapping("/deleteMember")
    public ResponseEntity<Void> deleteMember(@RequestParam("userKey") Long userKey) {
        log.info("회원 삭제 API 호출: userKey={}", userKey);

        memberService.deleteMember(userKey);

        return ResponseEntity.ok().build(); // 200 OK with null body
    }

    /**
     * 회원 상세 조회 POST /member/getMemberDetail
     */
    @PostMapping("/getMemberDetail")
    public ResponseEntity<MemberResponse> getMemberDetail(@RequestBody Map<String, Long> request) {
        Long userKey = request.get("userKey");
        log.info("회원 상세 조회 API 호출: userKey={}", userKey);

        if (userKey == null) {
            throw new IllegalArgumentException("userKey는 필수입니다.");
        }

        MemberResponse memberResponse = memberService.getMemberDetail(userKey);

        return ResponseEntity.ok(memberResponse);
    }

    /**
     * 회원 목록 조회 (검색, 페이징) POST /member/getMemberList
     */
    @PostMapping("/getMemberList")
    public ResponseEntity<MemberListResponse> getMemberList(
        @RequestBody MemberSearchRequest request) {
        log.info("회원 목록 조회 API 호출: page={}, size={}, hasSearch={}",
            request.getPage(), request.getSize(), request.hasSearchCondition());

        // 기본값 설정
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
