package com.fang.backend.Java常用设计模式.备忘录模式.打boss;

/**
 * @author shaobin
 * @date 2022/4/20 10:55
 */
public class Client {
    public static void main(String[] args) {
        // 开始打boss状态
        GameRole gameRole = new GameRole();
        gameRole.setVitality(15000);
        gameRole.setAttack(100);
        gameRole.setDefense(55);
        System.out.println(gameRole);
        // 存档
        RoleStateCaretaker roleStateCaretaker = new RoleStateCaretaker();
        roleStateCaretaker.setRoleStateMemento(gameRole.createRoleStateMemento());
        System.out.println("开打受到很多伤害");
        gameRole.beHurt();
        gameRole.beHurt();
        gameRole.beHurt();
        System.out.println(gameRole);
        System.out.println("打不过了，Game over!");
        // 加载存档
        System.out.println("加载存档");
        gameRole.setRoleStateMemento(roleStateCaretaker.getRoleStateMemento());
        System.out.println(gameRole);

    }
}
