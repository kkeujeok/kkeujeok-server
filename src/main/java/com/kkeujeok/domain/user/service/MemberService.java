//package com.kkeujeok.domain.user.service;
//
//import com.kkeujeok.domain.user.domain.Member;
//import com.kkeujeok.domain.user.domain.repository.MemberRepository;
//import com.kkeujeok.domain.user.dto.member.response.MemberRankingResponse;
//import com.kkeujeok.domain.user.dto.member.response.MemberResponse;
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class MemberService {
//    private final MemberRepository memberRepository;
//    @Autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    public List<MemberRankingResponse> getRanking() { //TOP10
//        return memberRepository.findAll(PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "luck")))
//                .stream()
//                .map(member -> new MemberRankingResponse(member.getNickname(), member.getLuck()))
//                .collect(Collectors.toList());
//    }
//    public int getUserRanking(Long userId) { //내 등수
//        return memberRepository.getRankingOfUser(userId) + 1;
//    }
//    public void increaseLuck(Long memberId) { //좋아요
//        Member member = memberRepository.findById(memberId)
//                .orElseThrow(() -> new EntityNotFoundException("Member not found"));
//        member.increaseLuck();
//        memberRepository.save(member);
//    }
//    public List<MemberResponse> searchMembers(String searchWord) { //검색
//        List<Member> members = memberRepository.findByNicknameContaining(searchWord);
//        return members.stream()
//                .map(member -> new MemberResponse(member.getId(), member.getNickname(), member.getEmail()))
//                .collect(Collectors.toList());
//    }
//
//}
