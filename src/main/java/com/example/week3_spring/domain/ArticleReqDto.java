package com.example.week3_spring.domain;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class ArticleReqDto {


    @NotNull
    @NotBlank
    private String date;

    @NotNull
    private Long showCount; //클릭수

    @NotNull
    private Long clickCount; //노출수


    @NotNull
    private Long adMoney; //광고비

    @NotNull
    private Long soldMoney; //판매금액

    @NotNull
    private Long soldCount; //판매 수량

    //ArticleReqDto -> Article
    public Article articleReqDtoToArticle(){
        return Article.builder()
                .date(this.date)
                .showCount(this.getShowCount())
                .clickCount(this.getClickCount())
                .adMoney(this.getAdMoney())
                .soldMoney(this.getSoldMoney())
                .soldCount(this.getSoldCount())
                .build();
    }



}
