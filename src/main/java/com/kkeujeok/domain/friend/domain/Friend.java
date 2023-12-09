package com.kkeujeok.domain.friend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kkeujeok.domain.user.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public Friend(long id, Member member, boolean isAllow) {
        this.id = id;
        this.member = member;
        this.isAllow = isAllow;
    }
}
