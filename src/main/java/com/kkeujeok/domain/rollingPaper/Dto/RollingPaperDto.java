package com.kkeujeok.domain.rollingPaper.Dto;

import com.kkeujeok.domain.rollingPaper.domain.RollingPaper;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RollingPaperDto {

    private Long id;
    private String content;
    private String senderNickname;

    @Builder
    public RollingPaperDto(long id, String content, String senderNickname) {
        this.id = id;
        this.content = content;
        this.senderNickname = senderNickname;
    }


    public static RollingPaperDto from(RollingPaper rollingPaper){
        return new RollingPaperDto(
                rollingPaper.getId(),
                rollingPaper.getContent(),
                rollingPaper.getSenderNickname()
        );
    }




}
