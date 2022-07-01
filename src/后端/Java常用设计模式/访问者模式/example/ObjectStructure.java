package 后端.Java常用设计模式.访问者模式.example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shaobin
 * @date 2022/6/30 18:02
 */
public class ObjectStructure {
    /**
     * 被访问者列表
     */
    private List<Element> elements = new ArrayList<>();

    public void attach(Element element) {
        elements.add(element);
    }

    public void detach(Element element) {
        elements.remove(element);
    }

    public void accept(Visitor visitor) {
        elements.forEach(element -> {
            element.accept(visitor);
        });
    }

}
