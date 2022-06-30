package 后端.Java常用设计模式.解释器模式.音乐解析器;

/**
 * @author shaobin
 * @date 2022/6/29 15:33
 */
public class Scale extends AbstractMusicParser {
    @Override
    String execute(String key, String value) {
        if (!key.equals("O")) {
            return null;
        }
        switch (value) {
            case "1":
                return "低音";
            case "2":
                return "中音";
            case "3":
                return "高音";
            default:
                return null;
        }
    }
}
