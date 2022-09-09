package com.fang.后端.Java常用设计模式.解释器模式.base_example;

/**
 * @author shaobin
 * @date 2022/6/29 11:15
 */
public class NonTerminalExpression extends AbstractExpression{
    @Override
    void interpret(Context context) {
        System.out.println("非终结符解析器解析文本：" + context.getText());
    }
}
