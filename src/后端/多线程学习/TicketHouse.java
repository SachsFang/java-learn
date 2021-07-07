package 后端.多线程学习;

/**
 * Created by SachsFang on 2021/7/6 14:40
 * 协调同步线程-synchronized后的 wait() notify() notifyAll()方法
 */
public class TicketHouse implements Runnable {
    /*售票处的剩余零钱*/
    int fiveAmount = 2;
    int tenAmount = 0;
    int twentyAmount = 0;
    /*声明线程*/
    Thread sachs;
    Thread jack;

    TicketHouse() {
        sachs = new Thread(this);
        jack = new Thread(this);
        sachs.setName("sachs");
        jack.setName("jack");
    }

    @Override
    public void run() {
        Thread person = Thread.currentThread();
        if (person == sachs) {
            saleTicket(20);
        } else if (person == jack) {
            try {
                Thread.sleep(100);//此处保证让sachs先买
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            saleTicket(5);
        }
    }

    public synchronized void saleTicket(int money) {
        if (money == 5) {
            fiveAmount+=1;
            System.out.println("电影票5元一张，刚好不用找,"+Thread.currentThread().getName()+"买票了");
        } else if (money == 20) {
            if (fiveAmount < 3) {
                System.out.println("不够钱找了，等下一位买票了再找");
                try {
                    wait();//如果这里改成 Thread.sleep(5000) 因为方法有synchronized修饰 sleep方法还在占用着该方法 所以其它线程无法进来
                    System.out.println("SS");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            twentyAmount+=1;
            fiveAmount-=3;
            System.out.println("电影票5元一张，找钱,"+Thread.currentThread().getName()+"买票了");
        }
        notifyAll();
    }
}
