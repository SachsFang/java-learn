package com.fang.后端.Java常用设计模式.装饰模式;

/**
 * @author shaobin
 * @date 2022/3/21 16:38
 */
public class ClothesDecorator extends Person {// 继承接口会更标准，也就是通过Decorator继承抽象，抽象有多个实现类

    private Person person;

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public void show() {
        if (this.person != null) {
            this.person.show();
        }
    }
}
