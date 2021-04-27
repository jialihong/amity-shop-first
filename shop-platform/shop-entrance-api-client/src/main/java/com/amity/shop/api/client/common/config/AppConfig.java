package com.amity.shop.api.client.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by Amity on 2021/4/27 下午 4:07
 */
@Component
@Configuration
@ConfigurationProperties(prefix = "app", ignoreInvalidFields = true, ignoreUnknownFields = true)
public class AppConfig {

    private String appKey;

    private String appSecret;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
