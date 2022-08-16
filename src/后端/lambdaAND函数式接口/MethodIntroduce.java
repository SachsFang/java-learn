package 后端.lambdaAND函数式接口;

import java.util.OptionalLong;
import java.util.function.LongBinaryOperator;
import java.util.stream.LongStream;

/**
 * 方法引入
 *
 * @author shaobin
 * @date 2022/8/15 18:00
 */
public class MethodIntroduce {
    public static void main(String[] args) {
        example3();
    }

    public static void example1() {
        BaseMethod.GreetingService lambdaInterface = value -> get(value);
        lambdaInterface.sayMessage("我是lambda表达式写法");
        BaseMethod.GreetingService methodIntroduceInterface = MethodIntroduce::get;
        methodIntroduceInterface.sayMessage("我是方法引入写法");
    }

    private static void get(String value) {
        System.out.println(value);
    }

    public static void example2() {
        LongStream longStream = LongStream.rangeClosed(0, 500);//创建流
//        OptionalLong nativeWritingOptional = longStream.reduce(new LongBinaryOperator() {
//            @Override
//            public long applyAsLong(long left, long right) {
//                return left + right;
//            }
//        });
        LongBinaryOperator longBinaryOperator = Long::sum;
        OptionalLong lambdaWritingOptional = longStream.reduce(longBinaryOperator);
        System.out.println(lambdaWritingOptional.getAsLong());
    }

    public static void example3() {
        //构造器引用
//        UserInterface userInterface1 = () -> new UserEntity();
        UserInterface userInterface =  UserEntity::new;
        System.out.println(userInterface.getUser());
        // 对象方法引用，因为引用的不是静态方法，所以需要传入相应的对象实例，并调用实例方法
//        InfoInterface infoInterface = (userEntity -> {
//            return userEntity.getMess();
//        });
        InfoInterface infoInterface = UserEntity::getMess;
        System.out.println(infoInterface.getMess(new UserEntity()));
        // 实例方法引用
        MessInterface messInterface3 = new UserEntity()::getMess;
        System.out.println(messInterface3.getMess());
        //静态方法引用
        MessInterface messInterface4 = UserEntity::staticGetMess;
        System.out.println(messInterface4.getMess());
    }
    public interface UserInterface {
        UserEntity getUser();
    }
    public interface MessInterface {
        String getMess();
    }
    public interface InfoInterface {
        String getMess(UserEntity userEntity);
    }
}