package com.amity.shop.common.validator;

import com.amity.shop.common.constant.CharConstants;
import com.amity.shop.common.constant.ErrorCode;
import com.amity.shop.common.exception.ValidationException;
import com.amity.shop.common.pojo.BaseForm;

import java.util.Objects;

/**
 * Created by Amity on 2021/4/27 上午 9:51
 */
public interface BaseValidator<T extends BaseForm> {

    /**
     * @param t 校验对象
     * @param type 区分新增、修改  add:新增；update:修改
     */
    default void validate(T t, String type) {
        //先验证所有需要检验的字段，后续再按修改还是新增分别校验对应的特有字段
        ErrorCode errorCode = validateCommon(t);
        if(!Objects.isNull(errorCode)) {
            throw new ValidationException(errorCode);
        }else {
            if(CharConstants.VALIDATOR_TYPE_ADD.equals(type)) {
                errorCode = validateInsert(t);
            }else {
                errorCode = validateUpdate(t);
            }
            if(!Objects.isNull(errorCode)) {
                throw new ValidationException(errorCode);
            }
        }
    }

    /**
     * 新增和修改都需要校验的逻辑
     * @param t 校验对象
     * @return ErrorCode
     */
    default ErrorCode validateCommon(T t) {
        return null;
    }

    /**
     * 新增需要校验的逻辑
     * @param t t
     * @return ErrorCode
     */
    default ErrorCode validateInsert(T t) {
        return null;
    }

    /**
     * 修改需要校验的逻辑
     * @param t t
     * @return ErrorCode
     */
    default ErrorCode validateUpdate(T t) {
        return null;
    }
}
