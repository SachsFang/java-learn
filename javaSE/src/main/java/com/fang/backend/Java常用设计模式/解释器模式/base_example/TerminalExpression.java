package com.fang.backend.Java常用设计模式.解释器模式.base_example;

/**
 * @author shaobin
 * @date 2022/6/29 11:13
 */
public class TerminalExpression extends AbstractExpression{
    @Override
    void interpret(Context context) {
        System.out.println("终结符解析器解析文本：" + context.getText());
    }
}
