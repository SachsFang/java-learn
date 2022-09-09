package com.fang.后端.Java常用设计模式.责任链模式.日志输出;

/**
 * Created by SachsFang on 2021/7/16 15:58
 */
public class InfoLogger extends AbstractLogger {
    InfoLogger(int level) {
        this.level = level;
    }

    @Override
    protected void printMessage(String message) {
        System.out.println("InfoLogger:" + message);
    }
}
