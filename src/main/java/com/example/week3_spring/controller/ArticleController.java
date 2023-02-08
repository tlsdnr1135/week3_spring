package com.example.week3_spring.controller;

import com.example.week3_spring.domain.Article;
import com.example.week3_spring.domain.ArticleObject;
import com.example.week3_spring.domain.ArticleReqDto;
import com.example.week3_spring.domain.ArticleResDto;
import com.example.week3_spring.domain.validator.ArticleReqDtoValidator;
import com.example.week3_spring.exception.CustomException;
import com.example.week3_spring.exception.DataResponseCode;
import com.example.week3_spring.exception.HttpResponse;
import com.example.week3_spring.repository.ArticleRepository;
import com.example.week3_spring.repository.BoardRepository;
import com.example.week3_spring.service.ArticleService;
import com.example.week3_spring.service.ArticleServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.week3_spring.exception.ResponseCode.INVALID_FAIL;
import static com.example.week3_spring.exception.ResponseCode.SUCCESS;

@RestController
public class ArticleController {

    private final ArticleServiceInterface articleServiceInterface;
    private final ArticleReqDtoValidator articleReqDtoValidator;
    private final BoardRepository boardRepository;

    public ArticleController(ArticleService service, ArticleReqDtoValidator articleReqDtoValidator, BoardRepository boardRepository) {
        this.articleServiceInterface = service;
        this.articleReqDtoValidator = articleReqDtoValidator;
        this.boardRepository = boardRepository;
    }

//    @InitBinder("articleReqDto")
//    public void InitBinder(WebDataBinder webDataBinder) {
//        webDataBinder.addValidators(articleReqDtoValidator);
//    }



    //전체 조회
    @GetMapping("/api/findAll")
    public ResponseEntity<HttpResponse> findAll() throws CustomException{
        boardRepository.findAll();
        System.out.println("안이얌");
        return HttpResponse.toDataHttpResponse(new DataResponseCode(SUCCESS,articleServiceInterface.findAll()));
    }

    //상세 조회
    @GetMapping("/api/findById/{id}")
    public ResponseEntity<HttpResponse> findById(@PathVariable Long id){
        return HttpResponse.toDataHttpResponse(new DataResponseCode(SUCCESS,articleServiceInterface.findById(id)));
    }

    //저장
    @PostMapping("/api/saveArticle")
    public ResponseEntity<HttpResponse> saveArticle(@Validated @RequestBody ArticleReqDto articleReqDto, Errors errors){
        //타입 맞지 않을 경우
        if(errors.hasErrors()){
            return HttpResponse.toHttpResponse(INVALID_FAIL);
        }

        return HttpResponse.toDataHttpResponse(new DataResponseCode(SUCCESS,articleServiceInterface.savaArticle(articleReqDto)));
    }

    //수정
    @PutMapping("/api/updateArticle/{id}")
    public ResponseEntity<HttpResponse> updateArticle(@PathVariable Long id,@Validated @RequestBody ArticleReqDto articleReqDto, Errors errors){
        //타입 맞지 않을 경우
        if(errors.hasErrors()){
            return HttpResponse.toHttpResponse(INVALID_FAIL);
        }


        return HttpResponse.toDataHttpResponse(new DataResponseCode(SUCCESS,articleServiceInterface.updateArticle(id, articleReqDto)));
    }

    //삭제
    @DeleteMapping("/api/deleteArticle/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        articleServiceInterface.deleteById(id);
        return HttpResponse.toHttpResponse(SUCCESS);
    }
}
