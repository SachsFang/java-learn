package com.fang.springboot.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fang.springboot.common.interfact.BaseDO;
import io.swagger.annotations.ApiModelProperty;

public class PpsBaseDO implements BaseDO {

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    protected String id;

    public PpsBaseDO() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}