package com.fang.springboot.common.functions_module.cache_query.pojo;

import com.fang.springboot.common.pojo.PpsBasicDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * 缓存中间表实体
 *
 * @author shaobin
 * @date 2024/5/7
 */
//@Entity
@Table(name = "imt_cahce")
//@EntityUniqueIndex({"methodPath", "params"})
@ApiModel(value = "缓存中间表")
public class ImtCacheDO extends PpsBasicDO {
    /**
     * 方法路径
     */
    private String methodPath;

    /**
     * 参数
     */
    private String params;

    /**
     * 数据
     */
    private String data;

    /**
     * 创建时间
     */
    private Date createTime;

    public ImtCacheDO() {
    }

    public ImtCacheDO(String methodPath, String params, String data) {
        this.methodPath = methodPath;
        this.params = params;
        this.data = data;
    }

    public ImtCacheDO(String methodPath, String params, String data, Date createTime) {
        this.methodPath = methodPath;
        this.params = params;
        this.data = data;
        this.createTime = createTime;
    }

    @ApiModelProperty(name = "方法路径")
    @Column(name = "method_path")
    public String getMethodPath(){
        return this.methodPath;
    }

    public void setMethodPath(String methodPath) {
        this.methodPath = methodPath;
    }

    @ApiModelProperty(name = "参数")
    @Column(name = "params")
    public String getParams(){
        return this.params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @ApiModelProperty(name = "数据")
    @Column(name = "data")
    public String getData(){
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @ApiModelProperty(name = "创建时间")
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}