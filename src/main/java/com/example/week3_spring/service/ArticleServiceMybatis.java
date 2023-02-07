package com.example.week3_spring.service;

import com.example.week3_spring.domain.Article;
import com.example.week3_spring.domain.ArticleReqDto;
import com.example.week3_spring.domain.ArticleResDto;
import com.example.week3_spring.repository.ArticleRepositoryMybatis;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleServiceMybatis implements ArticleServiceInterface{

    private final ArticleRepositoryMybatis articleRepositoryMybatis;


    //전체조회
    @Override
    public List<Article> findAll() {
        return articleRepositoryMybatis.findAll();
    }

    //상세조회
    @Override
    public ArticleResDto findById(Long id) {
        Article article = articleRepositoryMybatis.findById(id);

        //Dto -> Entity
        return article.ArticleToarticleResDto();
    }


    //등록
    @Override
    public Long savaArticle(ArticleReqDto articleReqDto) {
        Article article = articleReqDto.articleReqDtoToArticle();

        articleRepositoryMybatis.save(article);

        return article.getId();
    }

    //수정
    @Override
    public Long updateArticle(Long id, ArticleReqDto articleReqDto) {
        //아이디 찾기
        Article article = articleRepositoryMybatis.findById(id);

        //바꾸기
        article.changeArticle(articleReqDto);

        articleRepositoryMybatis.updateArticle(article);


        return article.getId();
    }

    @Override
    public void deleteById(Long id) {
        articleRepositoryMybatis.deleteById(id);
    }
}
