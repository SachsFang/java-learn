package com.fang.后端.Java常用设计模式.责任链模式.请假;

/**
 * @author shaobin
 * @date 2022/6/9 10:43
 */
public abstract class Manager {

    private Manager superior;

    public void setSuperior(Manager superior) {
        this.superior = superior;
    }

    public Manager getSuperior() {
        return superior;
    }

    /**
     * 执行员工请求
     */
    public abstract void doRequest(Request request);

}
