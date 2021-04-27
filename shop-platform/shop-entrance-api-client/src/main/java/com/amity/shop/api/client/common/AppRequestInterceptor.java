package com.amity.shop.api.client.common;

import com.amity.shop.api.client.common.config.AppConfig;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * Created by Amity on 2021/4/27 下午 4:13
 */
@ConditionalOnClass(RequestInterceptor.class)
public class AppRequestInterceptor implements RequestInterceptor {

    @Autowired
    private AppConfig appConfig;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("appKey", appConfig.getAppKey());
        requestTemplate.header("appSecret", appConfig.getAppSecret());
        requestTemplate.header("Accept-Language", LocaleContextHolder.getLocale().toLanguageTag());
    }
}
