package com.fang.springboot.common.format.datetime;

import com.fang.springboot.common.format.DateFormatType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shaobin
 * @date 2023/11/27 17:01
 */
@Slf4j
public class SmartDateFormatter {

    public static final List<String> DEFAULT_PATTERNS =
            Arrays.stream(DateFormatType.values()).map(DateFormatType::getFormatStr).collect(Collectors.toList());

    List<DateFormatter> dateFormatters;

    DateFormatFinder dateFormatFinder;

    public SmartDateFormatter() {
        dateFormatters = new ArrayList<>();
        for (String pattern : DEFAULT_PATTERNS) {
            dateFormatters.add(new DateFormatter(pattern));
        }
        dateFormatFinder = new DefaultDateFormatFinder(dateFormatters);
    }

    public SmartDateFormatter(DateFormatFinder dateFormatFinder) {
        this.dateFormatFinder = dateFormatFinder;
    }


    /**
     * 把日期字符串格式化为{@link Date}
     * @param dateString
     * @return
     * @throws IllegalArgumentException
     */
    public Date format(String dateString) throws IllegalArgumentException {
        if (StringUtils.isEmpty(dateString)) {
            return null;
        }

        DateFormatter matchFormatter = dateFormatFinder.find(dateString);

        Exception ex = null;

        if (matchFormatter != null) {
            try {
                return matchFormatter.format(dateString);
            } catch (Exception e) {
                ex = e;
            }
        }

        throw new IllegalArgumentException(dateString, ex);
    }

}
