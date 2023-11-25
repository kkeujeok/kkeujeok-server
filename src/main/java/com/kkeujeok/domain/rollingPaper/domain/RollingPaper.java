package com.kkeujeok.domain.rollingPaper.domain;

import com.kkeujeok.domain.user.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class RollingPaper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;
    private String senderNickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member member;
}
