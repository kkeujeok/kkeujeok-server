package com.kkeujeok.domain.friend.domain.repository;

import com.kkeujeok.domain.friend.domain.Friend;
import com.kkeujeok.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query("SELECT DISTINCT f FROM Friend f  " +
            "LEFT JOIN FETCH f.friend " +
            "LEFT JOIN FETCH f.user " +
            "WHERE f.user.id = :userId")
    List<Friend> findFriendListByUserId(@Param("userId") Long userId);

    @Query("SELECT distinct f FROM Friend f LEFT JOIN FETCH f.user WHERE f.user.id = :userId")
    List<Friend> findAllByUserId(@Param("userId") Long userId);

    List<Friend> findAllByUser(User user);

    @Query("SELECT distinct f FROM Friend f LEFT JOIN FETCH f.user WHERE f.user.id = :friendId")
    List<Friend> findAllByFriendId(Long friendId);
}
