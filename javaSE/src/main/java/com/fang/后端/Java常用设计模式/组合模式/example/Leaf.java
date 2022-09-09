package com.fang.后端.Java常用设计模式.组合模式.example;

/**
 * 树叶节点
 *
 * @author shaobin
 * @date 2022/4/21 16:18
 */
public class Leaf extends Component {

    public Leaf(String name) {
        super(name);
    }

    @Override
    public void add(Component component) {
        System.out.println("Leaf can not add");
    }

    @Override
    public void remove(Component component) {
        System.out.println("Leaf can not remove");
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
    }
}
