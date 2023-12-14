package com.kkeujeok.domain.user.service;

import com.kkeujeok.domain.user.domain.User;
import com.kkeujeok.domain.user.dto.LoginUserReq;
import com.kkeujeok.domain.user.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public boolean login(LoginUserReq loginUserReq) {
//        User user = checkUserInfo(loginUserReq.getEmail(), loginUserReq.getPw());
        Optional<User> userByEmail = userRepository.findByEmail(loginUserReq.getEmail());

        if (userByEmail.isEmpty()) {
            return false;
        }

        User user = userByEmail.get();
        user.setLoginStatus(true);

        return user.getLoginStatus(); //true or false
    }

    @Transactional
    public void logout(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        user.get().setLoginStatus(false);
    }

    @Transactional
    public void deleteUser(Long userId) { //계정 삭제
        userRepository.deleteById(userId);
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
