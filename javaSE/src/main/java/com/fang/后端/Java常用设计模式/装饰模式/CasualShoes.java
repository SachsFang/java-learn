package com.fang.后端.Java常用设计模式.装饰模式;

/**
 * @author shaobin
 * @date 2022/3/21 17:00
 */
public class CasualShoes extends ClothesDecorator {

    @Override
    public void show() {
        System.out.print("休闲鞋 ");
        super.show();
    }
}
