package com.fang.后端.Java常用设计模式.建造者模式;

/**
 * @author shaobin
 * @date 2022/4/14 17:51
 */
abstract class ComputerBuilder {
    /**
     * 主板
     */
    abstract String mainBoard();

    /**
     * cpu
     */
    abstract String cpu();

    /**
     * 内存
     */
    abstract String memory();

    /**
     * 硬盘
     */
    abstract String hardDisk();

}
