package com.fang.后端.Java常用设计模式.适配器模式.手机适配器;

/**
 * Created by SachsFang on 2021/7/15 17:38
 */
public class Adapter extends Phone {

    private Socket socket;

    public Adapter() {
        this.socket = new Socket();
    }

    @Override
    public void usePower5v() {
        socket.use220v();
    }
}
