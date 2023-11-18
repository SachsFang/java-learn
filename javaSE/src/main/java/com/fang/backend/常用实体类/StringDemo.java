package com.fang.backend.常用实体类;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by SachsFang on 2021/7/12 15:09
 */
public class StringDemo {
    public static void main(String[] args) {
        StringTokenizerDemo();
        System.out.println("-----------------------");
        ScannerDemo();
    }

    public static void StringTokenizerDemo() {
        String str = "I am want **to#*#do something with you";
        StringTokenizer tokenizer = new StringTokenizer(str, "#* ");

        System.out.println("单词总数：" + tokenizer.countTokens());
        while (tokenizer.hasMoreTokens()) {
            System.out.print(tokenizer.nextToken() + " ");
        }
    }

    public static void ScannerDemo() {
        String str = "I want to 234 make love 12 with100.5 you";
        String regex = "[^0123456789.]+";
        Scanner scanner = new Scanner(str);
        scanner.useDelimiter(regex);

        int sum = 0;
        while (scanner.hasNext()) {
            System.out.print(scanner.next() + " ");
            sum ++;
        }
        System.out.println();
        System.out.println("数字总数："+sum);
    }
}
