package 后端.Java常用设计模式.代理.JDK动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;
import 后端.Java常用设计模式.代理.UserDao;
import 后端.Java常用设计模式.代理.UserDaoImpl;

/**
 * 该文件是拷贝了自动生成的 UserDaoImpl 类的代理对象 $Proxy0，用于研究代理过程
 * @author shaobin
 * @date 2022/9/6 17:10
 */
public final class AutoCreateUserDaoImplProxy extends Proxy implements UserDao {
    public static void main(String[] args) {
        // 调试
        AutoCreateUserDaoImplProxy autoCreateUserDaoImplProxy = new AutoCreateUserDaoImplProxy(new JDKProxyInvocationHandler(new UserDaoImpl()));
        autoCreateUserDaoImplProxy.addUser("fang", 22);
    }
    private static Method m1;
    private static Method m3;
    private static Method m2;
    private static Method m0;
    private static Method m4;

    static {
        try {
            // 反射获各方法
            m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
            m3 = Class.forName("后端.Java常用设计模式.代理.UserDao").getMethod("addUser", Class.forName("java.lang.String"), Class.forName("java.lang.Integer"));
            m2 = Class.forName("java.lang.Object").getMethod("toString");
            m0 = Class.forName("java.lang.Object").getMethod("hashCode");
            m4 = Class.forName("后端.Java常用设计模式.代理.UserDao").getMethod("deleteUser");
        } catch (NoSuchMethodException var2) {
            throw new NoSuchMethodError(var2.getMessage());
        } catch (ClassNotFoundException var3) {
            throw new NoClassDefFoundError(var3.getMessage());
        }
    }

    public AutoCreateUserDaoImplProxy(InvocationHandler var1) {
        super(var1);
    }

    public final boolean equals(Object var1) {
        try {
            return (Boolean)super.h.invoke(this, m1, new Object[]{var1});
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final void addUser(String var1, Integer var2) {
        try {
            // 这里调回了 invocationHandler 的实现对象
            super.h.invoke(this, m3, new Object[]{var1, var2});
        } catch (RuntimeException | Error var4) {
            throw var4;
        } catch (Throwable var5) {
            throw new UndeclaredThrowableException(var5);
        }
    }

    public final String toString() {
        try {
            return (String)super.h.invoke(this, m2, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final int hashCode() {
        try {
            return (Integer)super.h.invoke(this, m0, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final void deleteUser() {
        try {
            super.h.invoke(this, m4, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }
}

