package com.amity.shop.common.pojo;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created by Amity on 2021/4/27 下午 4:25
 */
public class BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
