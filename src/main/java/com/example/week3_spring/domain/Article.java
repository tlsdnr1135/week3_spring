package com.example.week3_spring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;
    private Long showCount; //클릭수
    private Long clickCount; //노출수
    private Double switchCount; //전환율%(클릭수 / 노출수)
    private Long adMoney; //광고비
    private Long soldMoney; //판매금액
    private Long soldCount; //판매 수량

    @Builder
    public Article(Long id,String date, Long showCount, Long clickCount, Double switchCount, Long adMoney, Long soldMoney, Long soldCount) {
        this.id = id;
        this.date = date;
        this.showCount = showCount;
        this.clickCount = clickCount;
        this.switchCount = switchCount;
        this.adMoney = adMoney;
        this.soldMoney = soldMoney;
        this.soldCount = soldCount;
    }

    //Article -> ArticleResDto
    public ArticleResDto ArticleToarticleResDto(){
        return ArticleResDto.builder()
                .date(this.date)
                .showCount(this.showCount)
                .clickCount(this.clickCount)
                .switchCount(this.switchCount)
                .adMoney(this.adMoney)
                .soldMoney(this.soldMoney)
                .soldCount(this.soldCount)
        .build();
    }

    //수정
    public void makeswitchCount(){
        this.switchCount = Double.valueOf(clickCount)/Double.valueOf(showCount);
    }


    public void changeArticle(ArticleReqDto articleReqDto) {
        this.date = articleReqDto.getDate();
        this.clickCount = articleReqDto.getClickCount();
        this.showCount = articleReqDto.getShowCount();
        this.adMoney = articleReqDto.getAdMoney();
        this.soldMoney = articleReqDto.getSoldMoney();
        this.soldCount = articleReqDto.getSoldCount();
    }
}
