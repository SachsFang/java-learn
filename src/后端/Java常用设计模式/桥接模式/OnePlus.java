package 后端.Java常用设计模式.桥接模式;

import java.util.List;

/**
 * @author shaobin
 * @date 2022/5/13 18:20
 */
public class OnePlus extends PhoneBrand {

    public OnePlus(List<PhoneSoftWare> phoneSoftWares) {
        super(phoneSoftWares);
    }
}
