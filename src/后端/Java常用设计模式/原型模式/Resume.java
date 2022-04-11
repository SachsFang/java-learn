package 后端.Java常用设计模式.原型模式;

import java.io.*;
import java.util.Date;

/**
 * 简历类
 * @author shaobin
 * @date 2022/4/9 11:52
 */

public class Resume implements Cloneable, Serializable {

    private String name;
    private String sex;
    private Date departureTime;
    private Enclosure enclosure;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Enclosure getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", departureTime=" + departureTime +
                ", enclosure=" + enclosure +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        // 调用Object默认复制方法，在内存二进制流复制，性能好，但只是浅复制
        return super.clone();
    }

    /**
     * 深克隆
     * 使用序列化技术实现
     */
    public Resume deepClone() throws IOException, ClassNotFoundException {
        /* 将对象写进流中 */
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(this);
        /* 将对象从流中取出 */
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return (Resume) objectInputStream.readObject();
    }
}
