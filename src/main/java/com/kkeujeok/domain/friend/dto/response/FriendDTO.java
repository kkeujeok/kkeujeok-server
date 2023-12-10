package com.kkeujeok.domain.friend.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class FriendDTO {

    private Long id;

    private String name;

    @Builder
    public FriendDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
