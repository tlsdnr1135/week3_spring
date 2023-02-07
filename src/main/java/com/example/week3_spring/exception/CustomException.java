package com.example.week3_spring.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private ResponseCode responseCode;

    public CustomException(ResponseCode responseCode){
        super(responseCode.getMsg());
        this.responseCode = responseCode;
    }

}
