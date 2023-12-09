package com.kkeujeok.domain.friend.dto.response;

import com.kkeujeok.domain.friend.domain.Friend;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class FindAllFriendsRes {

    private List<Friend> friends;

    private String message;

    @Builder
    public FindAllFriendsRes(List<Friend> friends, String message) {
        this.friends = friends;
        this.message = message;
    }
}
