package com.fang.后端.内存泄漏溢出;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author shaobin
 * @date 2023/2/22 14:22
 */
public class User implements Serializable {

    private String name;

    private int sex;

    private int age;

    public User() {
    }

    public User(String name, int sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof User)) {
            return false;
        } else {
            User other = (User)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label31: {
                    Object this$name = this.getName();
                    Object other$name = other.getName();
                    if (this$name == null) {
                        if (other$name == null) {
                            break label31;
                        }
                    } else if (this$name.equals(other$name)) {
                        break label31;
                    }

                    return false;
                }

                if (this.getSex() != other.getSex()) {
                    return false;
                } else {
                    return this.getAge() == other.getAge();
                }
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof User;
    }

//    public int hashCode() {
//        int result = 1;
//        Object $name = this.getName();
//        result = result * 59 + ($name == null ? 43 : $name.hashCode());
//        result = result * 59 + this.getSex();
//        result = result * 59 + this.getAge();
//        return result;
//    }
}
