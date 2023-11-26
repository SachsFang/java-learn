package com.fang.springboot.common.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
public class PpsBasicDO extends PpsBaseDO {
    private String creatorId;
    private Date createTime;
    private String updaterId;
    private Date updateTime;

    public PpsBasicDO() {
    }

    @CreatedBy
    @ApiModelProperty("创建者")
    @Column(
            name = "creator_id",
            updatable = false
    )
    @JsonIgnore
    public String getCreatorId() {
        return this.creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    @ApiModelProperty("创建时间")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(
            name = "create_time",
            updatable = false
    )
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @LastModifiedBy
    @ApiModelProperty("最近一次修改人")
    @Column(
            name = "updater_id"
    )
    @JsonIgnore
    public String getUpdaterId() {
        return this.updaterId;
    }

    public void setUpdaterId(String updaterId) {
        this.updaterId = updaterId;
    }

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty("最后一次修改时间")
    @Column(
            name = "update_time"
    )
    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}