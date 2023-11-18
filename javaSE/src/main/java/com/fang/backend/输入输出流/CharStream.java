package com.fang.backend.输入输出流;

import java.io.*;

/**
 * Created by SachsFang on 2021/7/9 14:17
 * 字节输入、输出流
 */
public class CharStream {
    public static void main(String[] args) {
        File file = new File("D:\\ideaProject\\my-project\\java-learn\\src\\后端\\输入输出流", "hello.txt");
        writeFile(file);
        readFile(file);
    }

    public static void writeFile(File file) {
        char[] chars = "我现在用字符流...".toCharArray();
        try {
            System.out.println(file.getName() + "文件的大小为:" + file.length() + " 字节");
            FileWriter out = new FileWriter(file, true);
            for (int i = 0; i < 50; i++) {
                out.write(chars);
            }
            out.flush();//可以立刻将缓冲区的内容写入目的地，在close的时候也有次效果
            System.out.println(file.getName() + "文件的大小为:" + file.length() + " 字节");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFile(File file) {
        int n = -1;
        char[] chars = new char[(int) file.length()];
        try {
            FileReader in = new FileReader(file);
            while ((n = in.read(chars)) != -1) {
                String s = new String(chars);
                System.out.println(s);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
