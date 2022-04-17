package 后端.Java常用设计模式.责任链模式;

import java.util.List;

/**
 * Created by SachsFang on 2021/7/16 15:18
 * 抽象日志定义三个级别,小于或等于当前级别的,才去进行数据输出或处理
 */
public abstract class AbstractLogger {
    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;
    public int level;
    public AbstractLogger nextLogger;

    public void setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void logMessage(int level, String message) {
        if (this.level <= level) {
            printMessage(message);
        }
        if (this.nextLogger != null) {
            nextLogger.logMessage(level, message);
        }
    }

    public void handleMessage(int level, List<String> list) {
        if (this.level <= level) {
            String str = null;
            switch (this.level) {
                case 1:
                    str = "info";
                    break;
                case 2:
                    str = "debug";
                    break;
                case 3:
                    str = "error";
                    break;
            }
            list.add(str);
        }
        if (this.nextLogger != null) {
            nextLogger.handleMessage(level, list);
        }
    }



    protected abstract void printMessage(String message);
}
