package com.fang.springboot.common.util;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fang.springboot.common.format.DateFormatType;

/**
 * @author shaobin
 * @date 2023/9/8 17:13
 */
public class JsonUtil {
    public static String parseToJson(Object obj){
        return JSON.toJSONStringWithDateFormat(ObjectUtil.defaultIfNull(obj, new Object()), DateFormatType.DATE_FORMAT_STR.getFormatStr(), SerializerFeature.WriteDateUseDateFormat);
    }
}
