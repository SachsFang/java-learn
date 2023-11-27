package com.fang.springboot.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtils {

    public final static Pattern PATTERN_NUMBER = Pattern.compile("^\\d+$");
    public final static Pattern PATTERN_LETTER = Pattern.compile("^[A-Za-z]+$");

    /**
     * 全是数字
     * @param dateString
     * @return
     */
    public static boolean isNumber(String dateString) {
        return isMatch(PATTERN_NUMBER, dateString);
    }

    /**
     * 全是字母
     * @param pattern
     * @return
     */
    public static boolean isLetter(String pattern) {
        return isMatch(PATTERN_LETTER, pattern);
    }

    public static boolean isMatch(Pattern pattern, String orginal) {
        Matcher matcher = pattern.matcher(orginal);
        return matcher.matches();
    }

}