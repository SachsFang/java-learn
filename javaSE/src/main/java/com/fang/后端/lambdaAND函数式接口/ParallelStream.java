package com.fang.后端.lambdaAND函数式接口;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

/**
 * @author shaobin
 * @date 2022/8/15 17:03
 */
public class ParallelStream {
    public static void main(String[] args) {
//        generalStream();
        parallelStreamStream();
    }

    public static void generalStream() {
        Instant start = Instant.now();
        long sum = 0;
        for (long i = 0; i <= 50000000000L; i++) {
            sum += i;
        }
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("不同stream的处理时间：" + Duration.between(start, end).toMillis());
    }

    public static void parallelStreamStream() {
        Instant start = Instant.now();
        LongStream longStream = LongStream.rangeClosed(0, 50000000000L);
        System.out.println(longStream.parallel().reduce(Long::sum));
        Instant end = Instant.now();
        System.out.println("不同stream的处理时间：" + Duration.between(start, end).toMillis());
    }
}
