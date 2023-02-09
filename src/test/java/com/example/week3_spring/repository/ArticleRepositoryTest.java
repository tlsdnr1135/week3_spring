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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

//    @BeforeEach
//    void beforeEach() {
//        articleRepository.deleteAll();
//    }

    @Test
    @DisplayName("전체 조회 테스트")
    public void findAll() {
        //given

        //when
        List<Article> articleList = articleRepository.findAll();

        //then
        assertThat
        assertEquals(articleList.size(),0);//예상값 ,실제값
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
    //

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
        assertEquals(result.getId(),findArticle.get());
    }

    @Test
    @DisplayName("수정 테스트")
    public void update() {
        //given
        Long id = 1L;
        Article beforeArticle = Article.builder()
                .date("2023/02/01")
                .showCount(12L)
                .clickCount(13L)
                .adMoney(15L)
                .soldMoney(16L)
                .soldCount(17L)
                .build();
        beforeArticle.makeswitchCount();
        articleRepository.save(beforeArticle); //저장하기

        ArticleReqDto afterArticle = new ArticleReqDto();
        afterArticle.setDate("2023/02/05");


        //when
        Optional<Article> result = articleRepository.findById(id);
        result.ifPresent(article -> article.changeArticle(afterArticle));



        //then
        //다시 파인드 바이 아이디
        org.assertj.core.api.Assertions.assertThat(result).isNotEmpty();
        assertEquals(result.get().getId(),1L);
        assertEquals(result.get().getDate(),"2023/02/05");
    }

    //저장하는 것도 given에 해당하는 것인가?
    //옵셔널이 붙었을 때는 어떻게해야하나?

    @Test
    @DisplayName("삭제 테스트")
    public void delete() {
        //given
        Long id = 1L;
        Article beforeArticle = Article.builder()
                .date("2023/02/01")
                .showCount(12L)
                .clickCount(13L)
                .adMoney(15L)
                .soldMoney(16L)
                .soldCount(17L)
                .build();
        beforeArticle.makeswitchCount();
        articleRepository.save(beforeArticle); //저장하기

        //when
        Optional<Article> result = articleRepository.findById(id);
        if(result.isPresent()){
            System.out.println(result.isPresent());
            articleRepository.deleteById(id);
        }

        //then
        assertTrue(articleRepository.findById(id).isEmpty());
    }
    
    
}