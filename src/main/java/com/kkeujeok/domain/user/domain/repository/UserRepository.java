package com.kkeujeok.domain.user.domain.repository;

import com.kkeujeok.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("SELECT u FROM User u LEFT JOIN FETCH u.friends WHERE u.id = :userId")
//    Optional<User> findMemberWithFriend(@Param("userId") Long userId);

}
