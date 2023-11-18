package com.fang.backend.Java常用设计模式.解释器模式.音乐解析器;

/**
 * @author shaobin
 * @date 2022/6/29 11:30
 */
public class Note extends AbstractMusicParser {

    @Override
    String execute(String key, String value) {
        String result = "";
        switch (key) {
            case "C":
                result += "Do";
                break;
            case "D":
                result += "Re";
                break;
            case "E":
                result += "Mi";
                break;
            case "F":
                result += "Fa";
                break;
            case "G":
                result += "So";
                break;
            case "A":
                result += "La";
                break;
            case "B":
                result += "Ti";
                break;
        }
        switch (value) {
            case "0.25":
                result += "四分之一拍";
                break;
            case "0.5":
                result += "半拍";
                break;
            case "1":
                result += "一拍";
                break;
            case "2":
                result += "两拍";
                break;
        }
        return result;
    }
}
