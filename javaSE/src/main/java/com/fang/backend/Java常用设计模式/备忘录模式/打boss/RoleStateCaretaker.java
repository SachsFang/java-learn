package com.fang.backend.Java常用设计模式.备忘录模式.打boss;

/**
 * @author shaobin
 * @date 2022/4/20 10:54
 */
public class RoleStateCaretaker {

    private RoleStateMemento roleStateMemento;

    public RoleStateMemento getRoleStateMemento() {
        return roleStateMemento;
    }

    public void setRoleStateMemento(RoleStateMemento roleStateMemento) {
        this.roleStateMemento = roleStateMemento;
    }
}
