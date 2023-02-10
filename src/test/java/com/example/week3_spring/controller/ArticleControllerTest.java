package com.example.week3_spring.controller;

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
import org.springframework.transaction.annotation.Transactional;


import static com.example.week3_spring.exception.ResponseCode.SUCCESS;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("전체 조회 테스트")
    void findAll() throws Exception{
        String str = "$.[?(@.response == '%s')]";

        mockMvc.perform(get("/api/findAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(str,"OK").exists());

    }

    @Test
    @DisplayName("상세 조회 테스트")
    void findById() throws Exception{
        String str = "$.[?(@.message == '%s')]";

        mockMvc.perform(get("/api/findById/{id}",1L))
                .andExpect(status().is(404))
                .andExpect(jsonPath(str,"해당하는 게시글이 없습니다.").exists());

    }

    @Test
    @DisplayName("저장 테스트")
    void saveArticle() throws Exception{
        String str = "$.[?(@.message == '%s')]";

        ArticleReqDto articleReqDto = new ArticleReqDto();
        articleReqDto.setDate("2023/02/02");
        articleReqDto.setShowCount(11L);
        articleReqDto.setClickCount(11L);
        articleReqDto.setAdMoney(11L);
        articleReqDto.setSoldMoney(11L);
        articleReqDto.setSoldCount(11L);


        mockMvc.perform(get("/api/saveArticle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString())
                )
                .andExpect(status().is(404))
                .andExpect(jsonPath(str,"해당하는 게시글이 없습니다.").exists());
    }

    @Test
    void updateArticle() throws Exception{

    }

    @Test
    void deleteById() throws Exception{

    }
}