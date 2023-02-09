package com.example.week3_spring.service;

import com.example.week3_spring.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleService  articleService;

//    @Autowired
//    private ArticleService articleService;

    @Test
    @DisplayName("전체 조회")
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void savaArticle() {
    }

    @Test
    void updateArticle() {
    }

    @Test
    void deleteById() {
    }
}