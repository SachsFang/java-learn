package com.fang.后端.Java常用设计模式.解释器模式.base_example;

import java.util.AbstractList;

/**
 * @author shaobin
 * @date 2022/6/29 11:16
 */
public class Client {
    public static void main(String[] args) {
        Context context = new Context("待解析的文本");
        AbstractExpression terminalExpression = new TerminalExpression();
        terminalExpression.interpret(context);
        AbstractExpression nonTerminalExpression = new NonTerminalExpression();
        nonTerminalExpression.interpret(context);
    }
}
