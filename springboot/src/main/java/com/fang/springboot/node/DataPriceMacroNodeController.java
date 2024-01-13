package com.fang.springboot.node;

import com.fang.springboot.common.BaseController;
import com.fang.springboot.common.builder.BaseRespBuilder;
import com.fang.springboot.common.pojo.BaseResp;
import com.fang.springboot.common.util.MultiThreadCalcUtil;
import com.fang.springboot.node.pojo.DataPriceMacroNodeDO;
import com.fang.springboot.node.service.DataPriceMacroNodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

 /**
 * 交易结果-市场节点电价控制层
 * @author shaobin
 * @date 2023/11/27
 */
@Api(tags = "交易结果-市场节点电价功能接口")
@RestController
@RequestMapping("/node/price")
public class DataPriceMacroNodeController extends BaseController {
    @Autowired
    private DataPriceMacroNodeService dataPriceMacroNodeService;
    
    @ApiOperation("查询交易结果-市场节点电价数据")
    @GetMapping("/query")
    public BaseResp<List<DataPriceMacroNodeDO>> queryDataPriceMacroNodeList(@RequestParam Date startDate,
                                                                            @RequestParam Date endDate,
                                                                            @RequestParam List<String> nodeIds,
                                                                            @RequestParam List<String> dataTypes){
        return BaseRespBuilder.success().setData(
            dataPriceMacroNodeService.query(startDate, endDate, nodeIds, dataTypes)
        ).build();
    }

     @ApiOperation("查询交易结果-市场节点电价数据")
     @GetMapping("/queryMulti")
     public BaseResp<List<DataPriceMacroNodeDO>> queryDataPriceMacroNodeListByMulti(@RequestParam Date startDate,
                                                                             @RequestParam Date endDate,
                                                                             @RequestParam List<String> nodeIds,
                                                                             @RequestParam List<String> dataTypes){
         return BaseRespBuilder.success().setData(
                 MultiThreadCalcUtil.asyncShardingByDate(startDate, endDate, (itemStartDate, itemEndDate) ->
                         dataPriceMacroNodeService.query(itemStartDate, itemEndDate, nodeIds, dataTypes))
         ).build();
     }

    @ApiOperation("批量新增保存交易结果-市场节点电价数据")
    @PostMapping("/saveOrUpdateBatch")
    public BaseResp<List<DataPriceMacroNodeDO>> saveOrUpdateBatchDataPriceMacroNodeList(@RequestBody List<DataPriceMacroNodeDO> dataPriceMacroNodeDOList){
        return BaseRespBuilder.success().setData(
            dataPriceMacroNodeService.saveOrUpdateBatch(dataPriceMacroNodeDOList)
        ).build();
    }
    
    @ApiOperation("删除交易结果-市场节点电价数据")
    @DeleteMapping("/deleteBatch")
    public BaseResp<Void> deleteByIds(@RequestParam List<String> ids){
        dataPriceMacroNodeService.deleteByIds(ids);
        return BaseRespBuilder.success().build();
    }
}