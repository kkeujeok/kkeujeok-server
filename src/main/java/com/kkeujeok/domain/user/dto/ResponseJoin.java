package com.kkeujeok.domain.user.dto;

import com.kkeujeok.domain.user.domain.User;

public class ResponseJoin {

    private String message;
    private User user; // 사용자 정보

    // 생성자, 게터, 세터 등 필요한 코드

    public static ResponseJoin OK(User user) {
        ResponseJoin response = new ResponseJoin();
        response.setMessage("회원가입이 성공적으로 완료되었습니다.");
        response.setUser(user);
        return response;
    }


    public static ResponseJoin failure(String errorMessage) {
        ResponseJoin response = new ResponseJoin();
        response.setMessage("회원가입에 실패했습니다. " + errorMessage);
        return response;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUser(User user) {
        this.user = user;
    }

}