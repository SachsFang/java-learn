package com.fang.springboot.common.format.datetime;

public interface DateFormatFinder {

    DateFormatter find(String dateString);

}