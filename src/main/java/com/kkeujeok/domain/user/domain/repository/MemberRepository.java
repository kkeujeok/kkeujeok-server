package com.kkeujeok.domain.user.domain.repository;

import com.kkeujeok.domain.user.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT COUNT(m) FROM Member m WHERE m.luck > (SELECT m2.luck FROM Member m2 WHERE m2.id = :userId)")
    int getRankingOfUser(Long userId);


}
