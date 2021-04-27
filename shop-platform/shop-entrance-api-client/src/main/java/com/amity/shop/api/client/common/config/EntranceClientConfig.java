package com.amity.shop.api.client.common.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Amity on 2021/4/27 下午 4:11
 */
@Configuration
@EnableFeignClients(basePackages = {"com.amity.shop.api.client.service"})
public class EntranceClientConfig {
}
