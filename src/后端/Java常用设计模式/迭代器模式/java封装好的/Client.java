package 后端.Java常用设计模式.迭代器模式.java封装好的;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author shaobin
 * @date 2022/4/26 16:18
 */
public class Client {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        list.add("fang");
        list.add(100);
        list.add(200);
        list.add(300);
        list.add("ok");

        Iterator<Object> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
