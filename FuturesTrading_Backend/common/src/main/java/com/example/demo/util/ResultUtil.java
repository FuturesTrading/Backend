package com.example.demo.util;


import com.example.demo.response.Result;
import com.example.demo.response.ResultCode;

import static com.example.demo.response.ResultCode.SUCCESS;

public class ResultUtil {
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setMessage(SUCCESS.getMessage());
        result.setCode(SUCCESS.getCode());
        return result;
    }

    public static Result<String> success() {
        Result<String> result = new Result<>();
        result.setMessage("");
        result.setMessage(SUCCESS.getMessage());
        result.setCode(SUCCESS.getCode());
        return result;
    }

    public static <T> Result<T> failure(String message, int code) {
        Result<T> result = new Result<>();
        result.setMessage(message);
        result.setCode(code);
        return result;
    }

    public static <T> Result<T> failure(ResultCode resultCode) {
        return failure(resultCode.getMessage(), resultCode.getCode());
    }

}
