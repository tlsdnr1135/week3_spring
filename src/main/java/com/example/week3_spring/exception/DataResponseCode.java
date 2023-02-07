package com.example.week3_spring.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DataResponseCode {

    private Object data;
    private ResponseCode responseCode;

    @Builder
    public DataResponseCode( ResponseCode responseCode,Object data) {
        this.data = data;
        this.responseCode = responseCode;
    }
}
