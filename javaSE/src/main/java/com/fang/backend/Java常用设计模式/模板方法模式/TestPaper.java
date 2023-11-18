package com.fang.backend.Java常用设计模式.模板方法模式;

/**
 * 试卷类
 * @author shaobin
 * @date 2022/4/11 22:55
 */
public abstract class TestPaper {

    abstract String answerOne();

    abstract String answerTwo();

    abstract String answerThree();

    public void  answerTestPaper() {
        System.out.print("Question one XXX, please choose the answer:" + answerOne() + " / ");
        System.out.print("Question two XXX, please choose the answer:" + answerTwo() + " / ");
        System.out.println("Question three XXX, please choose the answer:" + answerThree());
    }

}
