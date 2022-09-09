package com.fang.后端.Java常用设计模式.访问者模式.example;

/**
 * 访问者接口
 *
 * @author shaobin
 * @date 2022/6/30 17:45
 */
public abstract class Visitor {
    /**
     * 访问元素A方法
     */
    abstract public void visitConcreteElemA(ConcreteElementA elem);

    /**
     * 访问元素B方法
     */
    abstract public void visitConcreteElemB(ConcreteElementB elem);
}
