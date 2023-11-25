package com.kkeujeok.domain.user.domain;

import com.kkeujeok.domain.friend.domain.Friend;
import com.kkeujeok.domain.rollingPaper.domain.RollingPaper;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;
    private String password;
    private String nickname;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private int luck;

    @OneToMany(mappedBy = "member")
    private List<RollingPaper> rollingPapers = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Friend> friends = new ArrayList<>();

}