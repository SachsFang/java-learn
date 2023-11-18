package com.fang.backend.自定义注解;

/**
 * @author shaobin
 * @date 2022/8/26 19:05
 */
public class BaseExample {
    public static void main(String[] args) {

    }

    @MyAnnotation
    public void text() {
        System.out.println("Annotation");
    }

}
