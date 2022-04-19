package 后端.Java常用设计模式.状态模式;

/**
 * 上午状态
 * @author shaobin
 * @date 2022/4/18 17:20
 */
public class ForenoonState implements State {
    @Override
    public void writeProgram(WorkContext workContext) {
        if (workContext.getHour() < 12) {
            System.out.println("现在是上午上班状态，精神百倍！");
        } else {
            workContext.setState(new NoonState());
            workContext.writeProgram();
        }
    }
}
