package 后端.Java常用设计模式.解释器模式.音乐解析器;

import 后端.Java常用设计模式.解释器模式.base_example.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author shaobin
 * @date 2022/6/30 10:11
 */
public class MusicParserUtil {
    public static String parse(String text) {
        AbstractMusicParser abstractMusicParser = null;
        Context context = new Context(text);
        String parseResult = "";
        while (context.getText().length() >= 2) {
            List<String> textList = Arrays.asList(context.getText().split(" "));
            String key = textList.get(0);
            switch (key) {
                case "O":
                    abstractMusicParser = new Scale();
                    break;
                case "C":
                case "D":
                case "E":
                case "F":
                case "G":
                case "A":
                case "B":
                    abstractMusicParser = new Note();
                    break;
                case "T":
                    abstractMusicParser = new Speed();
            }
            if (abstractMusicParser instanceof AbstractMusicParser) {
                parseResult += abstractMusicParser.parse(context) + " ";
            }
        }
        return parseResult;
    }
}
