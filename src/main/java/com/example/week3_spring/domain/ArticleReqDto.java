package com.example.week3_spring.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class ArticleReqDto {

    private String name;

    private String content;

    //ArticleReqDto -> Article
    public Article articleReqDtoToArticle(){
        return Article.builder()
                .name(this.getName())
                .content(this.getContent())
                .build();
    }

}
