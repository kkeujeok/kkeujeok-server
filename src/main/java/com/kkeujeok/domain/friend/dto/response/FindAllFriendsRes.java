package com.kkeujeok.domain.friend.dto.response;

import com.kkeujeok.domain.friend.domain.Friend;
import com.kkeujeok.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class FindAllFriendsRes {

    private List<FriendDTO> friends;

    private String message;

    @Builder
    public FindAllFriendsRes(List<FriendDTO> friends, String message) {
        this.friends = friends;
        this.message = message;
    }
}
