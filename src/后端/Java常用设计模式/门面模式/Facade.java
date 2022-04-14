package 后端.Java常用设计模式.门面模式;

/**
 * 门面类
 * @author shaobin
 * @date 2022/4/14 16:13
 */
public class Facade {

    private SubSystemOne subSystemOne;
    private SubSystemTwo subSystemTwo;
    private SubSystemThree subSystemThree;

    public Facade() {
        this.subSystemOne = new SubSystemOne();
        this.subSystemTwo = new SubSystemTwo();
        this.subSystemThree = new SubSystemThree();
    }

    public void facadeMethodOne() {
        System.out.println("facade method one:");
        subSystemOne.method();
        subSystemThree.method();
        System.out.println();
    }

    public void facadeMethodTwo() {
        System.out.println("facade method two:");
        subSystemThree.method();
        subSystemTwo.method();
        System.out.println();
    }

}
