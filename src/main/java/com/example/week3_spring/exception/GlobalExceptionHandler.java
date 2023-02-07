package com.example.week3_spring.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static java.time.LocalDateTime.now;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public void adssad(Exception e){
        System.out.println(e);
        System.out.println("sdsdsdssdssdsdds");
    }

    @ExceptionHandler(value = {CustomException.class})
    protected ResponseEntity<HttpResponse> handleCustomException(CustomException e){
        System.out.println("handleCustomException 안 = " + e);
        System.out.println("handleCustomException 안 = " + e.getResponseCode());
        System.out.println("handleCustomException 안 = " + e.getMessage());
        System.out.println("handleCustomException 안 = " + e.getLocalizedMessage());
        System.out.println("handleCustomException 안 = " + e.getCause());

        return HttpResponse.toHttpResponse(e.getResponseCode());
    }


}
