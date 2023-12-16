package com.kkeujeok.domain.user.domain.repository;

import com.kkeujeok.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);


    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String email);

    Optional<User> findByNickname(String nickname);

    @Query("SELECT COUNT(m) FROM User m WHERE m.luck > (SELECT m2.luck FROM User m2 WHERE m2.id = :userId)")
    int getRankingOfUser(Long userId);

    List<User> findByNicknameContaining(String searchWord);

    }
//    @Query("SELECT u FROM User u LEFT JOIN FETCH u.friends WHERE u.id = :userId")
//    Optional<User> findMemberWithFriend(@Param("userId") Long userId);

