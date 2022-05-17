package 后端.Java常用设计模式.桥接模式;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 手机品牌抽象类
 * @author shaobin
 * @date 2022/5/13 17:35
 */
public abstract class PhoneBrand {

    private List<PhoneSoftWare> phoneSoftWares = new ArrayList<>();

    public PhoneBrand(List<PhoneSoftWare> phoneSoftWares) {
        if (phoneSoftWares != null) {
            this.phoneSoftWares = phoneSoftWares;
        }
    }

    public void showSoftware() {
        this.phoneSoftWares.forEach(PhoneSoftWare::run);
    }

//    public void install(PhoneSoftWare phoneSoftWare) {
//        if (Objects.nonNull(phoneSoftWare)) {
//            phoneSoftWares.add(phoneSoftWare);
//        }
//    }
//
//    public void uninstall(PhoneSoftWare phoneSoftWare) {
//        if (Objects.nonNull(phoneSoftWare)) {
//            phoneSoftWares.remove(phoneSoftWare);
//        }
//    }
}
