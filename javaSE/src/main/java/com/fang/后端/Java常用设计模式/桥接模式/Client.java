package com.fang.后端.Java常用设计模式.桥接模式;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        // software
        PhoneSoftWare weChat = new WeChat();
        PhoneSoftWare chrome = new Chrome();
        List<PhoneSoftWare> phoneSoftWares = new ArrayList<>();
        phoneSoftWares.add(weChat);
        phoneSoftWares.add(chrome);
        // brand
        XiaoMi xiaoMi = new XiaoMi(phoneSoftWares);
        xiaoMi.showSoftware();
        OnePlus onePlus = new OnePlus(phoneSoftWares);
        onePlus.showSoftware();
    }
}
