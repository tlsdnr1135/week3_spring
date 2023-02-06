package com.example.week3_spring.repository;

import com.example.week3_spring.domain.Article;
import com.example.week3_spring.domain.ArticleResDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {

    List<Article> findAll();
    
}
