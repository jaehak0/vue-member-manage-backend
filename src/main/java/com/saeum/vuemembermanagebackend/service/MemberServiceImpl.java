package com.saeum.vuemembermanagebackend.service;

import com.saeum.vuemembermanagebackend.exception.MemberNotFoundException;
import com.saeum.vuemembermanagebackend.model.dto.request.MemberCreateRequest;
import com.saeum.vuemembermanagebackend.model.dto.request.MemberSearchRequest;
import com.saeum.vuemembermanagebackend.model.dto.request.MemberUpdateRequest;
import com.saeum.vuemembermanagebackend.model.dto.response.MemberListResponse;
import com.saeum.vuemembermanagebackend.model.dto.response.MemberResponse;
import com.saeum.vuemembermanagebackend.model.entity.Member;
import com.saeum.vuemembermanagebackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void createMember(MemberCreateRequest request) {
        log.info("회원 생성 요청: {}", request.getNick());

        Member member = Member.builder()
            .nick(request.getNick())
            .phone(request.getPhone())
            .email(request.getEmail())
            .age(request.getAge())
            .gender(request.getGender())
            .deleteYn(0)
            .build();

        memberRepository.save(member);
        log.info("회원 생성 완료: userKey={}", member.getUserKey());
    }

    @Override
    @Transactional
    public void updateMember(MemberUpdateRequest request) {
        log.info("회원 수정 요청: userKey={}", request.getUserKey());

        Member member = memberRepository.findActiveByUserKey(request.getUserKey())
            .orElseThrow(() -> new MemberNotFoundException(request.getUserKey()));

        // 회원 정보 업데이트
        member.setNick(request.getNick());
        member.setPhone(request.getPhone());
        member.setEmail(request.getEmail());
        member.setAge(request.getAge());
        member.setGender(request.getGender());

        // JPA dirty checking으로 자동 업데이트
        log.info("회원 수정 완료: userKey={}", request.getUserKey());
    }

    @Override
    @Transactional
    public void deleteMember(Long userKey) {
        log.info("회원 삭제 요청: userKey={}", userKey);

        Member member = memberRepository.findActiveByUserKey(userKey)
            .orElseThrow(() -> new MemberNotFoundException(userKey));

        // 논리 삭제 (deleteYn = 1)
        member.setDeleteYn(1);

        log.info("회원 삭제 완료: userKey={}", userKey);
    }

    @Override
    public MemberResponse getMemberDetail(Long userKey) {
        log.info("회원 상세 조회 요청: userKey={}", userKey);

        Member member = memberRepository.findActiveByUserKey(userKey)
            .orElseThrow(() -> new MemberNotFoundException(userKey));

        return MemberResponse.from(member);
    }

    @Override
    public MemberListResponse getMemberList(MemberSearchRequest request) {
        log.info("회원 목록 조회 요청: page={}, size={}, hasSearchCondition={}",
            request.getPage(), request.getSize(), request.hasSearchCondition());

        // 페이징 설정 (1-based -> 0-based 변환)
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize());

        Page<Member> memberPage;

        // 검색 조건에 따른 조회
        if (request.hasSearchCondition()) {
            String keyword = request.getSearchKeyword();
            log.info("검색 키워드: {}", keyword);

            // 통합 검색 (이름, 전화번호, 이메일)
            memberPage = memberRepository.findByKeywordContaining(keyword, pageable);
        } else {
            // 전체 조회
            memberPage = memberRepository.findActiveMembers(pageable);
        }

        // Entity -> DTO 변환
        Page<MemberResponse> memberResponsePage = memberPage.map(MemberResponse::from);

        log.info("회원 목록 조회 완료: totalElements={}, totalPages={}",
            memberResponsePage.getTotalElements(), memberResponsePage.getTotalPages());

        return MemberListResponse.from(memberResponsePage);
    }
}
