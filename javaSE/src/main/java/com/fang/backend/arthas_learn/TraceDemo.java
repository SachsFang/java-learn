package com.fang.backend.arthas_learn;

import lombok.SneakyThrows;

/**
 * @author shaobin
 * @date 2023/10/11 10:21
 */
public class TraceDemo {
    public void traceFirst(String msg) {
        System.out.println("trace first method print:" + msg);
        traceSecond();
    }

    public void traceSecond() {
        System.out.println("trace second method print");
        traceThird();
    }

    @SneakyThrows
    public void traceThird() {
        Thread.sleep(1000);
        System.out.println("trace third method print");
    }
}
