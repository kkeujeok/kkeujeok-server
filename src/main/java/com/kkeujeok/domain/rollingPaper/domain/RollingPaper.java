package com.kkeujeok.domain.rollingPaper.domain;

import com.kkeujeok.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class RollingPaper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;
    private String senderNickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @Builder
    public RollingPaper(String content, String senderNickname, User user) {
        this.content = content;
        this.senderNickname  = senderNickname;
        this.user = user;
    }

    public static RollingPaper of(String content, String senderNickname, User user){
        return new RollingPaper(
                content,
                senderNickname,
                user
        );
    }



}
