package com.example.week3_spring.service;

import com.example.week3_spring.domain.Article;
import com.example.week3_spring.domain.ArticleReqDto;
import com.example.week3_spring.domain.ArticleResDto;

import java.util.List;

public interface ArticleServiceInterface {

    //전체 조회
    public List<Article> findAll();

    //상세 조회
    public ArticleResDto findById(Long id);

    //등록
    public Long savaArticle(ArticleReqDto articleReqDto);

    //수정
    public Long updateArticle(Long id,ArticleReqDto articleReqDto);

    //삭제
    public void deleteById(Long id);
}
