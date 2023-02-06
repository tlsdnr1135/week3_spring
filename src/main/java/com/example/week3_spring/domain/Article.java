package com.example.week3_spring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String content;

    @Builder
    public Article(Long id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    //Article -> ArticleResDto
    public ArticleResDto ArticleToarticleResDto(){
        return ArticleResDto.builder()
                .name(this.name)
                .content(this.content)
        .build();
    }

    //수정
    public void changeArticle(ArticleReqDto articleReqDto){
        this.name = articleReqDto.getName();
        this.content = articleReqDto.getContent();
    }


}
