package 后端.输入输出流;

import java.io.File;
import java.io.IOException;

/**
 * Created by SachsFang on 2021/7/9 11:09
 */
public class RuntimeDemo {
    public static void main(String[] args) {
        Runtime ce = Runtime.getRuntime();
        File file = new File("D:\\PersonalCloud", "PersonalCloud.exe");
        try {
            String absolutePath = file.getAbsolutePath();
            ce.exec(file.getAbsolutePath());
            System.out.println("文件已打开");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
