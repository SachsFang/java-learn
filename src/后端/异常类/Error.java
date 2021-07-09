package 后端.异常类;

/**
 * Created by SachsFang on 2021/7/8 20:27
 */
public class Error {
    Error() {

    }

    public static void main(String[] args) {
        try {
            test();
        } catch (NumberFormatException e) {
            System.out.println("到这里处理了");
        }
        System.out.println("继续执行");
    }

    /*
     * throws的作用是不在本方法中进程异常处理，而是抛给调用此方法的类中进行处理
     * 一般在接口实现中使用这种异常处理机制，给调用接口者，灵活处理
     * */
    public static void test() throws NumberFormatException {
        int number = Integer.parseInt("ab80");
    }
}
