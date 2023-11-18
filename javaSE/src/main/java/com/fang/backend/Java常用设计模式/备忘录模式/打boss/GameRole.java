package com.fang.backend.Java常用设计模式.备忘录模式.打boss;

/**
 * 游戏角色类
 * @author shaobin
 * @date 2022/4/20 10:42
 */
public class GameRole {

    private int vitality;

    private int attack;

    private int defense;

    private RoleStateMemento roleStateMemento;

    public void beHurt() {
        this.vitality -= 5000;
        this.attack -= 2;
        this.defense -= 1;
    }

    public RoleStateMemento createRoleStateMemento() {
        return new RoleStateMemento(this.vitality, this.attack, this.defense);
    }

    public void setRoleStateMemento(RoleStateMemento roleStateMemento) {
        this.vitality = roleStateMemento.getVitality();
        this.attack =  roleStateMemento.getAttack();
        this.defense = roleStateMemento.getDefense();
    }

    public int getVitality() {
        return vitality;
    }

    public void setVitality(int vitality) {
        this.vitality = vitality;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public String toString() {
        return "GameRole{" +
                "vitality=" + vitality +
                ", attack=" + attack +
                ", defense=" + defense +
                ", roleStateMemento=" + roleStateMemento +
                '}';
    }
}
