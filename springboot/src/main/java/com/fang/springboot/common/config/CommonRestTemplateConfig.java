package com.fang.springboot.common.config;

import com.fang.springboot.common.intercepter.LoggingHttpRequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NoHttpResponseException;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.util.Collections;
import java.util.List;

/**
 * @author shaobin
 * @date 2022/10/20 10:20
 */
@Configuration
@Slf4j
public class CommonRestTemplateConfig {

    @Value("${rest-template.log-request}")
    private Boolean logRequest;

    @Bean
    @Primary // 当与依赖包的实例化的RestTemplate对象冲突时，可使用@Primary注解指定主实现
    public RestTemplate myRestTemplate(ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(factory));
        if (logRequest) {
            List<ClientHttpRequestInterceptor> interceptors = Collections.singletonList(new LoggingHttpRequestInterceptor());
            restTemplate.setInterceptors(interceptors);
        }
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        return restTemplate;
    }

    @Bean
    public ClientHttpRequestFactory fireClientHttpRequestFactory() throws Exception {
        // 忽略 https 证书认证
        TrustStrategy acceptingTrustStrategy = (X509CertificateChain, authType) -> true;
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
        SSLConnectionSocketFactory connectionSocketFactory = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setSSLSocketFactory(connectionSocketFactory);
        CloseableHttpClient httpClient = httpClientBuilder.setRetryHandler((exception, executionCount, context) -> {
            if (executionCount > 3) {
                log.warn("Maximum tries reached for client http pool");
                return false;
            }
            if (exception instanceof NoHttpResponseException || exception instanceof ConnectTimeoutException) {
                log.warn("NoHttpResponseException retry " + executionCount + " call");
                return true;
            }
            return false;
        }).build();
        // build ClientHttpRequestFactory
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(httpClient);
        // 设置读取超时时间
        factory.setReadTimeout(10000);
        // 设置连接超时时间
        factory.setConnectTimeout(15000);
        return factory;
    }
}
