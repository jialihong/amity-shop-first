package com.amity.shop.common.pojo;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created by Amity on 2021/4/27 上午 9:51
 */
public class BaseForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
