package com.fang.后端.Java常用设计模式.中介者模式;

/**
 * @author shaobin
 * @date 2022/6/10 17:42
 */
public class ConcreteMediator extends Mediator{

    private Sachs sachs;

    private John john;

    public void setSachs(Sachs sachs) {
        this.sachs = sachs;
    }

    public void setJohn(John john) {
        this.john = john;
    }

    @Override
    public void send(String message, Colleague colleague) {
        if ("Sachs".equals(colleague.getClass().getSimpleName())) {
            john.notify(message);
        } else if ("John".equals(colleague.getClass().getSimpleName())) {
            sachs.notify(message);
        }
    }
}
