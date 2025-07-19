package com.saeum.vuemembermanagebackend.config;

import com.saeum.vuemembermanagebackend.model.entity.Member;
import com.saeum.vuemembermanagebackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader {

    private final MemberRepository memberRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void loadTestData() {
        if (memberRepository.count() == 0) {
            log.info("초기 더미 데이터를 생성합니다...");

            List<Member> members = List.of(
                createMember("김새음", "010-1234-5678", "saeum@susoft.com", 25, "M"),
                createMember("이새음", "010-2345-6789", "lee@susoft.com", 30, "F"),
                createMember("박새음", "010-3456-7890", "park@susoft.com", 28, "M"),
                createMember("최새음", "010-4567-8901", "choi@susoft.com", 35, "F"),
                createMember("정새음", "010-5678-9012", "jung@susoft.com", 27, "M"),
                createMember("한새음", "010-6789-0123", "han@susoft.com", 32, "F"),
                createMember("조새음", "010-7890-1234", "cho@susoft.com", 29, "M"),
                createMember("윤새음", "010-8901-2345", "yoon@susoft.com", 26, "F"),
                createMember("장새음", "010-9012-3456", "jang@susoft.com", 31, "M"),
                createMember("임새음", "010-0123-4567", "lim@susoft.com", 33, "F"),
                createMember("강새음", "010-1111-2222", "kang@susoft.com", 24, "M"),
                createMember("신새음", "010-2222-3333", "shin@susoft.com", 36, "F"),
                createMember("배새음", "010-3333-4444", "bae@susoft.com", 28, "M"),
                createMember("노새음", "010-4444-5555", "no@susoft.com", 30, "F"),
                createMember("송새음", "010-5555-6666", "song@susoft.com", 27, "M"),
                createMember("홍길동", "010-6666-7777", "hong@test.com", 40, "M"),
                createMember("김철수", "010-7777-8888", "kim@test.com", 22, "M"),
                createMember("이영희", "010-8888-9999", "lee@test.com", 29, "F"),
                createMember("박민수", "010-9999-0000", "park@test.com", 34, "M"),
                createMember("최지현", "010-0000-1111", "choi@test.com", 26, "F"),
                createMember("정수민", "010-1010-2020", "jung@test.com", 31, "M"),
                createMember("한지우", "010-2020-3030", "han@test.com", 25, "F"),
                createMember("조현우", "010-3030-4040", "cho@test.com", 37, "M"),
                createMember("윤서연", "010-4040-5050", "yoon@test.com", 23, "F"),
                createMember("장도윤", "010-5050-6060", "jang@test.com", 32, "M")
            );

            memberRepository.saveAll(members);
            log.info("{}개의 더미 데이터가 생성되었습니다.", members.size());
        }
    }

    private Member createMember(String nick, String phone, String email, int age, String gender) {
        return Member.builder()
            .nick(nick)
            .phone(phone)
            .email(email)
            .age(age)
            .gender(gender)
            .deleteYn(0)
            .build();
    }
}