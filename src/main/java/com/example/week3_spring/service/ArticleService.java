package com.example.week3_spring.service;

import com.example.week3_spring.domain.Article;
import com.example.week3_spring.domain.ArticleObject;
import com.example.week3_spring.domain.ArticleReqDto;
import com.example.week3_spring.domain.ArticleResDto;
import com.example.week3_spring.exception.CustomException;
import com.example.week3_spring.exception.DataResponseCode;
import com.example.week3_spring.exception.HttpResponse;
import com.example.week3_spring.exception.ResponseCode;
import com.example.week3_spring.repository.ArticleRepository;
import com.example.week3_spring.repository.ArticleRepositoryMybatis;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.week3_spring.exception.ResponseCode.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService implements ArticleServiceInterface{

    private final ArticleRepository articleRepository;



    //전체 조회
    public List<Article> findAll() {
        List<Article> articleList = articleRepository.findAll();

        //찾은 리스트Article를 -> 오브젝트로 반환하기 위해 -> 그럴 이유 있나 HttpResponse잇는데
//        ArticleObject articleObjects = new ArticleObject();
//        articleObjects.setDataSulbin(articleList);

        return articleList;
    }

    //상세 조회
    public ArticleResDto findById(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new CustomException(INVALID_ARTICLE)
        );

        //Entity -> Dto
        return article.ArticleToarticleResDto();
    }

    //등록
    public Long savaArticle(ArticleReqDto articleReqDto) {
        //바꾸기, dto -> Article
        Article article = articleReqDto.articleReqDtoToArticle();
        //전환율 계산.
        article.makeswitchCount();

        return articleRepository.save(article).getId();
    }

    //수정
    public Long updateArticle(Long id,ArticleReqDto articleReqDto) {
        //아이디 찾기
        Article article = articleRepository.findById(id).orElseThrow(
                ()->new CustomException(INVALID_ARTICLE)
        );

        article.changeArticle(articleReqDto); //수정하기
        article.makeswitchCount(); //전환율 계산

        return article.getId();
    }

    //삭제
    public void deleteById(Long id) {
        //아이디 찾기
        if(!articleRepository.existsById(id)){
            new CustomException(INVALID_ARTICLE);
        }
        articleRepository.deleteById(id);
    }


}
