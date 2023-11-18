package com.fang.backend.多线程学习;

/**
 * Created by SachsFang on 2021/7/5 20:03
 * 多线程主函数
 */
public class OilStationMain {
    public static void main(String[] args) {
        /*OilStation oilStation1 = new OilStation();
        oilStation.setOil(200);

        Thread bx1 = new Thread(oilStation1);
        Thread bx3 = new Thread(oilStation1);
        bx1.setName("bx1");
        bx3.setName("bx3");
        bx1.start();
        bx3.start();*/

        /*OilStation2 oilStation2 = new OilStation2();
        oilStation2.setOil(400);
        oilStation2.bx1.start();
        oilStation2.bx3.start();*/

        OilStation3 oilStation3 = new OilStation3();
        oilStation3.oilTank.start();
        oilStation3.bx1.start();

        /*TicketHouse ticketHouse = new TicketHouse();
        ticketHouse.sachs.start();
        ticketHouse.jack.start();*/
    }
}
