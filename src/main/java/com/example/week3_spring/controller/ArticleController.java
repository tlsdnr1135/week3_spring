package com.example.week3_spring.controller;

import com.example.week3_spring.domain.Article;
import com.example.week3_spring.domain.ArticleReqDto;
import com.example.week3_spring.domain.ArticleResDto;
import com.example.week3_spring.repository.ArticleRepository;
import com.example.week3_spring.service.ArticleService;
import com.example.week3_spring.service.ArticleServiceInterface;
import com.example.week3_spring.service.ArticleServiceMybatis;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {

    private final ArticleServiceInterface articleServiceInterface;

    public ArticleController(ArticleService articleServiceMybatis) {
        this.articleServiceInterface = articleServiceMybatis;
    }

    //전체 조회
    @GetMapping("/api/findAll")
    public ResponseEntity<List<Article>> findAll(){
        return ResponseEntity.ok().body(articleServiceInterface.findAll());
    }

    //상세 조회
    @GetMapping("/api/findById/{id}")
    public ResponseEntity<ArticleResDto> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(articleServiceInterface.findById(id));
    }

    //저장
    @PostMapping("/api/saveArticle")
    public ResponseEntity<?> saveArticle(@RequestBody ArticleReqDto articleReqDto){
        articleServiceInterface.savaArticle(articleReqDto);
        return ResponseEntity.ok().body(null);
    }

    //수정
    @PutMapping("/api/updateArticle/{id}")
    public ResponseEntity<Long> updateArticle(@PathVariable Long id,@RequestBody ArticleReqDto articleReqDto){
        return ResponseEntity.ok().body(articleServiceInterface.updateArticle(id, articleReqDto));

    }

    //삭제
    @DeleteMapping("/api/deleteArticle/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        articleServiceInterface.deleteById(id);
        return ResponseEntity.ok().body("성공적으로 삭제.");
    }
}
