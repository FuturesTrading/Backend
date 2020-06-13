package com.example.common.response;

import lombok.Data;

@Data
public class Result<T> {
    T data;

    String message;

    int code;
}
