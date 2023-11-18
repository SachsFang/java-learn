package com.fang.backend.Java常用设计模式.访问者模式.example;

/**
 * @author shaobin
 * @date 2022/6/30 18:00
 */
public class ConcreteVisitorB extends Visitor {
    @Override
    public void visitConcreteElemA(ConcreteElementA elem) {
        System.out.println("访问者B访问了:" + elem.getElemName());
    }

    @Override
    public void visitConcreteElemB(ConcreteElementB elem) {
        System.out.println("访问者B访问了:" + elem.getElemName());
    }
}
