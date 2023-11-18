package com.fang.backend.Java常用设计模式.装饰模式;

/**
 * @author shaobin
 * @date 2022/3/21 17:02
 */
public class LeatherShoes extends ClothesDecorator {

    @Override
    public void show() {
        System.out.print("皮鞋 ");
        super.show();
    }
}
