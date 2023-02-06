package com.example.week3_spring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArticleResDto {

    private String name;
    private String content;

    @Builder
    public ArticleResDto(String name, String content) {
        this.name = name;
        this.content = content;
    }
}
