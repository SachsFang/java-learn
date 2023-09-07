package com.fang.springboot.common.function;

import java.util.Date;
import java.util.List;

/**
 * @author shaobin
 * @date 2023/8/28 15:38
 */
@FunctionalInterface
public interface DateBiFuntion<R> {
    List<R> apply(Date startDate, Date endDate);
}