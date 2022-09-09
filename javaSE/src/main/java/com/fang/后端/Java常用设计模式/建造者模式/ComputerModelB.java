package com.fang.后端.Java常用设计模式.建造者模式;

/**
 * @author shaobin
 * @date 2022/4/14 18:13
 */
public class ComputerModelB extends ComputerBuilder {

    @Override
    String mainBoard() {
        return "技嘉B系列主板";
    }

    @Override
    String cpu() {
        return "英伟达6000CPU";
    }

    @Override
    String memory() {
        return "刚威16G内存";
    }

    @Override
    String hardDisk() {
        return "金士顿1T硬盘";
    }
}
