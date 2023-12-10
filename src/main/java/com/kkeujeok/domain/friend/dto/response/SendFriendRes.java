package com.kkeujeok.domain.friend.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class SendFriendRes {

    private String message;

    @Builder
    public SendFriendRes(String message) {
        this.message = message;
    }
}
