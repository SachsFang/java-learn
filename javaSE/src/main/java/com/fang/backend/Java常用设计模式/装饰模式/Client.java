package com.fang.backend.Java常用设计模式.装饰模式;

/**
 * @author shaobin
 * @date 2022/3/21 17:02
 */
public class Client {
    public static void main(String[] args) {
        /** 人被衣服装饰 **/
        // 人
        Person person = new Person();
        person.setName("小方");
        // 衣服
        TShirt tShirt = new TShirt();
        Jeans jeans = new Jeans();
        CasualShoes casualShoes = new CasualShoes();
        LeatherShoes leatherShoes = new LeatherShoes();
        /* 第一种装饰组合 */
        casualShoes.setPerson(person);
        jeans.setPerson(casualShoes);
        tShirt.setPerson(jeans);
        tShirt.show();
        System.out.println();
        /* 第二种装饰组合 */
        leatherShoes.setPerson(person);
        jeans.setPerson(leatherShoes);
        tShirt.setPerson(jeans);
        tShirt.show();
    }
}
