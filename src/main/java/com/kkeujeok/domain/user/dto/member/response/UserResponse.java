package com.kkeujeok.domain.user.dto.member.response;

public class UserResponse {

    private Long id;
    private String nickname;
    public UserResponse(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }
}
