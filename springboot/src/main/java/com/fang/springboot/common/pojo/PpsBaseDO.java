package com.fang.springboot.common.pojo;

import com.fang.springboot.common.interfact.BaseDO;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class PpsBaseDO implements BaseDO {

    protected String id;

    public PpsBaseDO() {
    }

    @ApiModelProperty("id")
    @Id
    @Column(
        name = "id"
    )
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}