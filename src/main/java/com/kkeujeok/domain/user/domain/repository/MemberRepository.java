package com.kkeujeok.domain.user.domain.repository;

import com.kkeujeok.domain.user.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m FROM Member m LEFT JOIN FETCH m.friends WHERE m.id = :userId")
    Optional<Member> findMemberWithFriend(@Param("userId") Long userId);
}
