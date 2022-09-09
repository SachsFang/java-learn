package com.fang.后端.Java常用设计模式.责任链模式.请假;

/**
 * 总经理类
 *
 * @author shaobin
 * @date 2022/6/9 15:48
 */
public class GeneralManager extends Manager {
    @Override
    public void doRequest(Request request) {
        if (request.getReqType() == 1 && request.getReqAmount() <= 30) {
            System.out.println("总经理批准" + request.getName() + "请假" + request.getReqAmount() + "天");
        } else if (request.getReqType() == 1 && request.getReqAmount() > 30) {
            System.out.println("总经理不批准请假超过30天");
        } else {
            System.out.println("总经理暂不批准其它申请");
        }
    }
}
