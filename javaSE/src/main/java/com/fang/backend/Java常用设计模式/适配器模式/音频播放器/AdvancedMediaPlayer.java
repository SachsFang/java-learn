package com.fang.backend.Java常用设计模式.适配器模式.音频播放器;

/**
 * Created by SachsFang on 2021/11/1 11:39
 * 更高级的媒体播放器接口
 */
public interface AdvancedMediaPlayer {
    public void playVlc(String fileName);
    public void playMp4(String fileName);
}
