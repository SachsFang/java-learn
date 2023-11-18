package com.fang.backend.多线程学习.锁.CAS;

import java.util.concurrent.atomic.AtomicInteger;

public class CASObj {
    AtomicInteger atomicInteger = new AtomicInteger();

    public int getNum() {
        return atomicInteger.get();
    }

    public void increase() {
        atomicInteger.incrementAndGet();
        /* 上面的方法做了如下操作 */
        /*while (true) {//为了防止compareAndSet前，如果有其它线程进入改了 oldValue，那么此时 compareAndSet 肯定不成功，则需再重新执行一遍，这里就是为了达到重试的目的
            int oldValue = atomicInteger.get();
            int newValue = atomicInteger.get() + 1;
            if (atomicInteger.compareAndSet(oldValue, newValue)) {//CAS 判断是否新旧值赋值成功。compareAndSet方法是原子操作的，因为底层对内存加锁了（汇编级别加了lock）,也就是硬件级别加锁（缓存行锁/总线锁）
                break;
            }
        }*/

    }
}
