package com.fang.gateway.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author shaobin
 * @date 2023/1/21 20:20
 */
@Component
public class GatewayFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取request对象
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if (StringUtils.isEmpty(token)) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.BAD_GATEWAY);
            String msg = "token is null";
            DataBuffer buffer = response.bufferFactory().wrap(msg.getBytes());
            return response.writeWith(Mono.just(buffer));
        }
        return chain.filter(exchange);
    }
}
