package com.example.common.exception;

import com.example.common.response.ResultCode;

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

