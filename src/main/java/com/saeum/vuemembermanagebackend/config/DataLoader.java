package com.saeum.vuemembermanagebackend.config;

import com.saeum.vuemembermanagebackend.model.entity.Member;
import com.saeum.vuemembermanagebackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

            List<Member> members = createDummyMembers();

            memberRepository.saveAll(members);
            log.info("{}개의 더미 데이터가 생성되었습니다.", members.size());
        }
    }

    private List<Member> createDummyMembers() {
        List<Member> members = new ArrayList<>();
        
        // 성씨 배열
        String[] lastNames = {
            "김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", 
            "한", "오", "서", "신", "권", "황", "안", "송", "류", "전",
            "홍", "고", "문", "양", "손", "배", "조", "백", "허", "유",
            "남", "심", "노", "정", "하", "곽", "성", "차", "주", "우",
            "구", "신", "임", "나", "전", "민", "유", "진", "지", "엄"
        };

        // 이름 배열 (남성)
        String[] maleNames = {
            "민수", "철수", "영수", "현우", "준호", "지훈", "성민", "동현", "태현", "승현",
            "준영", "현준", "지원", "성호", "민호", "태준", "승우", "현식", "종민", "상훈",
            "진우", "수현", "민재", "기훈", "성준", "태영", "승민", "현수", "종현", "상현",
            "진호", "수민", "민규", "기현", "성우", "태민", "승호", "현우", "종호", "상우",
            "진수", "수호", "민성", "기수", "성현", "태호", "승준", "현민", "종수", "상준"
        };

        // 이름 배열 (여성)
        String[] femaleNames = {
            "지은", "수영", "민정", "지혜", "은영", "소영", "지영", "미영", "혜진", "예은",
            "수진", "민지", "지현", "은지", "소진", "지민", "미진", "혜원", "예진", "수빈",
            "민경", "지우", "은수", "소현", "지연", "미경", "혜영", "예림", "수현", "민서",
            "지원", "은정", "소연", "지수", "미선", "혜수", "예원", "수정", "민주", "지아",
            "은미", "소정", "지윤", "미정", "혜림", "예지", "수아", "민영", "지효", "은솔"
        };

        // 도메인 배열
        String[] domains = {
            "gmail.com", "naver.com", "daum.net", "kakao.com", "hanmail.net",
            "hotmail.com", "yahoo.com", "outlook.com", "susoft.com", "test.com",
            "example.com", "demo.co.kr", "sample.net", "company.com", "business.kr"
        };

        // 200개 멤버 생성
        for (int i = 0; i < 200; i++) {
            String lastName = lastNames[i % lastNames.length];
            String gender = (i % 2 == 0) ? "M" : "F";
            String firstName;
            
            if ("M".equals(gender)) {
                firstName = maleNames[i % maleNames.length];
            } else {
                firstName = femaleNames[i % femaleNames.length];
            }
            
            String fullName = lastName + firstName;
            
            // 전화번호 생성 (010-XXXX-XXXX)
            String phone = String.format("010-%04d-%04d", 
                (i % 9000) + 1000, 
                ((i * 7) % 9000) + 1000);
            
            // 이메일 생성
            String email = String.format("%s%s@%s", 
                lastName.toLowerCase(), 
                firstName.toLowerCase(), 
                domains[i % domains.length]);
            
            // 나이 생성 (20-65세)
            int age = 20 + (i % 46);
            
            members.add(createMember(fullName, phone, email, age, gender));
        }
        
        return members;
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