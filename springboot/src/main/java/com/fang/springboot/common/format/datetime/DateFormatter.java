package com.fang.springboot.common.format.datetime;

import cn.hutool.core.date.DateUtil;
import com.fang.springboot.common.util.PatternUtils;
import org.springframework.util.Assert;

import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateFormatter {

    private String pattern;
    private DateTimeFormatter dateTimeFormatter;

    /**
     * 是否仅有字母
     */
    private boolean isLetterOnly;

    public DateFormatter(String pattern) {
        Assert.hasText(pattern, "日期格式为空");

        this.pattern = pattern;
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        this.isLetterOnly = PatternUtils.isLetter(pattern);
    }

    public String getPattern() {
        return pattern;
    }

    public Date format(String dateString){
        return DateUtil.parse(dateString, this.dateTimeFormatter);
    }

    public boolean isLetterOnly() {
        return isLetterOnly;
    }
}