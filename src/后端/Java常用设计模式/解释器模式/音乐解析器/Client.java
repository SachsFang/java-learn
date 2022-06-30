package 后端.Java常用设计模式.解释器模式.音乐解析器;

/**
 * 音乐解析器客户端
 * 音阶：O 1、O 2、O 3 代表 低音阶、中音阶、高音阶
 * 音符：C、D、E、F、G、A、B 代表、Do Re、Mi、Fa、So、La、Ti 长度：0.25 四分之一拍 0.5半拍 1一拍 2两拍
 * 音速：<500 - 快速 、 500<= or <1000 中速 、 >=1000 快速
 *
 * @author shaobin
 * @date 2022/6/29 11:19
 */
public class Client {
    public static void main(String[] args) {
        System.out.println(MusicParserUtil.parse("T 500 O 1 C 1 F 0.5 D 2"));
    }
}
