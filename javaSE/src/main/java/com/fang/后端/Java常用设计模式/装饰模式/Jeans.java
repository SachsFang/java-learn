package com.fang.后端.Java常用设计模式.装饰模式;

/**
 * @author shaobin
 * @date 2022/3/21 16:57
 */
public class Jeans extends ClothesDecorator {

    @Override
    public void show() {
        System.out.print("牛仔裤 ");
        super.show();
    }
}
