package com.fang.backend.Java常用设计模式.适配器模式.音频播放器;

/**
 * Created by SachsFang on 2021/11/1 11:38
 * 普通媒体播放器接口
 */
public interface MediaPlayer {
    public void play(String audioType,  String fileName);
}
