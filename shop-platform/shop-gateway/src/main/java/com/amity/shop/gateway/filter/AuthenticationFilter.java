package com.amity.shop.gateway.filter;

import com.amity.shop.api.client.pojo.response.AppDetailResponse;
import com.amity.shop.api.client.service.AppAuthenticateService;
import com.amity.shop.common.exception.ServiceException;
import com.amity.shop.gateway.config.WhiteListConfig;
import com.amity.shop.gateway.constant.ErrorCodeEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

/**
 * 鉴权过滤器
 * Created by Amity on 2021/4/26 下午 7:40
 */
@Component
public class AuthenticationFilter implements GlobalFilter {

    @Autowired
    private WhiteListConfig whiteListConfig;

    @Autowired
    private AppAuthenticateService appAuthenticateService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String requestUrl = request.getURI().getPath();

        //白名单直接放行
        if(CollectionUtils.isNotEmpty(whiteListConfig.getUrls()) && whiteListConfig.getUrls().contains(requestUrl)) {
            return chain.filter(exchange);
        }

        //校验appKey\appSecret
        List<String> appKeys = exchange.getRequest().getHeaders().get("appKey");
        List<String> appSecrets = exchange.getRequest().getHeaders().get("appSecret");

        String appKey = CollectionUtils.isEmpty(appKeys)?"":appKeys.get(0);
        String appSecret = CollectionUtils.isEmpty(appSecrets)?"":appSecrets.get(0);
        if(StringUtils.isEmpty(appKey) || StringUtils.isEmpty(appSecret)) {
            //抛异常
            throw new ServiceException("App key or secret required!", ErrorCodeEnum.APP_KEY_OR_SECRET_REQUIRED.getCode());
        }

        //调接口 查询app信息
        AppDetailResponse appDetail = appAuthenticateService.getAppDetail(appKey, appSecret);
        if(Objects.isNull(appDetail) || StringUtils.isEmpty(appDetail.getAppCode())) {
            throw new ServiceException("Access not authorized!", ErrorCodeEnum.ACCESS_NOT_AUTHORIZED.getCode());
        }

        return chain.filter(exchange);
    }
}
