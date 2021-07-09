package 后端.输入输出流;

import java.io.*;

/**
 * Created by SachsFang on 2021/7/9 18:00
 * 对象流
 */
public class ObjectStream {

    public static void main(String[] args) {
        File file = new File("D:\\ideaProject\\my-project\\java-learn\\src\\后端\\输入输出流", "object.txt");
        writeObj(file);
        readObj(file);
    }

    public static void readObj(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream reader = new ObjectInputStream(fileInputStream);
            Car car = (Car) reader.readObject();
            System.out.println(car.getName() + car.getPrice());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void writeObj(File file) {
        try {
            Car car = new Car();
            car.setName("宝马X1");
            car.setPrice(3000000);

            FileOutputStream inputStream = new FileOutputStream(file);
            ObjectOutputStream writer = new ObjectOutputStream(inputStream);
            writer.writeObject(car);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Car implements Serializable { //实现Serializable接口时，其实是在告诉JVM此类可被序列化，可被默认的序列化机制序列化。序列化是将对象状态转换为可保持或传输的格式的过程。
        String name;
        int price;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
