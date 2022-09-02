package 后端.反射;

import 后端.lambdaAND函数式接口.UserEntity;

import java.lang.reflect.*;

/**
 * @author shaobin
 * @date 2022/8/17 16:12
 */
public class BaseExample {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchFieldException {
        Class<String> strClass = String.class;// 获取String的Class对象
        // 通过class对象获取类的各种信息ou
        String name = strClass.getName();
        Field[] fields = strClass.getFields();
        Method[] methods = strClass.getMethods();
        Constructor<String> publicConstructors = strClass.getConstructor();// 获取所有的公有构造方法
        Constructor<?>[] declaredConstructors = strClass.getDeclaredConstructors();// 获取所有的构造方法（包括私有、公有、受保护、默认）
        Class<?>[] annotatedInterfaces = strClass.getInterfaces();
        // 获取构造器、创建实例
        Constructor<String> constructor = strClass.getConstructor(String.class);// 参数为目标类构造方法参数的class类，无参构造方法则不用传递该参数
        Object obj = constructor.newInstance("hello reflection");// 根据构造器创建实例
        Class<?> stringClass = Class.forName("java.lang.String");
        System.out.println(stringClass);
        // 给成员私有属性赋值
        Class<?> userEntityClass = Class.forName("后端.lambdaAND函数式接口.UserEntity");
        UserEntity userEntity = (UserEntity) userEntityClass.newInstance();
        Field userNameField = userEntityClass.getDeclaredField("userName");// 获取私有属性
        userNameField.setAccessible(true);// 因为是私有属性，所以需要设置可访问权限
        userNameField.set(userEntity, "userName");
        System.out.println(userEntity);
        // 调用私有方法
        Method setUserNameMethod = userEntityClass.getDeclaredMethod("privateSetUserName", String.class);
        setUserNameMethod.setAccessible(true);
        setUserNameMethod.invoke(userEntity, "fang");
        System.out.println(userEntity);
    }
}
