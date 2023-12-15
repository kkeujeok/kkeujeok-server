package com.kkeujeok.domain.user.dto.member.response;

import lombok.Data;

@Data
public class UserRankingResponse {

    private String nickname;
    private int luck;

    public UserRankingResponse(String nickname, int luck) {
        this.nickname = nickname;
        this.luck = luck;
    }
}
