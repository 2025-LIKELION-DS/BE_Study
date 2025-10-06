package com.ll.demo03.domain.member.member.service;

import com.ll.demo03.domain.member.member.entity.Member;
import com.ll.demo03.domain.member.member.repository.MemberRepository;
import com.ll.demo03.global.exceptions.GlobalException;
import com.ll.demo03.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService{
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public RsData<Member> join(String username, String password, String nickname) {
         findByUsername(username).ifPresent( ignored -> {
            throw new GlobalException("401-1", "%s는 이미 사용중인 username 입니다.".formatted(username));
        });

        Member member = Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .refreshToken(UUID.randomUUID().toString())
                .build();

        memberRepository.save(member);
        return RsData.of("%s님 환영합니다.".formatted(nickname), member);
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public Member getReferenceById(long id) {
        return memberRepository.getReferenceById(id);
    }

    public long count() {
        return memberRepository.count();
    }

    public boolean matchPassword(String password, String encodedPassword) {
        return passwordEncoder.matches(password, encodedPassword);
    }
    public Optional<Member> findById(long id) {
        return memberRepository.findById(id);
    }

    public Optional<Member> findByRefreshToken(String refreshToken) {
        return memberRepository.findByRefreshToken(refreshToken);
    }
}

