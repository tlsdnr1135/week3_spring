package com.example.week3_spring.service;

import com.example.week3_spring.domain.Article;
import com.example.week3_spring.domain.ArticleReqDto;
import com.example.week3_spring.domain.ArticleResDto;
import com.example.week3_spring.repository.ArticleRepository;
import com.example.week3_spring.repository.ArticleRepositoryMybatis;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService implements ArticleServiceInterface{

    private final ArticleRepository articleRepository;



    //전체 조회
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    //상세 조회
    public ArticleResDto findById(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당하는 아이디 없습니다.")
        );

        //Dto -> Entity
        return article.ArticleToarticleResDto();
    }

    //등록
    public void savaArticle(ArticleReqDto articleReqDto) {
        //바꾸기 -> Article
        Article article = articleRepository.save(articleReqDto.articleReqDtoToArticle());
        System.out.println(article.getId());
//        return article.getId();
    }

    //수정
    public Long updateArticle(Long id,ArticleReqDto articleReqDto) {
        //아이디 찾기
        Article article = articleRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("해당하는 아이디가 없습니다.")
        );

        article.changeArticle(articleReqDto);

        return article.getId();
    }

    //삭제
    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }


}
