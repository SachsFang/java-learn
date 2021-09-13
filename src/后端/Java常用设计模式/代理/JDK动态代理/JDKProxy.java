package 后端.Java常用设计模式.代理.JDK动态代理;

import 后端.Java常用设计模式.代理.MyAspect;
import 后端.Java常用设计模式.代理.UserDao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy implements InvocationHandler {

    private UserDao userDao;

    public Object createProxy(UserDao userDao) {
        this.userDao = userDao;
        // 类加载器
        ClassLoader classLoader = JDKProxy.class.getClassLoader();
        // 被代理对象实现的所有接口
        Class[] interfaces = userDao.getClass().getInterfaces();
        // 使用代理类进行增强，返回代理后的对象
        return Proxy.newProxyInstance(classLoader, interfaces, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MyAspect aspect = new MyAspect();
        aspect.checkPermissions();
        // 在目标类调用方法，并传入参数
        Object obj = method.invoke(userDao, args);
        aspect.log();
        return obj;
    }
}
