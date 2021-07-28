package 后端.Java常用设计模式.责任链模式;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SachsFang on 2021/7/16 15:17
 */
public class Main {
    public static void main(String[] args) {
        AbstractLogger chain = Chain.getChainLogger();
        chain.logMessage(AbstractLogger.ERROR, "error");
        chain.logMessage(AbstractLogger.DEBUG, "debug");
        chain.logMessage(AbstractLogger.INFO, "normal info");

        List<String> list = new ArrayList<String>();
        chain.handleMessage(AbstractLogger.ERROR, list);
        System.out.println(list.toString());
    }
}
