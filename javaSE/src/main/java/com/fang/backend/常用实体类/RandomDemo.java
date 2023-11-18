package com.fang.backend.常用实体类;

import java.util.Random;

/**
 * Created by SachsFang on 2021/7/12 16:49
 */
public class RandomDemo {
    public static void main(String[] args) {
        Random random = new Random();
        int num = random.nextInt(100);//[0-100)的数
        System.out.println(num);
        boolean flag = random.nextBoolean();
        System.out.println(flag);
    }
}
