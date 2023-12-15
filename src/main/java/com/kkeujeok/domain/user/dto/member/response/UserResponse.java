package com.kkeujeok.domain.user.dto.member.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class UserResponse {

    private String email;
    private String nickname;

    @Builder
    public UserResponse(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }
}
