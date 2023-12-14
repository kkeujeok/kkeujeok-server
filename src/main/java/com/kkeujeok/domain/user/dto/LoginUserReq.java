package com.kkeujeok.domain.user.dto;

import lombok.Getter;

@Getter
public class LoginUserReq {
    private String email;
    private String pw;
}