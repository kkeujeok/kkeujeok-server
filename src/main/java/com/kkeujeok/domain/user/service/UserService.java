package com.kkeujeok.domain.user.service;

import com.kkeujeok.domain.user.domain.User;
import com.kkeujeok.domain.user.dto.LoginUserReq;
import com.kkeujeok.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public boolean join(User user){
        if (isDuplicateUser(user)) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
        userRepository.save(user);
        return true;
    }

    public Boolean login(LoginUserReq loginUserReq) {
        User user = checkUserInfo(loginUserReq.getEmail(), loginUserReq.getPw());

        if (user != null) {
            user.login();
            // 여기에서 로그인 성공에 대한 추가 로직을 수행할 수 있습니다.
        }
        return user.getLoginStatus(); //true or false
    }

    public void logout(Long userIdx) {
        Optional<User> user = userRepository.findById(userIdx);
        user.get().setLoginStatus(false);
    }

    public void deleteUser(Long userIdx) { //계정 삭제
        userRepository.deleteById(userIdx);
    }

    public String getPasswordByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("User not found for email: " + email));
        return user.getPassword();
    }


    private boolean isDuplicateUser(User user) {
        return userRepository.findByEmail(user.getEmail()).isPresent();
    }


    private User checkUserInfo(String email, String password) {
        User user = userRepository.findByEmail(email).orElse(null);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null; // 사용자 정보가 일치하지 않을 경우 null을 반환합니다.
        }
    }

}
