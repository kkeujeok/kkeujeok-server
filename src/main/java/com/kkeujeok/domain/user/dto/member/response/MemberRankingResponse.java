package com.kkeujeok.domain.user.dto.member.response;

public class MemberRankingResponse {

    private String nickname;
    private int luck;

    public MemberRankingResponse(String nickname, int luck) {
        this.nickname = nickname;
        this.luck = luck;
    }
}
