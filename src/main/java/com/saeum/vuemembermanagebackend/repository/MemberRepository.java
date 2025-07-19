package com.saeum.vuemembermanagebackend.repository;


import com.saeum.vuemembermanagebackend.model.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * 삭제되지 않은 회원 조회 (userKey로)
     */
    Optional<Member> findByUserKeyAndDeleteYn(Long userKey, Integer deleteYn);

    /**
     * 삭제되지 않은 회원 조회 (기본 메서드)
     */
    default Optional<Member> findActiveByUserKey(Long userKey) {
        return findByUserKeyAndDeleteYn(userKey, 0);
    }

    /**
     * 삭제되지 않은 회원 전체 조회 (페이징)
     */
    Page<Member> findByDeleteYnOrderByCreatedAtDesc(Integer deleteYn, Pageable pageable);

    /**
     * 삭제되지 않은 회원 전체 조회 (기본 메서드)
     */
    default Page<Member> findActiveMembers(Pageable pageable) {
        return findByDeleteYnOrderByCreatedAtDesc(0, pageable);
    }

    /**
     * 이름으로 검색 (삭제되지 않은 회원 대상)
     */
    @Query("SELECT m FROM Member m WHERE m.deleteYn = 0 AND m.nick LIKE %:nick% ORDER BY m.createdAt DESC")
    Page<Member> findByNickContaining(@Param("nick") String nick, Pageable pageable);

    /**
     * 전화번호로 검색 (삭제되지 않은 회원 대상)
     */
    @Query("SELECT m FROM Member m WHERE m.deleteYn = 0 AND m.phone LIKE %:phone% ORDER BY m.createdAt DESC")
    Page<Member> findByPhoneContaining(@Param("phone") String phone, Pageable pageable);

    /**
     * 이메일로 검색 (삭제되지 않은 회원 대상)
     */
    @Query("SELECT m FROM Member m WHERE m.deleteYn = 0 AND m.email LIKE %:email% ORDER BY m.createdAt DESC")
    Page<Member> findByEmailContaining(@Param("email") String email, Pageable pageable);

    /**
     * 복합 검색 (이름, 전화번호, 이메일)
     */
    @Query("SELECT m FROM Member m WHERE m.deleteYn = 0 AND " +
        "(m.nick LIKE %:keyword% OR m.phone LIKE %:keyword% OR m.email LIKE %:keyword%) " +
        "ORDER BY m.createdAt DESC")
    Page<Member> findByKeywordContaining(@Param("keyword") String keyword, Pageable pageable);

    /**
     * 삭제되지 않은 회원 수 조회
     */
    long countByDeleteYn(Integer deleteYn);

    /**
     * 삭제되지 않은 회원 수 조회 (기본 메서드)
     */
    default long countActiveMembers() {
        return countByDeleteYn(0);
    }
}
