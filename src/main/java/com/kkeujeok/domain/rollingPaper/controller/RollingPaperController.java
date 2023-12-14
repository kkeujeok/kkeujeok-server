package com.kkeujeok.domain.rollingPaper.controller;

import com.kkeujeok.domain.rollingPaper.Dto.RollingPaperDto;
import com.kkeujeok.domain.rollingPaper.Dto.RollingPaperPostDto;
import com.kkeujeok.domain.rollingPaper.service.RollingPaperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rolling-papers")
public class RollingPaperController {

    private final RollingPaperService rollingPaperService;

    /**
     * 롤링페이퍼 작성
     * {post-user-id} 는 작성하는 유저의 id   {target-user-id} 는 해당 롤링페이퍼의 유저의 id   입니다.
     */
    @PostMapping("/{post-user-id}/{target-user-id}")
    public ResponseEntity<?> post(@PathVariable("post-user-id") Long postUserId,@PathVariable("target-user-id") Long targetUserId, @RequestBody RollingPaperPostDto rollingPaperPostDto) {
        return rollingPaperService.post(postUserId,targetUserId,rollingPaperPostDto);
    }

    /**
     * 롤링페이퍼 삭제
     * 롤링페이퍼의 아이디를 보내주면 삭제입니다.
     */
    @DeleteMapping("/{rolling-paper-id}")
    public ResponseEntity<Void> delete(@PathVariable("rolling-paper-id") Long id){
        rollingPaperService.delete(id);
        return ResponseEntity.ok().build();
    }

    /**
     * 롤링페이퍼 개수 반환
     * 확인하고싶은 {user-id} 를 보내주게되면 해당 id의 롤링페이퍼의 개수를 반환합니다.
     */
    @GetMapping("/count/{user-id}")
    public ResponseEntity<Integer> getCount(@PathVariable("user-id") Long userId){
        int count = rollingPaperService.getCount(userId);
        return ResponseEntity.status(HttpStatus.OK).body(count);
    }

    /**
     * 롤링페이퍼 목록
     * 확인하고싶은 {user-id} 를 보내주게되면 해당 유저의 롤링페이퍼 목록을 반환합니다.
     */
    @GetMapping("/{user-id}")
    public ResponseEntity<List<RollingPaperDto>> getList(@PathVariable("user-id") Long userId){
        List<RollingPaperDto> dtos = rollingPaperService.getList(userId);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    /**
     * 롤링페이퍼 상세
     * 확인하고싶은 {rolling-paper-id}를 보내주게되면 해당 id의 롤링페이퍼를 상세 반환
     */
    @GetMapping("/detail/{rolling-paper-id}")
    public ResponseEntity<RollingPaperDto> getPaper(@PathVariable("rolling-paper-id") Long id){
        RollingPaperDto dto = rollingPaperService.getPaper(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);

    }

}
