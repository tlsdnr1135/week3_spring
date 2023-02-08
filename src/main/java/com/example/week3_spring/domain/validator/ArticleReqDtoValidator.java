package com.example.week3_spring.domain.validator;

import com.example.week3_spring.domain.ArticleReqDto;
import com.example.week3_spring.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class ArticleReqDtoValidator implements Validator {

    private final ArticleRepository articleRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(ArticleReqDto.class);
    }

    @Override
    public void validate(Object object, Errors errors) {
        ArticleReqDto articleReqDto = (ArticleReqDto)object;
        System.out.println("벨리데이트안");
//        
//        if(articleRepository.findById(articleReqDto.get))

    }

}
