package com.amity.shop.common.exception;

import com.amity.shop.common.constant.ErrorCode;

/**
 * Created by Amity on 2021/4/27 上午 10:02
 */
public class ValidationException extends ServiceException{

    private String field;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String code, String field) {
        super(code);
        this.field = field;
    }

    public ValidationException(String message, String code, String field) {
        super(message, code);
        this.field = field;
    }

    public ValidationException(ErrorCode errorCode) {
        super(errorCode);
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
