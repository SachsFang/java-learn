package com.fang.backend.集合.map.linkedHashMap.简单使用;

import java.util.LinkedHashMap;

/**
 * @author shaobin
 * @date 2023/3/6 16:26
 */
public class Main {
    public static void main(String[] args) {
        // access=false 默认排序，按插入的顺序排序
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        // access=true的情况下按访问顺序排序
//        LinkedHashMap<String, String> map = new LinkedHashMap<>(16, 0.75f, true);
        map.put("a", "a");
        map.put("b", "b");
        map.put("c", "c");
        map.put("a", "aaa");
        String b = map.get("b");
        map.forEach((key, value) -> {
            System.out.println(key + "/" + value);
        });
    }
}
