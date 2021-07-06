package 后端.多线程学习;

/**
 * Created by SachsFang on 2021/7/5 20:04
 * 多线程接口-目标对象和完全解耦写法
 */
public class OilStation1 implements Runnable {

    float oil=0;

    public void setOil(float oil) {
        this.oil = oil;
    }

    @Override
    public void run() {
        while (true) {
            String car = Thread.currentThread().getName();
            float carOil = 0;
            if ("bx1".equals(car)) {
                carOil = 35;
                oil-=carOil;
                System.out.println(car + "加油了");
            } else if ("bx3".equals(car)) {
                carOil = 60;
                oil-=carOil;
                System.out.println(car + "加油了");
            }
            System.out.println("油站还有" + oil + "L油)");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (oil <= 0) {
                return;
            }
        }
    }
}
