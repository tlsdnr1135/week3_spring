package com.example.week3_spring.repository;

import com.example.week3_spring.domain.Article;
import com.example.week3_spring.domain.ArticleResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleRepositoryMybatis {

    //전체 조회
    List<Article> findAll();

    //상세 조회
    Article findById(Long id);

    //등록
    void save(Article articleReqDtoToArticle);

    //수정
     void updateArticle(Article article);


    //삭제
    void deleteById(Long id);


}
