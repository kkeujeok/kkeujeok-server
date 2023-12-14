package com.kkeujeok.domain.friend.service;

import com.kkeujeok.domain.friend.domain.Friend;
import com.kkeujeok.domain.friend.domain.repository.FriendRepository;
import com.kkeujeok.domain.friend.dto.response.FindAllFriendsRes;
import com.kkeujeok.domain.friend.dto.response.FriendDTO;
import com.kkeujeok.domain.friend.dto.response.SendFriendRes;
import com.kkeujeok.domain.user.domain.User;
import com.kkeujeok.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    // Description : 친구 목록 조회
    public ResponseEntity<?> findAllFriends(Long userId) {

        Optional<User> userById = userRepository.findById(userId);
        if (userById.isEmpty()) {
            FindAllFriendsRes findAllFriendsRes = FindAllFriendsRes.builder()
                    .friends(null)
                    .message("해당 ID를 가진 유저를 찾을 수 없습니다.")
                    .build();

            return ResponseEntity.badRequest().body(findAllFriendsRes);
        }

        List<Friend> friendById = friendRepository.findAllByUserId(userId);

        List<FriendDTO> friendDTOS = new ArrayList<>();

        for (Friend friend : friendById) {
            FriendDTO friendDTO = FriendDTO.builder()
                    .id(friend.getFriend().getId())
                    .name(friend.getFriend().getNickname())
                    .build();

            friendDTOS.add(friendDTO);
        }

        FindAllFriendsRes findAllFriendsRes = FindAllFriendsRes.builder()
                .friends(friendDTOS)
                .message("친구 목록을 반환합니다.")
                .build();

        return ResponseEntity.ok(findAllFriendsRes);

    }

    // Description : 친구 신청
    @Transactional
    public ResponseEntity<?> sendFriend(Long userId, Long friendId) {

        Optional<User> userById = userRepository.findById(userId);
        if (userById.isEmpty()) {
            SendFriendRes sendFriendRes = SendFriendRes.builder()
                    .message("해당 ID를 가진 유저가 존재하지 않습니다.")
                    .build();

            return ResponseEntity.badRequest().body(sendFriendRes);
        }

        Optional<User> friendById = userRepository.findById(friendId);
        if (friendById.isEmpty()) {
            SendFriendRes sendFriendRes = SendFriendRes.builder()
                    .message("해당 ID를 가진 친구가 존재하지 않습니다.")
                    .build();

            return ResponseEntity.badRequest().body(sendFriendRes);
        }

        User user = userById.get();
        User friend = friendById.get();

        Friend newFromFriend = Friend.builder()
                .user(user)
                .friend(friend)
                .build();

        friendRepository.save(newFromFriend);

        Friend newToFriend = Friend.builder()
                .user(friend)
                .friend(user)
                .build();

        friendRepository.save(newToFriend);

        SendFriendRes sendFriendRes = SendFriendRes.builder()
                .message("친구 신청을 보냈습니다.")
                .build();

        return ResponseEntity.ok(sendFriendRes);
    }

    // Description : 친구 삭제
    @Transactional
    public ResponseEntity<?> deleteFriend(Long userId, Long friendId) {

        Optional<User> userById = userRepository.findById(userId);
        if (userById.isEmpty()) {
            SendFriendRes sendFriendRes = SendFriendRes.builder()
                    .message("해당 ID를 가진 유저가 존재하지 않습니다.")
                    .build();

            return ResponseEntity.badRequest().body(sendFriendRes);
        }

        Optional<User> friendById = userRepository.findById(friendId);
        if (friendById.isEmpty()) {
            SendFriendRes sendFriendRes = SendFriendRes.builder()
                    .message("해당 ID를 가진 친구가 존재하지 않습니다.")
                    .build();

            return ResponseEntity.badRequest().body(sendFriendRes);
        }

        FriendDTO friendDTO = null;
        List<FriendDTO> friendDTOS = new ArrayList<>();

        List<Friend> friendListById = friendRepository.findAllByUserId(userId);
        for (Friend friend : friendListById) {
            if (friend.getUser().getId() == userId && friend.getFriend().getId() == friendId) {
                friendRepository.deleteById(friend.getId());
                friendDTO = FriendDTO.builder()
                        .id(friend.getFriend().getId())
                        .name(friend.getFriend().getNickname())
                        .build();

                friendDTOS.add(friendDTO);
            }

        }

        List<Friend> friendListByFriendId = friendRepository.findAllByFriendId(friendId);
        for (Friend friend : friendListByFriendId) {
            if (friend.getUser().getId() == friendId && friend.getFriend().getId() == userId) {
                friendRepository.deleteById(friend.getId());
                friendDTO = FriendDTO.builder()
                        .id(friend.getFriend().getId())
                        .name(friend.getFriend().getNickname())
                        .build();

                friendDTOS.add(friendDTO);
            }

        }

        FindAllFriendsRes findAllFriendsRes = FindAllFriendsRes.builder()
                .friends(friendDTOS)
                .message("친구 삭제가 완료되었습니다. 삭제된 친구 목록을 반환합니다.")
                .build();

        return ResponseEntity.ok(findAllFriendsRes);

    }
}
