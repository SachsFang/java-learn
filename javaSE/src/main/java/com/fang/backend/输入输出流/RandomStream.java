package com.fang.backend.输入输出流;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by SachsFang on 2021/7/9 16:01
 * 随机流
 * 随机流是双向流，流的指向既可以作为流的源，也可以作为流的目的地，能够灵活进行读写操作
 */
public class RandomStream {
    public static void main(String[] args) {
        RandomAccessFile stream = creatRanDomAccess(false);
        writeFile(stream);
        readFile(stream);
        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stream = creatRanDomAccess(true);
        readChinese(stream);
    }

    public static RandomAccessFile creatRanDomAccess(Boolean isChinese) {
        String fileName = isChinese ? "cacheStream" : "hello";
        File file = new File("D:\\ideaProject\\my-project\\java-learn\\src\\后端\\输入输出流", fileName + ".txt");
        RandomAccessFile stream = null;
        try {
            stream = new RandomAccessFile(file, "rw"); //r为可读，rw为可读写
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return stream;
    }

    public static void writeFile(RandomAccessFile stream) {
        int numbers[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i = 0; i < numbers.length; i++) {
            try {
                stream.writeInt(numbers[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readFile(RandomAccessFile stream) {
        try {
            for (long i = stream.length() - 4; i >=0; i=i-4) {
                stream.seek(i);
                System.out.print(stream.readInt()+" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*RandomAccessFile在用readline读汉字时会乱码，需要将读出的字符转换一下*/
    public static void readChinese(RandomAccessFile stream) {
        try {
            long pos = 0;
            String line;
            while ((line = stream.readLine()) != null) {
                byte[] bytes = line.getBytes("iso-8859-1");
                line = new String(bytes);
                System.out.println(line);
                pos = stream.getFilePointer();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
