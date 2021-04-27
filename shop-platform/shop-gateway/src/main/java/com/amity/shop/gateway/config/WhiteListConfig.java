package com.amity.shop.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Amity on 2021/4/26 下午 7:41
 */
@ConfigurationProperties(prefix = "white-list")
@Component
public class WhiteListConfig {

    private List<String> urls;

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
