package com.kkeujeok.domain.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class EmptyUserRes {

    private String message;

    @Builder
    public EmptyUserRes(String message) {
        this.message = message;
    }
}
