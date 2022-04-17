package 后端.Java常用设计模式.抽象工厂模式.database_example;

/**
 * @author shaobin
 * @date 2022/4/17 14:34
 */
public class MySqlMenuDaoImpl implements MenuDao {
    @Override
    public void updateMenu() {
        System.out.println("mysql update menu");
    }

    @Override
    public void getMenu() {
        System.out.println("mysql get menu");
    }
}
