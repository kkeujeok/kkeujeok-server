package com.kkeujeok.domain.user.controller;

import com.kkeujeok.domain.user.domain.User;
import com.kkeujeok.domain.user.dto.LoginUserReq;
import com.kkeujeok.domain.user.dto.PostUserReq;
import com.kkeujeok.domain.user.dto.ResponseJoin;
import com.kkeujeok.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        //System.out.println("memberService = "+ memberService.getClass());
    }

    @PostMapping("/join")
    public ResponseEntity<?> create(UserForm form) {
        User user = new User();
        user.setEmail(form.getEmail());
        user.setPW(form.getPw());
        user.setGender(form.getGender());
        user.setNickname(form.getNickname());

        try {
            userService.join(user);
            return new ResponseEntity<>("User successfully created", HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            // 회원 가입이 실패한 경우에 대한 처리
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserReq loginUserReq) {
        boolean loginResult = userService.login(loginUserReq);

        if (loginResult) {
            // 로그인 성공에 대한 처리
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            // 로그인 실패에 대한 처리
            return new ResponseEntity<>("Login failed", HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/delete")
    public void deleteUser(@RequestParam Long userIdx) {
        userService.deleteUser(userIdx);
    }
    @PostMapping("/logout")
    public void logout(@RequestParam Long userIdx) {
        userService.logout(userIdx);
    }
    @PostMapping("/password")
    public ResponseEntity<?> getPassword(@RequestParam String email) {
        try {
            String password = userService.getPasswordByEmail(email);
            return new ResponseEntity<>("User password: " + password, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
}