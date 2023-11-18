package com.fang.backend.Java常用设计模式.建造者模式;

/**
 * 电脑构建指挥者
 * 指挥者控制构建流程，电脑配置细节通过建造者提供可实现不同的表示
 * @author shaobin
 * @date 2022/4/14 18:17
 */
public class ComputerDirector {

    Computer computer;

    public Computer construct(ComputerBuilder  computerBuilder) {
        computer = new Computer();
        // 先装主板
        computer.addPart(computerBuilder.mainBoard());
        // 再装CPU
        computer.addPart(computerBuilder.cpu());
        // 再装内存
        computer.addPart(computerBuilder.memory());
        // 最后装硬盘
        computer.addPart(computerBuilder.hardDisk());
        return computer;
    }

}
