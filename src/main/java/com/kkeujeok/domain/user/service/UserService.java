package com.kkeujeok.domain.user.service;

import com.kkeujeok.domain.rollingPaper.Dto.RollingPaperDto;
import com.kkeujeok.domain.rollingPaper.domain.RollingPaper;
import com.kkeujeok.domain.rollingPaper.repository.RollingPaperRepository;
import com.kkeujeok.domain.user.Utils.JwtUtil;
import com.kkeujeok.domain.user.domain.User;
import com.kkeujeok.domain.user.dto.EmptyUserRes;
import com.kkeujeok.domain.user.dto.FindUserRes;
import com.kkeujeok.domain.user.dto.LoginUserReq;
import com.kkeujeok.domain.user.dto.member.response.UserRankingResponse;
import com.kkeujeok.domain.user.dto.member.response.UserResponse;
import com.kkeujeok.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RollingPaperRepository rollingPaperRepository;

    private final JwtUtil jwtUtil;

    public boolean join(User user){
        if (isDuplicateUser(user)) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
        userRepository.save(user);
        return true;
    }

    @Transactional
    public String login(LoginUserReq loginUserReq) {
//        User user = checkUserInfo(loginUserReq.getEmail(), loginUserReq.getPw());
        Optional<User> userByEmail = userRepository.findByEmailAndPassword(loginUserReq.getEmail(), loginUserReq.getPw());

        if (userByEmail.isEmpty()) {
            throw new RuntimeException();
        }

        User user = userByEmail.get();
        user.setLoginStatus(true);
        userRepository.save(user); //토큰 저장

        String userToken = jwtUtil.generateToken(user);

        return userToken;
    }

    public boolean isEmailUnique(String email) {
        return !userRepository.findByEmail(email).isPresent();
    }

    public boolean isNicknameUnique(String nickname) {
        return !userRepository.findByNickname(nickname).isPresent();
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
        }
    }
    public int getUserLuck(Long userId) {
        Optional<User> member = userRepository.findById(userId);
        if (member.isPresent()) {
            User user = member.get();
            return user.getLuck();
        } else {
        }
        return 0;
    }
    public List<UserResponse> searchUsers(String searchWord) { //검색
        List<User> users = userRepository.findByEmailContaining(searchWord);
        List<UserResponse> userResponseList = new ArrayList<>();

        for (User user : users) {
            UserResponse userResponse = UserResponse.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .nickname(user.getNickname())
                    .build();
            userResponseList.add(userResponse);
        }
        return userResponseList;
    }

    // Description : 마이페이지 조회
    public ResponseEntity<?> myPage(Long userId) {
        Optional<User> userById = userRepository.findById(userId);
        if (userById.isEmpty()) {
            EmptyUserRes emptyUserRes = EmptyUserRes.builder()
                    .message("해당 ID를 가진 유저가 존재하지 않습니다.")
                    .build();

            return ResponseEntity.badRequest().body(emptyUserRes);
        }

        User user = userById.get();

        List<RollingPaper> rollingPaperList = rollingPaperRepository.findByUser(user);
        List<RollingPaperDto> rollingPaperDtos = new ArrayList<>();
        for (RollingPaper rollingPaper : rollingPaperList) {
            RollingPaperDto rollingPaperDto = RollingPaperDto.builder()
                    .id(rollingPaper.getId())
                    .content(rollingPaper.getContent())
                    .senderNickname(rollingPaper.getSenderNickname())
                    .build();

            rollingPaperDtos.add(rollingPaperDto);
        }

        FindUserRes findUserRes = FindUserRes.builder()
                .userNickName(user.getNickname())
                .gender(user.getGender())
                .rollingPaperDtos(rollingPaperDtos)
                .message("해당 유저의 마이페이지를 반환합니다.")
                .build();

        return ResponseEntity.ok(findUserRes);

    }
}
