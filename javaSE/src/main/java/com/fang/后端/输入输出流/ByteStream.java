package com.fang.后端.输入输出流;

import java.io.*;

/**
 * Created by SachsFang on 2021/7/9 11:31
 * 文件字节输入、输出流
 */
public class ByteStream {
    public static void main(String[] args) {
        File file = new File("D:\\ideaProject\\my-project\\java-learn\\src\\后端\\输入输出流", "hello.txt");
//        writeFile(file);
        readFile(file);
    }

    public static void writeFile(File file) {
        byte[] bytes = "我现在用字节流...".getBytes();
        try {
            System.out.println(file.getName() + "文件的大小为:" + file.length() + " 字节");
            FileOutputStream out = new FileOutputStream(file, true);
            for (int i = 0; i < 50; i++) {
                out.write(bytes);
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
        byte[] bytes = new byte[(int) file.length()];
        try {
            FileInputStream stream = new FileInputStream(file);
            while ((n = stream.read(bytes)) != -1) {
                String s = new String(bytes);
                System.out.println(s);
            }
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
