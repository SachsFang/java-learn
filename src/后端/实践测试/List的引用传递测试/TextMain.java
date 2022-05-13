package 后端.实践测试.List的引用传递测试;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shaobin
 * @date 2022/4/29 15:20
 */
public class TextMain {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
//        List<BigDecimal> list = new ArrayList<>();
//        list.add(new BigDecimal("1"));
        Obj obj = new Obj();
        List<BigDecimal> list = obj.getObjList();
        edit(list);
        System.out.println(list);
    }

    private static void edit(List<BigDecimal> list) {// list参数是一个引用
        list.set(0, new BigDecimal("-1"));
        list.add(new BigDecimal("2"));
        List<BigDecimal> listA = new ArrayList<>();
        listA.add(new BigDecimal("-1"));
        // list替换了引用，所以函数外不生效
        list = listA;
    }
}
