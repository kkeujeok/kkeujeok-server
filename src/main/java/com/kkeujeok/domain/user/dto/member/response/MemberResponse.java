package com.kkeujeok.domain.user.dto.member.response;

public class MemberResponse {

    private Long id;
    private String nickname;
    private String email;
    public MemberResponse(Long id, String nickname, String email) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
    }
}
