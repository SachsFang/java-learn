package com.fang.后端.Java常用设计模式.访问者模式.男女对比;

/**
 * @author shaobin
 * @date 2022/7/1 11:43
 */
public class Client {
    public static void main(String[] args) {
        Person man = new Man();
        Person women = new Women();
        PersonCompareStruct personCompareStruct = new PersonCompareStruct();
        personCompareStruct.attach(man);
        personCompareStruct.attach(women);

        Status success = new Success();
        Status fail = new Fail();
        personCompareStruct.accept(success);
        personCompareStruct.accept(fail);
        // 增加新的访问者“恋爱”只需添加一个类并在客户端调用即可，开闭原则
        Status fallInLove = new FallInLove();
        personCompareStruct.accept(fallInLove);
    }
}
