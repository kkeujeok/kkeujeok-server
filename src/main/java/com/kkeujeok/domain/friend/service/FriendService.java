package com.kkeujeok.domain.friend.service;

import com.kkeujeok.domain.friend.domain.Friend;
import com.kkeujeok.domain.friend.domain.repository.FriendRepository;
import com.kkeujeok.domain.friend.dto.response.FindAllFriendsRes;
import com.kkeujeok.domain.user.domain.Member;
import com.kkeujeok.domain.user.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FriendService {

    private final FriendRepository friendRepository;
    private final MemberRepository memberRepository;

    // Description : 친구 목록 조회
    public ResponseEntity<?> findAllFriends(Long userId) {

        Optional<Member> userById = memberRepository.findMemberWithFriend(userId);
        if (userById.isEmpty()) {
            FindAllFriendsRes findAllFriendsRes = FindAllFriendsRes.builder()
                    .friends(null)
                    .message("해당 ID를 가진 유저를 찾을 수 없습니다.")
                    .build();

            return ResponseEntity.badRequest().body(findAllFriendsRes);
        }

        Member member = userById.get();
        List<Friend> friends = member.getFriends();

        FindAllFriendsRes findAllFriendsRes = FindAllFriendsRes.builder()
                .friends(null)
                .message("친구 목록을 반환합니다.")
                .build();

        return ResponseEntity.ok(findAllFriendsRes);

    }


}
