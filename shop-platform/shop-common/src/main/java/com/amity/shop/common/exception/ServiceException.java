package com.amity.shop.common.exception;

import com.amity.shop.common.constant.ErrorCode;

/**
 * Created by Amity on 2021/4/27 上午 10:02
 */
public class ServiceException extends RuntimeException{

    private String code;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, String code) {
        super(message);
        this.code = code;
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }

    public ServiceException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
