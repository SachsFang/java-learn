package 后端.Java常用设计模式.责任链模式.请假;

/**
 * @author shaobin
 * @date 2022/6/9 18:19
 */
public class TechnicalManager extends Manager{
    @Override
    public void doRequest(Request request) {
        if (request.getReqType() == 1 && request.getReqAmount() <= 10) {
            System.out.println("技术经理批准" + request.getName() + "请假" + request.getReqAmount() + "天");
        } else {
            this.getSuperior().doRequest(request);
        }
    }
}
