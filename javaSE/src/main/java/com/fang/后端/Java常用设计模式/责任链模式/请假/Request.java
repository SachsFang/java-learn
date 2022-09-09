package com.fang.后端.Java常用设计模式.责任链模式.请假;

/**
 * @author shaobin
 * @date 2022/6/9 15:49
 */
public class Request {

    private String name;

    private int reqType;

    private int reqAmount;

    public Request(String name, int reqType, int reqAmount) {
        this.name = name;
        this.reqType = reqType;
        this.reqAmount = reqAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReqType() {
        return reqType;
    }

    public void setReqType(int reqType) {
        this.reqType = reqType;
    }

    public int getReqAmount() {
        return reqAmount;
    }

    public void setReqAmount(int reqAmount) {
        this.reqAmount = reqAmount;
    }
}
