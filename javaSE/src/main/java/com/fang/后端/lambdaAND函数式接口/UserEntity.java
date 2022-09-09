package com.fang.后端.lambdaAND函数式接口;

/**
 * @author shaobin
 * @date 2022/8/16 11:08
 */
public class UserEntity {
    private String userName;
    private int age;

    public UserEntity(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    public UserEntity() {
    }
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static String staticUserName() {
        return "static mess";
    }

    private void privateSetUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
