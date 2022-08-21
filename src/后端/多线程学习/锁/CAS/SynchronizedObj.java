package 后端.多线程学习.锁.CAS;

public class SynchronizedObj {
    private int num;

    public int getNum() {
        return num;
    }

    public synchronized void increase() {
        num++;
    }

}
