package com.amity.shop.api.client.service;

import com.amity.shop.api.client.common.constant.PathConstants;
import com.amity.shop.api.client.pojo.response.AppDetailResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Amity on 2021/4/27 下午 4:20
 */
@FeignClient(name = PathConstants.SHOP_ENTRANCE_API, path = PathConstants.COMMON_PATH, contextId = "appAuthenticate")
public interface AppAuthenticateService {

    @GetMapping("/appAuthenticate/getAppDetail")
    AppDetailResponse getAppDetail(@RequestParam("appKey") String appKey, @RequestParam("appSecret") String appSecret);
}
