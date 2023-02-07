package com.example.week3_spring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ArticleResDto {

    private String date;


    private Long showCount; //클릭수


    private Long clickCount; //노출수

    private Double switchCount; //전환율%(클릭수 / 노출수)


    private Long adMoney; //광고비


    private Long soldMoney; //판매금액


    private Long soldCount; //판매 수량

    @Builder
    public ArticleResDto(String date,Long showCount, Long clickCount, Double switchCount, Long adMoney, Long soldMoney, Long soldCount) {
        this.date = date;
        this.showCount = showCount;
        this.clickCount = clickCount;
        this.switchCount = switchCount;
        this.adMoney = adMoney;
        this.soldMoney = soldMoney;
        this.soldCount = soldCount;
    }
}
