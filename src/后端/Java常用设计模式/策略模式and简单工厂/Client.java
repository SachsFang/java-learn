package 后端.Java常用设计模式.策略模式and简单工厂;

import java.math.BigDecimal;

/**
 * @author shaobin
 * @date 2022/3/14 12:39
 * 打折活动客户端
 */
public class Client {
    public static void main(String[] args) {
        ChargeContext chargeContextReturn = new ChargeContext("return");
        System.out.println(chargeContextReturn.getResult(new BigDecimal(600)));

        ChargeContext chargeContextRebate = new ChargeContext("rebate");
        System.out.println(chargeContextRebate.getResult(new BigDecimal(600)));

        ChargeContext chargeContextNormal = new ChargeContext("normal");
        System.out.println(chargeContextNormal.getResult(new BigDecimal(600)));

    }
}
