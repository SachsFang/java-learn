package com.fang.backend.Java常用设计模式.模板方法模式;

/**
 * @author shaobin
 * @date 2022/4/13 18:30
 */
public class Student extends TestPaper {

    private String answerOne;
    private String answerTwo;
    private String answerThree;

    public Student(String answerOne, String answerTwo, String answerThree) {
        this.answerOne = answerOne;
        this.answerTwo = answerTwo;
        this.answerThree = answerThree;
        super.answerTestPaper();
    }

    @Override
    String answerOne() {
        return this.answerOne;
    }

    @Override
    String answerTwo() {
        return this.answerTwo;
    }

    @Override
    String answerThree() {
        return this.answerThree;
    }
}
