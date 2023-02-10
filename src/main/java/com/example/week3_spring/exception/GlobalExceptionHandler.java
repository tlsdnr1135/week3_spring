package com.example.week3_spring.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static java.time.LocalDateTime.now;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = {CustomException.class})
    protected ResponseEntity<HttpResponse> handleCustomException(CustomException e){
        System.out.println("handleCustomException");
        return HttpResponse.toHttpResponse(e.getResponseCode());
    }
    //이건 왜 아래에서 안잡힘?
    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpResponse> itgException(Exception e){
        System.out.println("itgException");
        //여기서 그냥 강제로 줘야할듯... 하드코딩으로
        return HttpResponse.toItgHttpResponse(e);
    }

    //RunException 종류 별로 나눠야 하나?
    //널 포인트인 경우하고 문법 오류?하고

}
