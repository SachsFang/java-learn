package com.fang.后端.接口的学习;

/**
 * Created by SachsFang on 2021/5/8 20:38
 */
public class defaultInterface {
    public interface Car {
        default void say() {
            System.out.println("I am a car");
        }
        static void speak() {
            System.out.println("speak something");
        }
    }

    public interface Bus {
        default void say() {
            System.out.println("I am a bus");
        }
    }


    public static void main(String[] args) {
        MyCar myCar = new MyCar();
        myCar.say();
    }

    class b implements Comparable {

        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }
}
