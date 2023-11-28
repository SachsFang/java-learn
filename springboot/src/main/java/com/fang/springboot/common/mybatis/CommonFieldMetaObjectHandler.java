package com.fang.springboot.common.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * mybaits插入更新自动填充数据处理器
 * 参考：<a href="https://zhuanlan.zhihu.com/p/433228145?utm_id=0">...</a>
 */
@Component
public class CommonFieldMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
//        metaObject.setValue("createTime", new Date());
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "creatorId", String.class, "sachs");
        this.strictInsertFill(metaObject, "updaterId", String.class, "sachs");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "updaterId", String.class, "sachs");
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }
}