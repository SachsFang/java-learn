package com.fang.后端.输入输出流;

import java.io.*;

/**
 * Created by SachsFang on 2021/7/9 17:25
 * 数据流
 * 以字节流为基础（字节流为底层数据流，数据流为上层数据流）
 * 当读取一个数值是，可不关心这个数值应该是多少字节
 */
public class DataStream {
    public static void main(String[] args) {
        File file = new File("D:\\ideaProject\\my-project\\java-learn\\src\\后端\\输入输出流", "hello.txt");
        writeFile(file);
        readFile(file);
    }

    private static void readFile(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            DataInputStream reader = new DataInputStream(fileInputStream);
            System.out.println(reader.readInt());
            System.out.println(reader.readFloat());
            System.out.println(reader.readLong());
            System.out.println(reader.readBoolean());
            char c;

            while ((c = reader.readChar()) != '\0') {
                System.out.print(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeFile(File file) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            DataOutputStream writer = new DataOutputStream(fileOutputStream);
            writer.writeInt(100);
            writer.writeFloat(3.1415920535f);
            writer.writeLong(1234554545454542L);
            writer.writeBoolean(true);
            writer.writeChars("I wanna tell you,I wanna make love with you!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
