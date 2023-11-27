package com.fang.springboot.common.format.datetime;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class DefaultDateFormatFinder implements DateFormatFinder {

    /**
     * 根据长度进行分组
     */
    Map<Integer, List<DateFormatter>> dateFormatterCache = new HashMap<>();

    List<DateFormatter> dateFormatters;

    public DefaultDateFormatFinder(List<DateFormatter> dateFormatters) {
        init(dateFormatters);
    }

    protected void init(List<DateFormatter> dateFormatters) {
        Assert.notEmpty(dateFormatters, "dateFormatters为空");

        this.dateFormatters = dateFormatters;
        this.dateFormatterCache = dateFormatters.stream().collect(Collectors.groupingBy(item -> item.getPattern().length()));
    }

    /**
     * 查找与dateString格式匹配的格式化器
     *
     * @param dateString
     * @return
     */
    @Override
    public DateFormatter find(String dateString) {
        int dateStringLength = dateString.length();
        //长度相同的候选格式
        List<DateFormatter> candidateFormatters = dateFormatterCache.get(dateStringLength);

        if (candidateFormatters == null || candidateFormatters.isEmpty()) {
            String patternsMessage = this.dateFormatters.stream().map(DateFormatter::getPattern).collect(Collectors.joining(","));
            throw new IllegalArgumentException("日期字符串" + dateString + "无法匹配日期如下格式：" + patternsMessage);
        }

        //从候选中找出字符数与标准格式相匹配的
        List<DateFormatter> matchFormatterList = new ArrayList<>();
        for (int i = 0; i < candidateFormatters.size(); i++) {
            DateFormatter candidateFormatter = candidateFormatters.get(i);
            if (isMatch(candidateFormatter, dateString)) {
                //pattern = yyyyMMdd 仅字母， dataString 20190901 仅数字
                matchFormatterList.add(candidateFormatter);
            }
        }
        if (matchFormatterList.size() == 1) {
            return matchFormatterList.get(0);
        }
        String patternsMessage = matchFormatterList.stream().map(DateFormatter::getPattern).collect(Collectors.joining(","));
        throw new IllegalArgumentException("日期字符串" + dateString + "找到" + candidateFormatters.size() + "长度匹配的日期格式，分别为" + patternsMessage);
    }

    /**
     * 是否匹配，匹配规则分两种情况：
     * 1. dateString全是数字且dateFormatter全是字母，那么匹配结果为true，例如pattern=yyyymmdd, dateString=20190901
     * 2. 非数字部分对应字符匹配个数大于0且非数字部分个数与匹配数字符数相等，例如pattern=yyyy-mm-dd, dateString=2019-09-01， 字符匹配数为2且大于0
     *
     * @param dateFormatter
     * @param dateString
     * @return
     */
    protected boolean isMatch(DateFormatter dateFormatter, String dateString) {
        int dateStringLength = dateString.length();

        //匹配的非数字计数
        int matchCharCount = 0;

        //日期字符串的数字计数
        int dateStringNumberCount = 0;
        for (int idx = 0; idx < dateStringLength; idx++) {
            if (Character.isDigit(dateString.charAt(idx))) {
                dateStringNumberCount++;
                continue;
            }

            if (dateFormatter.getPattern().charAt(idx) == dateString.charAt(idx)) {
                matchCharCount++;
            }
        }

        //dateString仅包含数字
        boolean isDateStringNumberOnly = (dateStringNumberCount == dateStringLength);

        //非数字部分是匹配的， 例如
        boolean isMatch = (matchCharCount != 0) && (matchCharCount == dateStringLength - dateStringNumberCount);
        if (isDateStringNumberOnly && dateFormatter.isLetterOnly()) {
            //dataString全是数字，例如：20190910, dateFormatter全是字母 yyyyMMdd 或 MMddyyyy, 认为是匹配的，因为无法区分
            return true;
        } else {
            return isMatch;
        }

    }


}