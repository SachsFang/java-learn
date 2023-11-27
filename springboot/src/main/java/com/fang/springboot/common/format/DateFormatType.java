package com.fang.springboot.common.format;

/**
 * @author shaobin
 * @date 2023/11/27 16:59
 */
public enum DateFormatType {
    /**
     * 格式为：yyyy-MM-dd_HH:mm
     */
    DATE_TO_MINIUTE_FORMAT("yyyy-MM-dd_HH:mm"),

    /**
     * 格式为：yyyy-MM-dd HH:mm:ss
     */
    DATE_FORMAT_STR("yyyy-MM-dd HH:mm:ss"),

    /**
     * 格式为：yyyy-MM-dd HH:mm:ss.SSS
     */
    DATE_FORMAT_STR_SSS("yyyy-MM-dd HH:mm:ss.SSS"),

    /**
     * 格式为：yyyyMMddHHmmss
     */
    SIMPLE_DATE_TIME_FORMAT_STR("yyyyMMddHHmmss"),

    /**
     * 格式为：yyyyMMddHHmmssSSS
     */
    SIMPLE_DATE_TIME_MICROSECONDS_FORMAT_STR("yyyyMMddHHmmssSSS"),

    /**
     * 格式为：yyyyMMddHHmm
     */
    SIMPLE_DATE_TIME_MINUTE_FORMAT_STR("yyyyMMddHHmm"),

    /**
     * 格式为：yyyy-MM-dd
     */
    SIMPLE_DATE_FORMAT_STR("yyyy-MM-dd"),

    /**
     * 格式为：yyyyMMdd
     */
    SIMPLE_DATE_FORMAT_COMMON_STR("yyyyMMdd"),

    /**
     * 格式为：yyyyMM
     */
    SIMPLE_DATE_FORMAT_YEAR_MON("yyyyMM"),

    /**
     * 格式为：yyyy/MM/dd
     */
    SIMPLE_DATE_FORMAT_VIRGULE_STR("yyyy/MM/dd"),

    /**
     * 格式为：HH:mm:ss
     */
    HOUR_MINUTE_SECOND("HH:mm:ss"),

    /**
     * 格式为：yyyy-MM
     */
    YEAR_MONTH_STR("yyyy-MM"),

    /**
     * 格式为：HH:mm
     */
    HOUR_MINUTE("HH:mm"),

    /**
     * 格式为：yyyy年MM月dd日
     */
    SIMPLE_DATE_CHINESE("yyyy年MM月dd日");

    private final String formatStr;

    DateFormatType(String formatStr) {
        this.formatStr = formatStr;
    }

    public String getFormatStr() {
        return this.formatStr;
    }
}
