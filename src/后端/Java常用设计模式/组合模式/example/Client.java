package 后端.Java常用设计模式.组合模式.example;

/**
 * @author shaobin
 * @date 2022/4/21 17:16
 */
public class Client {
    public static void main(String[] args) {
        // 根节点
        Composite root = new Composite("北京总公司");
        // 第一层节点
        Composite firstOneComposite = new Composite("华东分公司");
        Leaf firstOneLeaf = new Leaf("总公司财务部");
        Leaf firstTwoLeaf = new Leaf("总公司人事部");
        root.add(firstOneLeaf);
        root.add(firstTwoLeaf);
        root.add(firstOneComposite);
        // 第二层节点
        Composite secondOneComposite = new Composite("南京办事处");
        Composite secondTwoComposite = new Composite("杭州办事处");
        Leaf secondOneLeaf = new Leaf("华东分公司人事部");
        Leaf secondTwoLeaf = new Leaf("华东分公司财务部");
        firstOneComposite.add(secondOneComposite);
        firstOneComposite.add(secondTwoComposite);
        firstOneComposite.add(secondOneLeaf);
        firstOneComposite.add(secondTwoLeaf);
        // 第三层节点
        Leaf thirdOneLeaf = new Leaf("南京办事处人事部");
        Leaf thirdTwoLeaf = new Leaf("南京办事处财务部");
        Leaf thirdThreeLeaf = new Leaf("杭州办事处人事部");
        Leaf thirdFourLeaf = new Leaf("杭州办事处财务部");
        secondOneComposite.add(thirdOneLeaf);
        secondOneComposite.add(thirdTwoLeaf);
        secondTwoComposite.add(thirdThreeLeaf);
        secondOneComposite.add(thirdFourLeaf);

        root.display(1);
    }
}