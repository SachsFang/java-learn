package com.fang.micro.api.order.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author shaobin
 * @date 2022/12/25 18:56
 */
@Data
public class Order implements Serializable {

    @JsonProperty("code") // feign在数据传输时需要序列化，如果不加上此注解，反序列化后的数据就为空值
    private String code;

    @JsonProperty("productId")
    private String productId;

    @JsonProperty("amount")
    private int amount;

    @JsonProperty("remark")
    private String remark;
}
