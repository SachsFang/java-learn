package com.fang.springboot.node.serviceImpl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fang.springboot.node.dao.DataPriceMacroNodeDAO;
import com.fang.springboot.node.pojo.DataPriceMacroNodeDO;
import com.fang.springboot.node.service.DataPriceMacroNodeService;
import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 交易结果-市场节点电价服务实现
 * @author shaobin
 * @date 2023/11/24
 */
@Service
public class DataPriceMacroNodeServiceImpl implements DataPriceMacroNodeService {

    @Autowired
    private DataPriceMacroNodeDAO dataPriceMacroNodeDAO;
    
    @Override
    public List<DataPriceMacroNodeDO> query(Date startDate, Date endDate){
        return query(startDate, endDate, (String) null);
    }

    @Override
    public List<DataPriceMacroNodeDO> query(Date startDate, Date endDate, String orgId) {
        return query(startDate, endDate, Collections.singletonList(orgId));
    }

    @Override
    public List<DataPriceMacroNodeDO> query(Date startDate, Date endDate, List<String> orgIds){

        return dataPriceMacroNodeDAO.selectList(Wrappers.<DataPriceMacroNodeDO>lambdaQuery()
                .ge(Objects.nonNull(startDate), DataPriceMacroNodeDO::getDate, startDate)
                .le(Objects.nonNull(endDate), DataPriceMacroNodeDO::getDate, endDate)
            );
    }
    
    @Override
    public List<DataPriceMacroNodeDO> saveOrUpdateBatch(List<DataPriceMacroNodeDO> dataPriceMacroNodeDOList){
        dataPriceMacroNodeDOList.forEach(item -> {
            if (Objects.nonNull(item.getId())) {
                dataPriceMacroNodeDAO.updateById(item);
            } else {
                dataPriceMacroNodeDAO.insert(item);
            }
        });
        return dataPriceMacroNodeDOList;
    }
    
    @Override
    public boolean deleteByIds(List<String> ids){
        if (CollectionUtils.isEmpty(ids)) {
            return true;
        }
        dataPriceMacroNodeDAO.deleteBatchIds(ids.stream().map(id -> {
            DataPriceMacroNodeDO dataPriceMacroNodeDO = new DataPriceMacroNodeDO();
            dataPriceMacroNodeDO.setId(id);
            return dataPriceMacroNodeDO;
        }).collect(Collectors.toList()));
        return true;
    }
}