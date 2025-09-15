package com.ll.demo03.global.rq;

import com.ll.demo03.domain.member.member.entity.Member;
import com.ll.demo03.domain.member.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope // Rq의 객체가 요청이 들어올 때마다 하나씩 생김
@RequiredArgsConstructor
public class Rq {
    private final MemberService memberService;

    public Member getMember() {
        return memberService.getReferenceById(1L);
    }
}
