package com.fang.后端.封装自定义数据类型;

/**
 * Created by SachsFang on 2021/2/21 19:14
 */
public class Student {
    private int id;
    private String name;
    private long number;

    public Student(String name, long number) {
        this.name = name;
        this.number = number;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name can not be null");
        }
        this.name = name;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        if (number < 0) {
            throw new IllegalArgumentException("number can not be negative");
        }
        this.number = number;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
