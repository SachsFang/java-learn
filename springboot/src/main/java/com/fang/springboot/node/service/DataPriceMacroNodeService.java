package com.fang.springboot.node.service;

import com.fang.springboot.common.interfact.FacadeService;
import com.fang.springboot.node.pojo.DataPriceMacroNodeDO;

import java.util.Date;
import java.util.List;

  /**
 * 交易结果-市场节点电价服务
 * @author shaobin
 * @date 2023/11/24
 */
public interface DataPriceMacroNodeService extends FacadeService {
    
    /**
     * 查询交易结果-市场节点电价数据
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 交易结果-市场节点电价结果集
     */
    List<DataPriceMacroNodeDO> query(Date startDate, Date endDate);
    
    /**
     * 查询交易结果-市场节点电价数据
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param orgId 厂站ID
     * @return 交易结果-市场节点电价结果集
     */
    List<DataPriceMacroNodeDO> query(Date startDate, Date endDate, String orgId);
    
    /**
     * 查询交易结果-市场节点电价数据
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param orgIds 厂站ID列表
     * @return 交易结果-市场节点电价结果集
     */
    List<DataPriceMacroNodeDO> query(Date startDate, Date endDate, List<String> orgIds);
    
    /**
     * 保存或更新交易结果-市场节点电价数据
     * @param dataPriceMacroNodeDOList 交易结果-市场节点电价数据列表
     * @return
     */
    List<DataPriceMacroNodeDO> saveOrUpdateBatch(List<DataPriceMacroNodeDO> dataPriceMacroNodeDOList);
    
    /**
     * 根据ID列表删除数据
     * @param ids ID列表
     * @return
     */
    boolean deleteByIds(List<String> ids);
}