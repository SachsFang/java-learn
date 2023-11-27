package com.fang.springboot.common.reslover;

import com.fang.springboot.common.format.datetime.SmartDateFormatter;
import org.springframework.core.MethodParameter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Date;
import java.util.Objects;

/**
 * 接口传参时间解析器
 */
public class DateHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private SmartDateFormatter smartDateFormatter;

    public DateHandlerMethodArgumentResolver(SmartDateFormatter smartDateFormatter){
        Assert.notNull(smartDateFormatter, "smartDateFormatter is required");
        this.smartDateFormatter = smartDateFormatter;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 判断该参数是否需要通过该解析器进行解析
        return parameter.getParameterType() == Date.class && !parameter.hasParameterAnnotation(DateTimeFormat.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String dateStr = webRequest.getParameter(Objects.requireNonNull(parameter.getParameterName()));
        // 根据实际需要解析时间参数
        //todo 此处目前只匹配了一种时间类型，后续可拓展为多种
        return smartDateFormatter.format(dateStr);
    }
}