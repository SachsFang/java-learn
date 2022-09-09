package com.fang.后端.Java常用设计模式.原型模式;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * @author shaobin
 * @date 2022/4/9 11:59
 */
public class Main {
    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        // 原型对象
        Resume resume = new Resume();
        resume.setName("小方");
        resume.setSex("boy");
        Enclosure enclosure = new Enclosure("dataFile", "data");
        resume.setEnclosure(enclosure);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2022);
        calendar.set(Calendar.MONTH, 3);
        calendar.set(Calendar.DAY_OF_MONTH, 22);
        Date date = calendar.getTime();
        resume.setDepartureTime(date);
        // 克隆对象
        Resume cloneResume = (Resume) resume.clone();
        // 深克隆对象
        Resume deepCloneResume = resume.deepClone();
        // 改日期深浅克隆
        calendar.set(Calendar.DAY_OF_MONTH, 25);
        date.setTime(12345);
        resume.setDepartureTime(date);
        enclosure.setFileName("docFile");
        enclosure.setFileType("doc");

        System.out.println(resume.toString());
        System.out.println(cloneResume.toString());
        System.out.println(deepCloneResume.toString());
        System.out.println(resume == cloneResume);

    }
}
