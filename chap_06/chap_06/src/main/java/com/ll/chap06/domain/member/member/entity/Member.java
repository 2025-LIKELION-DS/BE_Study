package com.ll.chap06.domain.member.member.entity;

import com.ll.chap06.global.jpa.entity.BaseTime;
import jakarta.persistence.Entity;
import lombok.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Builder
@Getter
@Setter
public class Member extends BaseTime {
    private String username;
    private String password;
    private String nickname;
}

