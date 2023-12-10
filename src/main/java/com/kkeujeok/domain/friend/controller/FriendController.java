package com.kkeujeok.domain.friend.controller;

import com.kkeujeok.domain.friend.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/friends")
public class FriendController {

    private final FriendService friendService;

    // Description : 친구 목록 조회
    @GetMapping("/{user-id}")
    public ResponseEntity<?> findAllFriends(@PathVariable(value = "user-id") Long userId) {
        return friendService.findAllFriends(userId);
    }

    // Description : 친구 신청
    @PostMapping("/{user-id}/{friend-id}")
    public ResponseEntity<?> sendFriend(@PathVariable(value = "user-id") Long userId, @PathVariable(value = "friend-id") Long friendId) {
        return friendService.sendFriend(userId, friendId);
    }

    // Description : 친구 삭제
    @DeleteMapping("/{user-id}/{friend-id}")
    public ResponseEntity<?> deleteFriend(@PathVariable(value = "user-id") Long userId, @PathVariable(value = "friend-id") Long friendId) {
        return friendService.deleteFriend(userId, friendId);
    }

}
