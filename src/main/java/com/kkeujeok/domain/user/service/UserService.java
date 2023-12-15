package com.kkeujeok.domain.user.service;

import com.kkeujeok.domain.user.domain.User;
import com.kkeujeok.domain.user.dto.LoginUserReq;
import com.kkeujeok.domain.user.dto.member.response.UserRankingResponse;
import com.kkeujeok.domain.user.dto.member.response.UserResponse;
import com.kkeujeok.domain.user.domain.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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

    // 남영

    public List<UserRankingResponse> getRanking() { //TOP10
        return userRepository.findAll(PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "luck")))
                .stream()
                .map(member -> new UserRankingResponse(member.getNickname(), member.getLuck()))
                .collect(Collectors.toList());
    }

    public int getUserRanking(Long userId) { //내 등수
        return userRepository.getRankingOfUser(userId) + 1;
    }

    @Transactional
    public void increaseLuck(Long memberId) { //좋아요
        Optional<User> member = userRepository.findById(memberId);

        if (member.isPresent()) {
            User user = member.get();
            user.increaseLuck();
        } else {
            // 사용자를 찾을 수 없는 경우에 대한 처리 로직을 추가할 수 있습니다.
            // 예를 들어, 사용자를 찾을 수 없다는 메시지를 로그에 남기거나 예외를 던지는 등의 처리를 할 수 있습니다.
        }
    }
    public int getUserLuck(Long userId) {
        Optional<User> member = userRepository.findById(userId);
        if (member.isPresent()) {
            User user = member.get();
            return user.getLuck();
        } else {
            // 사용자를 찾을 수 없는 경우에 대한 처리 로직을 추가할 수 있습니다.
            // 예를 들어, 사용자를 찾을 수 없다는 메시지를 로그에 남기거나 예외를 던지는 등의 처리를 할 수 있습니다.
        }
        return 0;
    }
    public List<UserResponse> searchUsers(String searchWord) { //검색
        List<User> users = userRepository.findByNicknameContaining(searchWord);
        return users.stream()
                .map(user -> new UserResponse(user.getId(), user.getNickname()))
                .collect(Collectors.toList());
    }


}
