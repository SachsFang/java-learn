package 后端.Java常用设计模式.组合模式.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 树枝类（具体组合类）
 * @author shaobin
 * @date 2022/4/21 17:11
 */
public class Composite extends Component {

    private List<Component> componentList;

    public Composite(String name) {
        super(name);
        this.componentList = new ArrayList<>();
    }

    @Override
    public void add(Component component) {
        if (component != null) {
            componentList.add(component);
        }
    }

    @Override
    public void remove(Component component) {
        if (component != null) {
            componentList.remove(component);
        }
    }

    @Override
    public void display(int depth) {
        String str = "-";
        StringBuilder depthStrBuilder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            depthStrBuilder.append(str);
        }
        System.out.print(depthStrBuilder.toString());
        System.out.println(this.name);
        for (Component component : componentList) {
            component.display(depth + 2);
        }
    }
}
