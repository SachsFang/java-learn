package com.fang.springboot.common.intercepter;

import com.fang.springboot.common.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author shaobin
 * @date 2022/10/20 10:26
 */
@Slf4j
public class LoggingHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] body, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        trackRequest(httpRequest, body);
        ClientHttpResponse response = clientHttpRequestExecution.execute(httpRequest, body);
        trackResponse(response);
        return response;
    }

    private void trackRequest(HttpRequest httpRequest, byte[] body) throws UnsupportedEncodingException {
        log.info("=========================== request Logging ===========================");
        log.info("Url:{}", httpRequest.getURI());
        log.info("Method:{}", httpRequest.getMethod());
        log.info("Headers:{}", httpRequest.getHeaders());
        log.info("Body:{}", new String(body, "utf-8"));
    }

    private void trackResponse(ClientHttpResponse response) throws IOException {
        log.info("=========================== response Logging ===========================");
        log.info("Status Code:{}", response.getStatusCode());
        log.info("Status Text:{}", response.getStatusText());
        log.info("Headers:{}", response.getHeaders());
        log.info("Body:{}", ResponseUtil.convertBody(response.getBody()));
    }
}
