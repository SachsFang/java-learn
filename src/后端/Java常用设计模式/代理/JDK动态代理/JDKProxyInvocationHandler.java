package 后端.Java常用设计模式.代理.JDK动态代理;

import 后端.Java常用设计模式.代理.MyAspect;
import 后端.Java常用设计模式.代理.UserDaoImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxyInvocationHandler implements InvocationHandler {

    private Object target;

    public JDKProxyInvocationHandler() {
    }

    public JDKProxyInvocationHandler(Object target) {
        this.target = target;
    }

    public Object createProxy(Object target) {
        this.target = target;
        // 类加载器 - 第一个参数传递的是目标对象的类加载器
        ClassLoader classLoader = target.getClass().getClassLoader();
        // 目标对象实现的所有接口 - 第二个参数
        Class<? >[] interfaces = target.getClass().getInterfaces();
        // 自己实现的 JDKProxy InvocationHandler - 第三个参数
        // 生成代理类对象
        return Proxy.newProxyInstance(classLoader, interfaces, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[]  args) throws Throwable {
        MyAspect aspect = new MyAspect();
        aspect.checkPermissions();
        // 在目标类调用方法，并传入参数(invoke的第一个参数需是目标类target，因为调用的是目标类的方法，而不能是代理类proxy）
        Object obj = method.invoke(target, args);
        aspect.log();
        return obj;
    }
}
