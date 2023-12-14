package com.kkeujeok.domain.rollingPaper.Dto;

import com.kkeujeok.domain.user.domain.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateRollingPaperRes {

    private Long id;

    private String content;

    private String senderNickname;


    @Builder
    public CreateRollingPaperRes(Long id, String content, String senderNickname) {
        this.id = id;
        this.content = content;
        this.senderNickname = senderNickname;
    }
}
