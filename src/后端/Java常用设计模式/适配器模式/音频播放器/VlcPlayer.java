package 后端.Java常用设计模式.适配器模式.音频播放器;

/**
 * Created by SachsFang on 2021/11/1 11:41
 * 高级播放器实现类
 */

public class VlcPlayer implements AdvancedMediaPlayer{
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: "+ fileName);
    }

    @Override
    public void playMp4(String fileName) {
        //什么也不做
    }
}
