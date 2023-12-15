package com.kkeujeok.domain.user.dto;

import com.kkeujeok.domain.user.domain.Gender;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class UserForm {
    private String email;
    private String password;
    private Gender gender;
    private String nickname;

}
