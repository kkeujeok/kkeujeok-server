package com.kkeujeok.domain.friend.controller;

import com.kkeujeok.domain.friend.dto.response.FindAllFriendsRes;
import com.kkeujeok.domain.friend.dto.response.SendFriendRes;
import com.kkeujeok.domain.friend.service.FriendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Friend API", description = "Friend 관련 API입니다.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/friends")
public class FriendController {

    private final FriendService friendService;

    // Description : 친구 목록 조회
    @Operation(summary = "친구 목록 조회", description = "User ID를 이용하여 친구 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "친구 목록 조회 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = FindAllFriendsRes.class))}),
            @ApiResponse(responseCode = "400", description = "친구 목록 조회 실패 - friends : null", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = FindAllFriendsRes.class))}),
    })
    @GetMapping("/{user-id}")
    public ResponseEntity<?> findAllFriends(@PathVariable(value = "user-id") Long userId) {
        return friendService.findAllFriends(userId);
    }

    // Description : 친구 신청
    @Operation(summary = "친구 신청", description = "User ID와 Friend ID를 이용하여 친구 관계를 신청합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "친구 신청 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SendFriendRes.class))}),
            @ApiResponse(responseCode = "400", description = "친구 신청 실패", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SendFriendRes.class))}),
    })
    @PostMapping("/{user-id}/{friend-id}")
    public ResponseEntity<?> sendFriend(@PathVariable(value = "user-id") Long userId, @PathVariable(value = "friend-id") Long friendId) {
        return friendService.sendFriend(userId, friendId);
    }

    // Description : 친구 삭제
    @Operation(summary = "친구 삭제", description = "User ID와 Friend ID를 이용하여 친구 관계를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "친구 삭제 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = FindAllFriendsRes.class))}),
            @ApiResponse(responseCode = "400", description = "친구 삭제 실패", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SendFriendRes.class))}),
    })
    @DeleteMapping("/{user-id}/{friend-id}")
    public ResponseEntity<?> deleteFriend(@PathVariable(value = "user-id") Long userId, @PathVariable(value = "friend-id") Long friendId) {
        return friendService.deleteFriend(userId, friendId);
    }

}
