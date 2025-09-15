package com.ll.demo03.domain.member.member.service;

import com.ll.demo03.domain.member.member.entity.Member;
import com.ll.demo03.domain.member.member.repository.MemberRepository;
import com.ll.demo03.global.exceptions.GlobalException;
import com.ll.demo03.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 조회 결과가 최대 1개면 optional 리턴 , 아니면 list
    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);

    }

    @Transactional //private 엔 안 먹음
    public RsData<Member> join(String username, String password, String nickname) {
        findByUsername(username).ifPresent(ignored -> {
            throw new GlobalException("400-1", "%s(은)는 이미 존재하는 아이디입니다.".formatted(username));
        });

        Member member = Member.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .build();

        memberRepository.save(member);

        return RsData.of("회원가입이 완료되었습니다.", member);
    }

    public Member getReferenceById(long id) {
        return memberRepository.getReferenceById(id);
    }

    public long count() {
        return memberRepository.count();
    }
}
