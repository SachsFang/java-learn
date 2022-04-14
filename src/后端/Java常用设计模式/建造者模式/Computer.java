package 后端.Java常用设计模式.建造者模式;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Computer
 * @description computer is a product
 * @author shaobin
 * @date 2022/4/14 17:48
 */
public class Computer {

    List<String> parts;

    Computer() {
        parts = new ArrayList();
    }

    public void addPart(String part) {
        parts.add(part);
    }

    public String getInfo() {
        return parts.toString();
    }

}
