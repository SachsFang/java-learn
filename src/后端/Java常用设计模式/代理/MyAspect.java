package 后端.Java常用设计模式.代理;

public class MyAspect {
    public void checkPermissions() {
        System.out.println("模拟检查权限");
    }

    public void log() {
        System.out.println("模拟记录日志");
    }
}
