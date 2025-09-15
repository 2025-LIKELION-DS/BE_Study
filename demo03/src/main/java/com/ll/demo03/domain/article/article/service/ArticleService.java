package com.ll.demo03.domain.article.article.service;

import com.ll.demo03.domain.article.article.entity.Article;
import com.ll.demo03.domain.article.article.repository.ArticleRepository;
import com.ll.demo03.domain.member.member.entity.Member;
import com.ll.demo03.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class ArticleService {
    private final ArticleRepository articleRepository;

    public long count() {
        return articleRepository.count();
    }
    //리턴
    // - 이번에 생성된 게시물의 번호
    // - 게시물 생성에 대한 결과 메시지
    // - 결과 코드
    // - 10분안에 5개 이상 -> 실패가 나올수잇음
    @Transactional //하나로 묶는 느낌 -> 오류날때 취소시켜줌
    public RsData<Article> write(Member author, String title, String body) {
        Article article = Article
                .builder()
                .author(author)
                .title(title)
                .body(body)
                .build();

        articleRepository.save(article);

        return RsData.of("%d번 게시물이 작성되었습니다.".formatted(article.getId()), article);
        //단순히 성공실패 만 보여주고싶으면 return RsData.OK 로 쓰면됨
    }

    @Transactional
    public void delete(Article article) {
        articleRepository.delete(article);
    }

    public Optional<Article> findById(long id) {
        return findById(id);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }
}
