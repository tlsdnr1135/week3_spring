package com.example.week3_spring.repository;

import com.example.week3_spring.domain.Article;
import com.example.week3_spring.domain.ArticleReqDto;
import org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import  static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;


@DataJpaTest
class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @BeforeEach
    void beforeEach() {
        articleRepository.deleteAll();
    }

    //두개를 항상 넣음 -> 사이즈 체크 -> 2개 이상이면 통과
    //전체조회는 어떤식으로 검증을 할까...?
    @Test
    @DisplayName("전체 조회 테스트")
    public void findAll() {
        //given
        Article article1 = Article.builder()
                .date("2023/02/01")
                .showCount(12L)
                .clickCount(13L)
                .adMoney(15L)
                .soldMoney(16L)
                .soldCount(17L)
                .build();
        Article article2 = Article.builder()
                .date("2023/02/01")
                .showCount(12L)
                .clickCount(13L)
                .adMoney(15L)
                .soldMoney(16L)
                .soldCount(17L)
                .build();
        articleRepository.save(article1);
        articleRepository.save(article2);

        //when
        List<Article> articleList = articleRepository.findAll();

        //then
        assertTrue(articleList.size()>=2);
    }


    @Test
    @DisplayName("단일 조회 테스트_값이 있을 때")
    public void findById_Success() {
        //given
        Article article = Article.builder()
                .date("2023/02/01")
                .showCount(12L)
                .clickCount(13L)
                .adMoney(15L)
                .soldMoney(16L)
                .soldCount(17L)
                .build();

        Long id = articleRepository.save(article).getId();

        //when
        Optional<Article> articleOP = articleRepository.findById(id); //아이디 있는지 확인.

        //then
        assertEquals(id,articleOP.get().getId());
    }
//    @Test
//    @DisplayName("단일 조회 테스트_값이 없을 때")
//    public void findById_Fail() {
//        //given
//        Long id = articleRepository.findById();
//
//        //when
//        Optional<Article> articleOP = articleRepository.findById(id); //아이디 있는지 확인.
//
//        //then
//        assertTrue(article.isEmpty());
//
//    }

    @Test
    @DisplayName("저장 테스트")
    public void save() {
        //given
        Article article = Article.builder()
                .date("2023/02/01")
                .showCount(12L)
                .clickCount(13L)
                .adMoney(15L)
                .soldMoney(16L)
                .soldCount(17L)
                .build();
        article.makeswitchCount();

        //when
        Article result = articleRepository.save(article);

        //then
        Optional<Article> findArticle = articleRepository.findById(result.getId());
        assertEquals(result.getId(),findArticle.get().getId());
    }

    @Test
    @DisplayName("수정 테스트")
    public void update() {
        //given
        Article beforeArticle = Article.builder()
                .date("2023/02/01")
                .showCount(12L)
                .clickCount(13L)
                .adMoney(15L)
                .soldMoney(16L)
                .soldCount(17L)
                .build();
        beforeArticle.makeswitchCount();
        Article article = articleRepository.save(beforeArticle); //저장하기
        Long id = article.getId();

        ArticleReqDto afterArticle = new ArticleReqDto();
        afterArticle.setDate("2023/02/05");


        //when
        Optional<Article> result = articleRepository.findById(id);
        result.ifPresent(a -> a.changeArticle(afterArticle));


        //then
        if(result.isPresent()){
            assertThat(result.get().getDate()).isEqualTo("2023/02/05");
        }
    }

    @Test
    @DisplayName("삭제 테스트")
    public void delete() {
        //given
        Article beforeArticle = Article.builder()
                .date("2023/02/01")
                .showCount(12L)
                .clickCount(13L)
                .adMoney(15L)
                .soldMoney(16L)
                .soldCount(17L)
                .build();
        beforeArticle.makeswitchCount();

        Article article = articleRepository.save(beforeArticle); //저장하기
        Long id = article.getId();

        //when
        Optional<Article> result = articleRepository.findById(id);
        if(result.isPresent()){
            articleRepository.deleteById(id);
        }

        //then
        assertTrue(articleRepository.findById(id).isEmpty());
    }
    
    
}