package com.fang.后端.Java常用设计模式.责任链模式.请假;

/**
 * @author shaobin
 * @date 2022/6/9 18:20
 */
public class Client {
    public static void main(String[] args) {
        // 初始化责任链
        TechnicalManager technicalManager = new TechnicalManager();
        TechnicalDirector technicalDirector = new TechnicalDirector();
        GeneralManager generalManager = new GeneralManager();
        technicalManager.setSuperior(technicalDirector);
        technicalDirector.setSuperior(generalManager);
        // 申请
        Request req1 = new Request("小蓝", 1, 5);
        technicalManager.doRequest(req1);
        Request req2 = new Request("小蓝", 1, 15);
        technicalManager.doRequest(req2);
        Request req3 = new Request("小红", 1, 25);
        technicalManager.doRequest(req3);
        Request req4 = new Request("小方", 1, 40);
        technicalManager.doRequest(req4);
        Request req5 = new Request("小方", 2, 5);
        technicalManager.doRequest(req5);
    }
}
