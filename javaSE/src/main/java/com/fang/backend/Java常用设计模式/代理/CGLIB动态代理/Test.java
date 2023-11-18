package com.fang.backend.Java常用设计模式.代理.CGLIB动态代理;

import com.fang.backend.Java常用设计模式.代理.UserDaoImpl;
import net.sf.cglib.core.DebuggingClassWriter;

/**
 * Created by SachsFang on 2021/10/22 17:03
 */
public class Test {
    public static void main(String[] args) {
        // 将CGLIB动态代理生成好的 class 文件存放到本地（生成后放在 /com/sun/proxy 以方便研究代理类
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\ideaProject\\myself\\java-learn\\javaSE\\src\\main\\java\\com\\fang\\后端\\Java常用设计模式\\代理\\CGLIB动态代理\\自动生成的代理类");
        // 创建代理对象
        CglibProxy cglibProxy = new CglibProxy();
        // 创建目标对象
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        // 获取增强后的目标对象
        UserDaoImpl userDao = cglibProxy.createProxy(userDaoImpl);
        // 执行方法
        userDao.addUser("fang", 22);
        userDao.deleteUser();
    }
}
