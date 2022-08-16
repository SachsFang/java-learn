package 后端.lambdaAND函数式接口;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author shaobin
 * @date 2022/8/13 15:35
 */
public class ListToMapExample {
    public static void main(String[] args) {
        List<UserDO> userDOList = new ArrayList<>();
        userDOList.add(new UserDO("fang", 12));
        userDOList.add(new UserDO("sachs", 22));
        userDOList.add(new UserDO("ting", 28));
        userDOList.add(new UserDO("bing", 29));
        userDOList.add(new UserDO("ring", 21));
        // List 转 Map
        Map<String, UserDO> nativeWritingUserMap = userDOList.stream().collect(Collectors.toMap(
                new Function<UserDO, String>() {//第一个参数为声明List的对象，第二个为转换Map声明的key类型
                    @Override
                    public String apply(UserDO userDO) {
                        return userDO.getName();//返回转换Map的key值
                    }
                }, new Function<UserDO, UserDO>() {//第一个参数为声明List的对象，第二个为转换Map声明的value类型
                    @Override
                    public UserDO apply(UserDO userDO) {
                        return userDO;//返回转换Map的value值
                    }
                })
        );
        nativeWritingUserMap.forEach(new BiConsumer<String, UserDO>() {
            @Override
            public void accept(String key, UserDO userDO) {
                System.out.println(key + " " + userDO.toString());
            }
        });
        Map<String, UserDO> lambdaWritingUserMap = userDOList.stream(). collect(Collectors.toMap(
                userDO -> userDO.getName(),
                userDO -> userDO
        ));
        lambdaWritingUserMap.forEach((key, userDO) -> System.out.println(key + " " + userDO.toString()));
        // max
        UserDO userDO = userDOList.stream().max(new Comparator<UserDO>() {
            @Override
            public int compare(UserDO o1, UserDO o2) {
                return o1.getAge() - o2.getAge();
            }
        }).get();
        System.out.println(userDO);
        // any match
        boolean anyMatch = userDOList.stream().anyMatch(new Predicate<UserDO>() {
            @Override
            public boolean test(UserDO userDO) {
                return "fang".equals(userDO.getName());
            }
        });
        System.out.println(anyMatch);
        // skip limit
        List<UserDO> skipLimitList = userDOList.stream().skip(1).limit(2).collect(Collectors.toList());
        skipLimitList.forEach(item -> System.out.println(item));
        //
    }

    private static class UserDO {
        private String name;
        private int age;

        public UserDO(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "UserDO{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
