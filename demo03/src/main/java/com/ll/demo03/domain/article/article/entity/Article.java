package com.ll.demo03.domain.article.article.entity;

import com.ll.demo03.domain.member.member.entity.Member;
import com.ll.demo03.global.jpa.entity.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class Article extends BaseTime {
    private String title;
    @Column(columnDefinition = "TEXT")
    private String body;
    //private Long authorId; // 1,2,3 넣는거 아래랑 같음
    @ManyToOne //하나의 회원이 여러 아티클 사용
    private Member author;



}
