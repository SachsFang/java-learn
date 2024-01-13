package com.fang.springboot.node.service;

import com.fang.springboot.common.interfact.FacadeService;
import com.fang.springboot.node.pojo.DataPriceMacroNodeDO;

import java.util.Date;
import java.util.List;

/**
 * 交易结果-市场节点电价服务
 *
 * @author shaobin
 * @date 2023/11/24
 */
public interface DataPriceMacroNodeService extends FacadeService {

    List<DataPriceMacroNodeDO> query(Date startDate, Date endDate, List<String> nodeIds, List<String> dataTypes);

    /**
     * 保存或更新交易结果-市场节点电价数据
     *
     * @param dataPriceMacroNodeDOList 交易结果-市场节点电价数据列表
     * @return
     */
    List<DataPriceMacroNodeDO> saveOrUpdateBatch(List<DataPriceMacroNodeDO> dataPriceMacroNodeDOList);

    /**
     * 根据ID列表删除数据
     *
     * @param ids ID列表
     * @return
     */
    boolean deleteByIds(List<String> ids);
}