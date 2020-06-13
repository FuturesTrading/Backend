package com.example.demo.exception;

import com.example.demo.response.ResultCode;
import com.example.demo.response.ResultCode;

public class ServiceException extends RuntimeException {
    ResultCode resultCode;

    public ServiceException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

}

