package com.kkeujeok.domain.friend.controller;

import com.kkeujeok.domain.friend.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    // Description : 친구 삭제

}
