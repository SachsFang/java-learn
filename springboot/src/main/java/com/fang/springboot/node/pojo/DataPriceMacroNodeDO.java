package com.fang.springboot.node.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fang.springboot.common.pojo.PpsBasicDO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

/**
 * 交易结果-市场节点电价实体
 *
 * @author shaobin
 * @date 2023/11/24
 */
@Data
@TableName(value = "data_price_macro_node")
@ApiModel(value = "交易结果-市场节点电价")
public class DataPriceMacroNodeDO extends PpsBasicDO {

    /**
     * 节点ID
     */
    private String acNodeId;

    /**
     * 日期
     */
    private Date date;

    /**
     * 节点电价
     */
    private String nodePrice;

    /**
     * 结果类型
     */
    private String dataType;

    /**
     * 第一个数据点的时间
     */
    private Date tStartTime;

    /**
     * 数据点个数
     */
    private Integer tSize;

    /**
     * 时间间隔
     */
    private Integer tDelta;

    /**
     * 时间间隔单位 0：秒，1：分钟，2：小时，3：天，4：月，5：年
     */
    private Integer tDeltaUnit;

}