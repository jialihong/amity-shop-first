package com.amity.shop.gateway.exception;

import com.alibaba.fastjson.JSON;
import com.amity.shop.common.exception.ServiceException;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 网关异常通用处理器，只作用在webflux环境下，优先级低于 {@link ResponseStatusExceptionHandler}执行
 * Created by Amity on 2021/4/26 下午 8:09
 */
@Configuration
@Order(-1)
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable exception) {
        ServerHttpResponse response = serverWebExchange.getResponse();

        if(response.isCommitted()) {
            return Mono.error(exception);
        }

        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        if(exception instanceof ResponseStatusException) {
            response.setStatusCode(((ResponseStatusException)exception).getStatus());
        }

        Map<String, String> message = new HashMap<>(16);

        if(exception instanceof ServiceException){
            message.put("code", ((ServiceException)exception).getCode());
            message.put("message", exception.getMessage());
        }else {
            message.put("code", HttpStatus.BAD_GATEWAY.toString());
            message.put("message", "server error");
        }

        byte[] bits = JSON.toJSONString(message).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Flux.just(buffer));
    }
}
