package com.kkeujeok.domain.user.dto;

import com.kkeujeok.domain.rollingPaper.Dto.RollingPaperDto;
import com.kkeujeok.domain.rollingPaper.domain.RollingPaper;
import com.kkeujeok.domain.user.domain.Gender;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class FindUserRes {

    private String userNickName;

    private Gender gender;

    private List<RollingPaperDto> rollingPaperDtos = new ArrayList<>();

    private String message;

    @Builder
    public FindUserRes(String userNickName, Gender gender, List<RollingPaperDto> rollingPaperDtos, String message) {
        this.userNickName = userNickName;
        this.gender = gender;
        this.rollingPaperDtos = rollingPaperDtos;
        this.message = message;
    }
}
