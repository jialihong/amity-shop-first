package com.amity.shop.api.client.pojo.response;

import com.amity.shop.common.pojo.BaseDTO;

import java.util.List;

/**
 * Created by Amity on 2021/4/27 下午 4:27
 */
public class AppDetailResponse extends BaseDTO {

    private String appCode;

    private String appKey;

    private List<String> authorizedUrl;

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public List<String> getAuthorizedUrl() {
        return authorizedUrl;
    }

    public void setAuthorizedUrl(List<String> authorizedUrl) {
        this.authorizedUrl = authorizedUrl;
    }
}
