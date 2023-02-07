package com.example.week3_spring.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.loader.collection.OneToManyJoinWalker;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ArticleObject {

    List<Article> dataSulbin;

}
