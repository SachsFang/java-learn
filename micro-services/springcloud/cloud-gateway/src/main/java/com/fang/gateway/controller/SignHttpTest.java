package org.apache.shenyu.admin.sign;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.shenyu.admin.utils.HttpUtils;
import org.apache.shenyu.common.utils.SignUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * author maisr@tsintergy.com.
 * date 2023-03-27 15:53:41.
 */
public class SignHttpTest {

    private final Logger logger = LoggerFactory.getLogger(SignHttpTest.class);

    private String domain = "http://localhost:9195";

    private String version = "tsie";

    private String appKey = "3E99BBD5052246EE90DC0C01D222232F";

    private String time = String.valueOf(System.currentTimeMillis());

    private ObjectMapper objectMapper = new ObjectMapper();

    private MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

    private String signAlg = "HS256";

    @Test
    public void testTsieSignWithNoBody() {
        // x-ca-switch 不传 、为空、值为close
        // String path = "/usercenter/web/pf/enterprise";
        String path = "/usercenter/web/home";
        HttpUrl url = HttpUtils.buildHttpUrl(domain + path + "?name=xixihaha&nbme=123&tenantId=e4c8c7207b87a996017b966b29bf0062");

        Map<String, String> map = new HashMap<>(3);
        //timestamp为毫秒数的字符串形式 String.valueOf(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli())
        //值应该为毫秒数的字符串形式
        map.put("x-ca-timestamp", time);
        //请求的url path
        map.put("path", path);
        //签名算法版本
        map.put("x-ca-version", version);
        // 如果打开了请求体校验，在路径上 ?name=xixihaha 也需要加入到参数中
        //map.put("name", "xixihaha");
        //map.put("nbme", "123");

        // 设置超时方便debug
        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .callTimeout(Duration.ofMinutes(5))
                .readTimeout(Duration.ofMinutes(5))
                .writeTimeout(Duration.ofMinutes(5))
                .connectTimeout(Duration.ofMinutes(5))
                .build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("x-ca-switch", "open")
                // 不传、tsie、其他值
                .addHeader("x-ca-version", version)
                .addHeader("x-ca-timestamp", time)
                .addHeader("x-ca-key", appKey)
                .addHeader("x-ca-sign", genSign(map, appKey, ""))
                .build();
        Call call = okHttpClient.newCall(request);
        Response response = null;
        try {
            response = call.execute();
            logger.info(response.body().string());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    @Test
    public void testTsieSignPostPathParma() throws JsonProcessingException {
        // x-ca-switch 不传 、为空、值为close
        String path = "/client/post/hi";
        HttpUrl url = HttpUtils.buildHttpUrl(domain + path + "?name=jack");

        // 设置超时方便debug
        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .callTimeout(Duration.ofMinutes(5))
                .readTimeout(Duration.ofMinutes(5))
                .writeTimeout(Duration.ofMinutes(5))
                .connectTimeout(Duration.ofMinutes(5))
                .build();

        Map<String, String> map = new HashMap<>(3);
        //timestamp为毫秒数的字符串形式 String.valueOf(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli())
        //值应该为毫秒数的字符串形式
        map.put("x-ca-timestamp", time);
        //请求的url path
        map.put("path", path);
        //签名算法版本
        map.put("x-ca-version", version);
        // post请求、query param
        map.put("name", "jack");

        Request request = new Request.Builder()
                // 请求体为空
                .post(RequestBody.create(mediaType, ""))
                .url(url)
                .addHeader("x-ca-switch", "open")
                // 不传、tsie、其他值
                .addHeader("x-ca-version", "tsie")
                .addHeader("x-ca-timestamp", time)
                .addHeader("x-ca-key", appKey)
                .addHeader("x-sign-method", signAlg)
                .addHeader("x-ca-sign", genSign(map, appKey, signAlg))
                .build();
        Call call = okHttpClient.newCall(request);
        Response response = null;
        try {
            response = call.execute();
            logger.info(response.body().string());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    @Test
    public void testTsieSignBody() throws JsonProcessingException {
        // x-ca-switch 不传 、为空、值为close
        final String path = "/http/order/save";
        final HttpUrl url = HttpUtils.buildHttpUrl(domain + path);

        // 设置超时方便debug
        final OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .callTimeout(Duration.ofMinutes(5))
                .readTimeout(Duration.ofMinutes(5))
                .writeTimeout(Duration.ofMinutes(5))
                .connectTimeout(Duration.ofMinutes(5))
                .build();
        Map<String, String> body = new HashMap<>();
        body.put("id", time);
        body.put("name", "apple");
        RequestBody requestBody = RequestBody.create(mediaType, objectMapper.writeValueAsString(body));

        Map<String, String> map = new HashMap<>(3);
        //timestamp为毫秒数的字符串形式 String.valueOf(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli())
        //值应该为毫秒数的字符串形式
        map.put("x-ca-timestamp", time);
        //请求的url path
        map.put("path", path);
        //签名算法版本
        map.put("x-ca-version", version);
        map.putAll(body);

        Request request = new Request.Builder()
                .post(requestBody)
                .url(url)
                .addHeader("x-ca-switch", "open")
                // 不传、tsie、其他值
                .addHeader("x-ca-version", "tsie")
                .addHeader("x-ca-timestamp", time)
                .addHeader("x-ca-key", appKey)
                .addHeader("x-ca-sign", genSign(map, appKey, ""))
                .build();
        Call call = okHttpClient.newCall(request);
        Response response = null;
        try {
            response = call.execute();
            logger.info(response.body().string());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    private String genSign(final Map<String, String> map, final String appKey, String alg) {
        List<String> storedKeys = Arrays.stream(map.keySet()
                        .toArray(new String[]{}))
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        final String contactStr = storedKeys.stream()
                .map(key -> String.join("", key, map.get(key)))
                .collect(Collectors.joining()).trim();
        logger.info("contact string {}", contactStr);
        alg = StringUtils.isBlank(alg) ? SignUtils.SIGN_MD5 : alg;
        String sign = SignUtils.sign(alg, appKey, contactStr).toUpperCase();
        logger.info("sign is {}", sign);
        return sign;
    }
}