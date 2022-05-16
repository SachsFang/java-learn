package 后端.Java常用设计模式.桥接模式;

/**
 * 手机品牌抽象类
 * @author shaobin
 * @date 2022/5/13 17:35
 */
public abstract class PhoneBrand {

    PhoneBrand(PhoneSoftWare phoneSoftWare) {
        phoneSoftWare.run();
    }


}
