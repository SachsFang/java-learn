package com.fang.后端.Java常用设计模式.访问者模式.example;

/**
 * @author shaobin
 * @date 2022/6/30 17:57
 */
public class ConcreteVisitorA extends Visitor {
    @Override
    public void visitConcreteElemA(ConcreteElementA elem) {
        System.out.println("访问者A访问了:" + elem.getElemName());
    }

    @Override
    public void visitConcreteElemB(ConcreteElementB elem) {
        System.out.println("访问者A访问了:" + elem.getElemName());
    }
}
