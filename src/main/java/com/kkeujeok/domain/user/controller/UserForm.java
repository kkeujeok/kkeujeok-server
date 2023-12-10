package com.kkeujeok.domain.user.controller;

import com.kkeujeok.domain.user.domain.Gender;

public class UserForm {
    private String email;
    private String pw;
    private Gender gender;
    private String nickname;


    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPw() {
       return this.pw;
    }

    public Gender getGender() {
        return this.gender;
    }

    public String getNickname() {
      return  this.nickname;
    }
}
