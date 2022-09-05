package com.fang.spring.user.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.FactoryBean;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author shaobin
 * @date 2022/8/29 16:28
 */
@Data
@ToString
public class User {

    private String id;

    private String name;

    private Integer age;

    private String[] addressArray;

    private List<String> addressList;

    private Set<String> addressSet;

    private Map<Integer, String> addressMap;

    private List<Address> addressObjectList;

    public User() {
        System.out.println(this + "生命周期：执行无参构造方法，实例化对象");
    }

    public User(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
        System.out.println(this + "生命周期：执行有参构造方法，实例化对象");
    }

    public void init() {
        System.out.println(this + "生命周期：初始化");
    }

    public void destroy() {
        System.out.println(this + "生命周期：销毁");
    }

    // get and set methods
    public String getId() {
        return id;
    }

    public void setId(String id) {
        System.out.println(this + "生命周期：属性赋值 - id");
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println(this + "生命周期：属性赋值 - name");
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        System.out.println(this + "生命周期：属性赋值 - age");
        this.age = age;
    }

//    @Override
//    public Object getObject() throws Exception {
//        //此处方法是实现 FactoryBean 的方法，注入IOC容器时可返回不同的类型（本文件时User类型，但可以返回Address类型）
//        return new Address();
//    }
//
//    @Override
//    public Class<?> getObjectType() {
//        //此处方法是实现 FactoryBean 的方法，注入IOC容器时可返回不同的类型（本文件时User类型，但可以返回Address类型）
//        return Address.class;
//    }
}
