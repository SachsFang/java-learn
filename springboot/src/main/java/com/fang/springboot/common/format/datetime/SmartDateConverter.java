package com.fang.springboot.common.format.datetime;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author shaobin
 * @date 2023/11/27 16:56
 */
public class SmartDateConverter implements Converter<String, Date> {

    SmartDateFormatter smartDateFormatter;

    public SmartDateConverter() {
        smartDateFormatter = new SmartDateFormatter();
    }

    @Override
    public Date convert(String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        return smartDateFormatter.format(source);
    }
}
