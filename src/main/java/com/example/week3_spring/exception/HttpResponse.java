package com.example.week3_spring.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
public class HttpResponse implements Serializable {

    private LocalDateTime timestamp;
    private Integer status;
    private String response;
    private String code;
    private String message;
    private Object data;

    @Builder
    public HttpResponse(LocalDateTime timestamp, Integer status, String response, String code, String message, Object data) {
        this.timestamp = timestamp;
        this.status = status;
        this.response = response;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    //일반 리턴
    public static ResponseEntity<HttpResponse> toDataHttpResponse(DataResponseCode e){
        return ResponseEntity
                .status(e.getResponseCode().getHttpStatus())
                .body(HttpResponse.builder()
                        .timestamp(now())
                        .status(e.getResponseCode().getHttpStatus().value())
                        .response(e.getResponseCode().getHttpStatus().name())
                        .code(e.getResponseCode().name())
                        .message(e.getResponseCode().getMsg())
                        .data(e.getData())
                        .build()
                );
    }

    //커스텀 리턴
    public static ResponseEntity<HttpResponse> toHttpResponse(ResponseCode e){
        return ResponseEntity
                .status(e.getHttpStatus()) //내가 정한 스테이터스 ex)
                .body(
                        HttpResponse.builder()
                                .timestamp(now()) //현재 시간
                                .status(e.getHttpStatus().value()) //내가 정한 코드의 밸류 ex)404
                                .response(e.getHttpStatus().name()) //내가 정한 코드의 이름 ex)
                                .code(e.name()) //내가 직접 정한 코드 ex) BAD_REQUEST
                                .message(e.getMsg())
                                .build()
                );
    }

    //통합 리턴
    public static ResponseEntity<HttpResponse> toItgHttpResponse(Exception e){
        return ResponseEntity
                .status(400) //내가 정한 스테이터스 ex)
                .body(
                        HttpResponse.builder()
                                .timestamp(now()) //현재 시간
                                .status(400) //내가 정한 코드의 밸류 ex)404
                                .response(BAD_REQUEST.getReasonPhrase()) //내가 정한 코드의 이름 ex)
                                .code(String.valueOf(e.getCause())) //내가 직접 정한 코드 ex) BAD_REQUEST
                                .message(e.getMessage())
                                .build()
                );
    }


}
