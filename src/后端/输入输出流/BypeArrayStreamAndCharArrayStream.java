package 后端.输入输出流;

import java.io.*;

/**
 * Created by SachsFang on 2021/7/11 17:10
 */
public class BypeArrayStreamAndCharArrayStream {
    public static void main(String[] args) {
        byteArrayStream();
        charArrayStream();
    }

    public static void byteArrayStream(){
        byte[] bytes = "I am a good boy".getBytes();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {
            outStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] readBytes = new byte[bytes.length];
        ByteArrayInputStream inStream = new ByteArrayInputStream(bytes);
        try {
            inStream.read(readBytes);
            System.out.println(new String(readBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void charArrayStream() {
        char[] chars = "我是最强的！".toCharArray();
        CharArrayWriter writer = new CharArrayWriter();
        try {
            writer.write(chars);
        } catch (IOException e) {
            e.printStackTrace();
        }

        char[] readChars = new char[chars.length];
        CharArrayReader reader = new CharArrayReader(chars);
        try {
            reader.read(readChars);
            System.out.println(new String(readChars));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
