package 后端.Java常用设计模式.解释器模式.base_example;

/**
 * @author shaobin
 * @date 2022/6/29 11:09
 */
public class Context {
    private String text;

    public Context(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
