package 后端.Java常用设计模式.建造者模式;

/**
 * 组装电脑客户端
 * @author shaobin
 * @date 2022/4/14 17:46
 */
public class Client {
    public static void main(String[] args) {
        // 生产电脑的指挥者
        ComputerDirector computerDirector = new ComputerDirector();
        // 构建型号A电脑
        Computer computerModelA = computerDirector.construct(new ComputerModelA());
        System.out.println("modelA:" + computerModelA.getInfo());
        // 构建型号B电脑
        Computer computerModeB = computerDirector.construct(new ComputerModelB());
        System.out.println("modelB:" + computerModeB.getInfo());
    }
}
