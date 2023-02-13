package com.example.week3_spring.controller;

import com.example.week3_spring.domain.Article;
import com.example.week3_spring.domain.ArticleReqDto;
import com.example.week3_spring.exception.DataResponseCode;
import com.example.week3_spring.exception.HttpResponse;
import com.example.week3_spring.repository.ArticleRepository;
import com.example.week3_spring.service.ArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.transaction.annotation.Transactional;


import static com.example.week3_spring.exception.ResponseCode.SUCCESS;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Transactional
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ArticleService articleService;
    
    @Autowired
    private ArticleRepository articleRepository;


    @Autowired
    private ObjectMapper objectMapper;


    
    //파라미터 라이즈드?추가하기
    @Test
    @DisplayName("전체 조회 테스트")
    void findAll() throws Exception{
        String str = "$.[?(@.response == '%s')]";

        mockMvc.perform(get("/api/findAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(str,"OK").exists());

    }

    @Test
    @DisplayName("상세_조회_테스트_성공")
    void findById_SUCESS() throws Exception{
        String str = "$.[?(@.message == '%s')]";

        mockMvc.perform(get("/api/findById/{id}",1L))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath(str,"해당하는 게시글이 없습니다.").exists());

    }
    @Test
    @DisplayName("상세_조회_테스트_예외처리_파라미터_오류")
    void findById_ID_ERROR() throws Exception{
        String str = "$.[?(@.response == '%s')]";

        mockMvc.perform(get("/api/findById/{id}","ㅁㄴㅇ"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath(str,"Bad Request").exists());
    }

    @Test
    @DisplayName("저장_테스트_성공")
    void saveArticle_SUCCESS() throws Exception{
        String str = "$.[?(@.message == '%s')]";
        String str2 = "$.[?(@.data == '%d')]";
//        String date = "$..data[?(@.date == '%s')]";

        ArticleReqDto articleReqDto = new ArticleReqDto();
        articleReqDto.setDate("2023/02/02");
        articleReqDto.setShowCount(11L);
        articleReqDto.setClickCount(11L);
        articleReqDto.setAdMoney(11L);
        articleReqDto.setSoldMoney(11L);
        articleReqDto.setSoldCount(11L);


        mockMvc.perform(post("/api/saveArticle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(articleReqDto))
                )
                .andExpect(status().isOk()) //바꾸기
                .andExpect(jsonPath(str,"성공").exists())
                .andExpect(jsonPath("$.data").isNumber());
    }
    @Test
    @DisplayName("저장_테스트_예외처리_파라미터_DTO_오류")
    void saveArticle_DTO_ERROR() throws Exception{
        String str = "$.[?(@.message == '%s')]";
        String str2 = "$.[?(@.data == '%d')]";
//        String date = "$..data[?(@.date == '%s')]";

        ArticleReqDto articleReqDto = new ArticleReqDto();
        articleReqDto.setDate("2023/02/02");
        articleReqDto.setShowCount(null);
        articleReqDto.setClickCount(11L);
        articleReqDto.setAdMoney(11L);
        articleReqDto.setSoldMoney(11L);
        articleReqDto.setSoldCount(11L);


        mockMvc.perform(post("/api/saveArticle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(articleReqDto))
                )
                .andExpect(status().is4xxClientError()) //바꾸기
                .andExpect(jsonPath(str,"형식이 잘못되었습니다.").exists());
    }

    @Test
    @DisplayName("수정 테스트 실패")
    void updateArticleFail() throws Exception{
        String str = "$.[?(@.message == '%s')]";
        String str2 = "$.[?(@.data == '%d')]";

        ArticleReqDto articleReqDto = new ArticleReqDto();
        articleReqDto.setDate("2023/02/02");
        articleReqDto.setShowCount(11L);
        articleReqDto.setClickCount(11L);
        articleReqDto.setAdMoney(11L);
        articleReqDto.setSoldMoney(11L);
        articleReqDto.setSoldCount(11L);

        mockMvc.perform(put("/api/updateArticle/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(articleReqDto))
                )
                .andExpect(status().is(404))
                .andExpect(jsonPath(str,"해당하는 게시글이 없습니다.").exists());
    }
    @Test
    @DisplayName("수정 테스트 성공")
    void updateArticleSuccess() throws Exception{
        String str = "$.[?(@.message == '%s')]";
        String str2 = "$.[?(@.data == '%d')]";
        String date = "$..data[?(@.date == '%s')]";

        ArticleReqDto articleReqDto = new ArticleReqDto();
        articleReqDto.setDate("2023/02/05");
        articleReqDto.setShowCount(11L);
        articleReqDto.setClickCount(11L);
        articleReqDto.setAdMoney(11L);
        articleReqDto.setSoldMoney(11L);
        articleReqDto.setSoldCount(11L);

        Article articleToDto = articleReqDto.articleReqDtoToArticle();


        Article article = Article.builder()
                .id(1L)
                .date("2023/02/02")
                .showCount(112L)
                .clickCount(113L)
                .adMoney(115L)
                .soldMoney(116L)
                .soldCount(117L)
                .build();
        Long articleId = articleRepository.save(article).getId();
        
        

        mockMvc.perform(put("/api/updateArticle/{id}",articleId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(articleToDto))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath(str,"성공").exists())
                .andExpect(jsonPath(str2,articleId).exists());
    }

    @Test
    @DisplayName("수정_테스트_예외처리_DTO")
    void updateArticle_DTO_ERROR() throws Exception{
        String str = "$.[?(@.message == '%s')]";
        String str2 = "$.[?(@.data == '%d')]";
        String date = "$..data[?(@.date == '%s')]";

        ArticleReqDto articleReqDto = new ArticleReqDto();
        articleReqDto.setDate("2023/02/05");
        articleReqDto.setShowCount(null);
        articleReqDto.setClickCount(11L);
        articleReqDto.setAdMoney(11L);
        articleReqDto.setSoldMoney(11L);
        articleReqDto.setSoldCount(11L);

        mockMvc.perform(put("/api/updateArticle/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(articleReqDto))
                )
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath(str,"형식이 잘못되었습니다.").exists());
    }

    @Test
    @DisplayName("삭제_테스트_성공")
    void deleteById_SUCCESS() throws Exception{
        String str = "$.[?(@.message == '%s')]";
        String str2 = "$.[?(@.data == '%d')]";

        Article article = Article.builder()
                .id(1L)
                .date("2023/02/02")
                .showCount(112L)
                .clickCount(113L)
                .adMoney(115L)
                .soldMoney(116L)
                .soldCount(117L)
                .build();
        Long articleId = articleRepository.save(article).getId();



        mockMvc.perform(delete("/api/deleteArticle/{id}",articleId))
                .andExpect(status().isOk())
                .andExpect(jsonPath(str,"성공").exists());
    }
    @Test
    @DisplayName("삭제_테스트_오류처리")
    void deleteById() throws Exception{
        String str = "$.[?(@.response == '%s')]";

        mockMvc.perform(delete("/api/deleteArticle/{id}","ㅁㄴㅇ"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath(str,"Bad Request").exists());
    }
}