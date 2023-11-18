package com.fang.backend.Java常用设计模式.解释器模式.base_example;

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
