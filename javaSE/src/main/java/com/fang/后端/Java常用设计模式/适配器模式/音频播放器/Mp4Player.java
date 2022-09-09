package com.fang.后端.Java常用设计模式.适配器模式.音频播放器;

/**
 * Created by SachsFang on 2021/11/1 11:42
 * 高级播放器实现类
 */
public class Mp4Player implements AdvancedMediaPlayer{

    @Override
    public void playVlc(String fileName) {
        //什么也不做
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: "+ fileName);
    }
}
