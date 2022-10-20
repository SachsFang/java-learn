package com.fang.springboot.common.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;
import java.util.Objects;

/**
 * @author shaobin
 * @date 2022/10/20 11:19
 */
public class RequestUtil {

    public static HttpHeaders getHeaders(MediaType mediaType) {
        return getHeaders(mediaType, null);
    }

    public static HttpHeaders getHeaders(MediaType mediaType, String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(Objects.nonNull(mediaType) ? mediaType : MediaType.APPLICATION_JSON);
        if (Objects.nonNull(token)) {
            httpHeaders.put(HttpHeaders.COOKIE, Collections.singletonList(token));
        }
        return httpHeaders;
    }
}
