package com.kkeujeok.domain.user.controller;

import com.kkeujeok.domain.user.dto.member.response.MemberRankingResponse;
import com.kkeujeok.domain.user.dto.member.response.MemberResponse;
import com.kkeujeok.domain.user.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class MemberController {
    private final MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/ranking") //TOP10
    public List<MemberRankingResponse> getRanking() {
        return memberService.getRanking();
    }
    @GetMapping("/members/ranking/{userId}") //내 등수
    public Integer getUserRanking(@PathVariable Long userId) {
        return memberService.getUserRanking(userId);
    }
    @PostMapping("/members/{userId}/luck")
    public void increaseLuck(@PathVariable Long userId) {
        memberService.increaseLuck(userId);
    }
    @GetMapping("/members/{search-word}")
    public ResponseEntity<List<MemberResponse>> searchMembers(@PathVariable String searchWord) {
        List<MemberResponse> members = memberService.searchMembers(searchWord);
        return ResponseEntity.ok(members);
    }

}
