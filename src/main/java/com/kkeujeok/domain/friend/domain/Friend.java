package com.kkeujeok.domain.friend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kkeujeok.domain.user.domain.User;
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
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_id")
    private User friend;

    @Builder
    public Friend(long id, User user, User friend) {
        this.id = id;
        this.user = user;
        this.friend = friend;
    }
}
