package com.fang.backend.Java常用设计模式.责任链模式.日志输出;

/**
 * Created by SachsFang on 2021/7/16 15:56
 */
public class ErrorLogger extends AbstractLogger {
    ErrorLogger(int level) {
        this.level = level;
    }

    @Override
    protected void printMessage(String message) {
        System.out.println("ErrorLogger:" + message);
    }
}
