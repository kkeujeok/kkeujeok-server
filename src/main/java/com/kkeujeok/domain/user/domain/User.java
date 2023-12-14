package com.kkeujeok.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kkeujeok.domain.friend.domain.Friend;
import com.kkeujeok.domain.rollingPaper.domain.RollingPaper;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private String password;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private int luck;

    private boolean loginStatus;

    public boolean getLoginStatus() {
        return this.loginStatus;
    }

    public void setEmail(String userEmail) {
        this.email = userEmail;
    }

    public void setPW (String password) {
        this.password = password;
    }

    public void setNickname (String nickname) {
        this.nickname = nickname;
    }

    public void setGender (Gender gender) {
        this.gender = gender;
    }

    public void setLoginStatus (boolean status) {
        this.loginStatus = status;
    }

    public void login() {
        this.loginStatus = true;
    }

    public void logout() {
        this.loginStatus = false;
    }


//    @OneToMany(mappedBy = "user")
//    private List<RollingPaper> rollingPapers = new ArrayList<>();

//    @OneToMany(mappedBy = "user")
//    private List<Friend> friends = new ArrayList<>();

}
