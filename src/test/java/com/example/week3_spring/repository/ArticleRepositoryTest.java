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


@DataJpaTest
class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @BeforeEach
    void beforeEach() {
        articleRepository.deleteAll();
    }

    @Test
    @DisplayName("전체 조회 테스트")
    public void findAll() {
        //given

        //when
        List<Article> articleList = articleRepository.findAll();

        //then
//        assertEquals(articleList.size(),0);//예상값 ,실제값
    }
    //전체조회는 어떤식으로 검증을 할까...?
    //디비가 달라져도 작동하게

    @Test
    @DisplayName("단일 조회 테스트")
    public void findById() {
        //given
        Long id = 1L;

        //when
        Optional<Article> article = articleRepository.findById(id); //아이디 있는지 확인.

        //then
        if(article.isPresent()){
            assertEquals(1L,article.get().getId());
        }else{
            assertTrue(article.isEmpty());
        }
    }

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