package com.kkeujeok.domain.user.dto.member.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class UserResponse {

    private Long id;
    private String nickname;

    @Builder
    public UserResponse(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }
}
