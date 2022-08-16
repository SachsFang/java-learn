package 后端.lambdaAND函数式接口;

/**
 * @author shaobin
 * @date 2022/8/16 11:08
 */
public class UserEntity {
    private String userName;
    private int age;

    public UserEntity(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    public UserEntity() {
    }
    public String getMess() {
        return this.userName;
    }
    public static String staticGetMess() {
        return "static mess";
    }
}
