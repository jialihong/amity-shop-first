package com.amity.shop.gateway.constant;

import com.amity.shop.common.constant.ErrorCode;

/**
 * Created by Amity on 2021/4/27 下午 12:01
 */
public enum ErrorCodeEnum implements ErrorCode {

    APP_KEY_OR_SECRET_REQUIRED("0000001", ""),
    ACCESS_NOT_AUTHORIZED("0000002", ""),
    ;

    private String code;

    private String message;

    ErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
