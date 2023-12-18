package com.kkeujeok.domain.user.dto.member.response;

import lombok.Builder;

public class UserResponse {

    private Long id;
    private String email;
    private String nickname;

    @Builder
    public UserResponse(Long id, String email, String nickname) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
    }
}
