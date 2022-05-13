package 后端.实践测试.List的引用传递测试;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shaobin
 * @date 2022/4/29 15:31
 */
public class Obj {

    private List<BigDecimal> objList;

    Obj() {
        objList = new ArrayList<>();
        objList.add(new BigDecimal("1"));
    }

    public List<BigDecimal> getObjList() {
        return objList;
    }

    public void setObjList(List<BigDecimal> objList) {
        this.objList = objList;
    }
}
