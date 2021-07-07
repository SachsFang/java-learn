package 后端.内部类;

/**
 * Created by SachsFang on 2021/7/7 10:18
 */
public class main {
    public static void main(String[] args) {
        Car car1 = new CarImpl();

        Car car2 = new Car() { //匿名子类 - 当代码少的时候方便,不需要显式地写一个实现子类
            @Override
            public void openDoor() {

            }

            @Override
            public String config() {
                return null;
            }

            @Override
            public void run() {

            }
        };
    }
}
