package 后端.Java常用设计模式.建造者模式;

/**
 * @author shaobin
 * @date 2022/4/14 18:04
 */
public class ComputerModelA extends ComputerBuilder {

    @Override
    String mainBoard() {
        return "华硕Z系列主板";
    }

    @Override
    String cpu() {
        return "英特尔12代CPU";
    }

    @Override
    String memory() {
        return "三星32G内存";
    }

    @Override
    String hardDisk() {
        return "三星512G硬盘";
    }
}
