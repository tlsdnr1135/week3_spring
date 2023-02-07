package com.example.week3_spring.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import static org.springframework.http.HttpStatus.*;

@Getter
public enum ResponseCode implements Serializable {

    //200
    SUCCESS(OK,"성공"),

    //400
    INVALID_ARTICLE(NOT_FOUND,"해당하는 게시글이 없습니다."),

    INVALID_FAIL(BAD_REQUEST,"형식이 잘못되었습니다.");


    private HttpStatus httpStatus;
    private String msg;

    ResponseCode(HttpStatus httpStatus, String msg) {
        this.httpStatus = httpStatus;
        this.msg = msg;
    }
}
