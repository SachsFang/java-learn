package 后端.Java常用设计模式.访问者模式.男女对比;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shaobin
 * @date 2022/7/1 11:39
 */
public class PersonCompareStruct {

    private List<Person> personList = new ArrayList<>();

    public void attach(Person person) {
        personList.add(person);
    }

    public void detach(Person person) {
        personList.remove(person);
    }

    public void accept(Status status) {
        personList.forEach(person -> {
            person.accept(status);
        });
    }
}
