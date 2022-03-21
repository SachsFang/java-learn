package 后端.Java常用设计模式.装饰模式;

/**
 * @author shaobin
 * @date 2022/3/21 16:52
 */
public class TShirt extends ClothesDecorator {

    @Override
    public void show() {
        // 这里是增加的功能代码
        System.out.print("TShirt ");
        // 这里是原被装饰类的功能
        super.show();
    }
}
