package 后端.输入输出流;

import java.io.*;

/**
 * Created by SachsFang on 2021/7/9 15:41
 * 缓冲流
 * 缓冲流是在字节输入、输出流的基础上使用BufferedReader or BufferedWriter,它们的参数分别是FileReader or FileWriter的实例，相当于加强了字符流
 * 缓冲流最大的特点是读写能力更强，能够处理换行问题(readLine or newLine)
 */
public class CacheStream {
    public static void main(String[] args) {
        File file = new File("D:\\ideaProject\\my-project\\java-learn\\src\\后端\\输入输出流", "cacheStream.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeFile(file);
        readFile(file);
    }

    public static void readFile(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String lineContent;
            while ((lineContent = reader.readLine()) != null) {
                System.out.println(lineContent);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(File file) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write("我会成功的");
            writer.newLine();
            writer.write("我一定可以拿12000薪资");
            writer.newLine();
            writer.write("我要两年之内买到宝马X1");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
