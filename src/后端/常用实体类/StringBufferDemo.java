package 后端.常用实体类;

/**
 * Created by SachsFang on 2021/7/12 15:29
 */
public class StringBufferDemo {
    public static void main(String[] args) {
        StringBufferTest();
    }

    public static void StringBufferTest() {
        String str = "I want to buy ";
        StringBuffer stringBuffer = new StringBuffer(str);
        stringBuffer.append("bx1");
        System.out.println(stringBuffer.charAt(14));
        stringBuffer.setCharAt(16, '3');
        System.out.println(stringBuffer.insert(17, " and q3"));
        stringBuffer.delete(17, 24);
        stringBuffer.replace(14, 17, "宝马");
        System.out.println(stringBuffer.toString());
        stringBuffer.reverse();
        System.out.println(stringBuffer.toString());
    }
}
