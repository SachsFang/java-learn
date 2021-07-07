package 函数式接口;

import java.util.*;

/**
 * Created by SachsFang on 2021/5/8 20:03
 */
public class baseMethod {
    /*函数式定义方法*/
    @FunctionalInterface
    interface GreetingService {
        void sayMessage(String message);
    }

    public static void main(String[] args) {
        /*lambda方式的方法实现*/
        GreetingService greetService = message -> System.out.println("Hello " + message);
        greetService.sayMessage("我懂了");

        GreetingService otherGreetService = params -> System.out.println("hello world" + params);
        otherGreetService.sayMessage("哈哈");

        /*方法引用的方式实现*/
        GreetingService quote = baseMethod::getMessage;
        quote.sayMessage("引用的方式");


        LinkedList<String> a = new LinkedList<String>();
        Collections.sort(a);
        HashMap<String, String> c = new HashMap<>();
        Map<String, String> d = new HashMap<String, String>();
        TreeSet<Integer> treeSet = new TreeSet<Integer>();
        treeSet.add(10);
        treeSet.add(5);
        treeSet.add(4);
        Iterator iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static void getMessage(String str) {
        System.out.println(str + ":我后面通过方法引用的方式给GreetingService实现默认抽象方法");
    }
}
