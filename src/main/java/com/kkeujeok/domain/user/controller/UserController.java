package com.kkeujeok.domain.user.controller;

import com.kkeujeok.domain.user.domain.User;
import com.kkeujeok.domain.user.dto.LoginUserReq;
import com.kkeujeok.domain.user.dto.UserForm;
import com.kkeujeok.domain.user.dto.member.response.UserRankingResponse;
import com.kkeujeok.domain.user.dto.member.response.UserResponse;
import com.kkeujeok.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        //System.out.println("memberService = "+ memberService.getClass());
    }

    // 유라

    //회원가입
    @PostMapping("/join")
    public ResponseEntity<?> create(@RequestBody UserForm form) {
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

    // 이메일(아이디) 중복 확인
    @PostMapping("/check-email")
    public boolean checkEmailUnique(@RequestBody String email) {
        return userService.isEmailUnique(email);
    }

    //닉네임 중복 확인
    @PostMapping("/check-nickname")
    public boolean checkNicknameUnique(@RequestBody String nickname) {
        return userService.isNicknameUnique(nickname);
    }

    //로그인
    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserReq loginUserReq) {
        String token = String.valueOf(userService.login(loginUserReq));

        if (token != null) {
            // 로그인 성공에 대한 처리
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            // 로그인 실패에 대한 처리
            return new ResponseEntity<>("Login failed", HttpStatus.UNAUTHORIZED);
        }
    }

    //유저 삭제
    @DeleteMapping("/delete/{user-id}")
    public void deleteUser(@PathVariable(value = "user-id") Long userId) {
        userService.deleteUser(userId);
    }

    //로그아웃
    @PostMapping("/logout/{user-id}")
    public void logout(@PathVariable(value = "user-id") Long userId) {
        userService.logout(userId);
    }

    //비밀번호 찾기
    @GetMapping("/password/{user-email}")
    public ResponseEntity<?> getPassword(@PathVariable(value = "user-email") String email) {
        try {
            String password = userService.getPasswordByEmail(email);
            return new ResponseEntity<>("User password: " + password, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }


    // 남영

    @GetMapping("/ranking") //TOP10
    public List<UserRankingResponse> getRanking() {
        return userService.getRanking();
    }

    @GetMapping("/ranking/{userId}") //내 등수
    public int getUserRanking(@PathVariable Long userId) {
        return userService.getUserRanking(userId);
    }

    @PostMapping("/{userId}/increaseLuck")//좋아요 +1
    public void increaseLuck(@PathVariable Long userId) {
        userService.increaseLuck(userId);
    }

    @GetMapping("/{userId}/luck") //좋아요 가져오기
    public int getUserLuck(@PathVariable Long userId) {
        return userService.getUserLuck(userId);
    }

//    @GetMapping("/search/{search-word}")
//    public ResponseEntity<List<UserResponse>> searchMembers(@PathVariable(value = "search-word") String searchWord) {
//        List<UserResponse> users = userService.searchMembers(searchWord);
//        return ResponseEntity.ok(users);
//    }

    @GetMapping("/search/{search-word}")
    public List<UserResponse> searchUsers(@PathVariable(value = "search-word") String searchWord) {
        return userService.searchUsers(searchWord);
    }

    // Description : 마이페이지 조회
    @GetMapping("/myPage/{user-id}")
    public ResponseEntity<?> myPage(@PathVariable(value = "user-id") Long userId) {
        return userService.myPage(userId);

    }

}