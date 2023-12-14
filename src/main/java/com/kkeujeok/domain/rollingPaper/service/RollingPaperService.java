package com.kkeujeok.domain.rollingPaper.service;

import com.kkeujeok.domain.rollingPaper.Dto.CreateRollingPaperRes;
import com.kkeujeok.domain.rollingPaper.Dto.RollingPaperDto;
import com.kkeujeok.domain.rollingPaper.Dto.RollingPaperPostDto;
import com.kkeujeok.domain.rollingPaper.domain.RollingPaper;
import com.kkeujeok.domain.rollingPaper.repository.RollingPaperRepository;
import com.kkeujeok.domain.user.domain.User;
import com.kkeujeok.domain.user.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RollingPaperService {

    private final RollingPaperRepository rollingPaperRepository;
    private final UserRepository userRepository;

    public List<RollingPaperDto> getList(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(()->new EntityNotFoundException("해당 맴버 엔티티가 없습니다."));
        List<RollingPaper> rollingPapers = user.getRollingPapers();
        if(rollingPapers.isEmpty()){
            throw new EntityNotFoundException("해당 맴버의 롤링페이퍼가 없습니다.");
        }

        return rollingPapers
                .stream()
                .map(RollingPaperDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public ResponseEntity<?> post(Long postUserId, Long targetUserId, RollingPaperPostDto rollingPaperPostDto) {
        User user = userRepository.findById(targetUserId)
                .orElseThrow(()-> new EntityNotFoundException("해당 맴버 엔티티가 없습니다."));

        String senderNickname = userRepository.findById(postUserId).get().getNickname();

        RollingPaper rollingPaper = RollingPaper.builder()
                .content(rollingPaperPostDto.getContent())
                .senderNickname(senderNickname)
                .user(user)
                .build();

        rollingPaperRepository.save(rollingPaper);

        CreateRollingPaperRes createRollingPaperRes = CreateRollingPaperRes.builder()
                .id(rollingPaper.getId())
                .content(rollingPaper.getContent())
                .senderNickname(rollingPaper.getSenderNickname())
                .build();


        RollingPaper entity = rollingPaperRepository.save(rollingPaper);
        return ResponseEntity.ok(createRollingPaperRes);
    }

    @Transactional
    public void delete(Long id) {
        rollingPaperRepository.deleteById(id);
    }


    public int getCount(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new EntityNotFoundException("해당 맴버 엔티티가 없습니다."));

        List<RollingPaper> list = rollingPaperRepository.findByUser(user);
        return list.size();
    }

    public RollingPaperDto getPaper(Long id) {
        RollingPaper rollingPaper = rollingPaperRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("해당 롤링페이퍼 엔티티가 없습니다."));
        return RollingPaperDto.from(rollingPaper);
    }
}
