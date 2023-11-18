package com.fang.backend.Java常用设计模式.解释器模式.音乐解析器;

import com.fang.backend.Java常用设计模式.解释器模式.base_example.Context;

import java.util.Arrays;
import java.util.List;

/**
 * @author shaobin
 * @date 2022/6/29 11:32
 */
public abstract class AbstractMusicParser {

    public String parse(Context context) {
        String text = context.getText();
        List<String> splitTextList = Arrays.asList(text.split(" "));
        if (splitTextList.size() < 2) {
            return null;
        }
        String key = splitTextList.get(0);
        String value = splitTextList.get(1);
        context.setText(splitTextList.size() == 2 ? text.substring(key.length() + value.toString().length() + 1) : text.substring(key.length() + value.toString().length() + 2));
        return execute(key, value);
    }

    abstract String execute(String key, String value);
}
