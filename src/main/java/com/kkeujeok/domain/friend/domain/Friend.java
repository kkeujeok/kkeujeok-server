package com.kkeujeok.domain.friend.domain;

import com.kkeujeok.domain.user.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member member;

    private boolean isAllow;
}
