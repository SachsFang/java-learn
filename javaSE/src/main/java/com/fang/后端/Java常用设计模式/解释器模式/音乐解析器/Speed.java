package com.fang.后端.Java常用设计模式.解释器模式.音乐解析器;

/**
 * @author shaobin
 * @date 2022/6/30 10:19
 */
public class Speed extends AbstractMusicParser {
    @Override
    String execute(String key, String value) {
        Integer speedValue = Integer.valueOf(value);
        if (speedValue < 500) {
            return "快速";
        } else if (speedValue >= 500 && speedValue < 1000) {
            return "中速";
        } else {
            return "慢速";
        }
    }
}
