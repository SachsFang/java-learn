package com.fang.gateway.configuration;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 限流规则配置
 * @author shaobin
 * @date 2023/1/21 21:01
 */
@Configuration
@Slf4j
public class CurrentLimitRuleConfig {

    public CurrentLimitRuleConfig() {
        List<String> resourceList = new ArrayList<>();
        resourceList.add("getUser");
//        resourceList.add("insertUser");
        initFlowRuleConfig(resourceList, 1);
        // 对热点参数限流
//        initParamFlowRuleConfig(Collections.singletonList("insertUser"), 1);
    }

    public void initFlowRuleConfig(List<String> resources, int qbs) {
        List<FlowRule> rules = new ArrayList<>();
        resources.forEach(resource -> {
            FlowRule rule = new FlowRule();
            rule.setResource(resource);
            rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
            // Qps控制在1
            rule.setCount(qbs);
            rule.setLimitApp("default");
            rules.add(rule);
        });
        FlowRuleManager.loadRules(rules);
        log.info("初始化配置限流规则成功！");
    }

    public void initParamFlowRuleConfig(List<String> resources, int qbs) {
        List<ParamFlowRule> rules = new ArrayList<>();
        resources.forEach(resource -> {
            ParamFlowRule rule = new ParamFlowRule();
            rule.setResource(resource);
            rule.setParamIdx(0);
            rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
            // Qps控制在1
            rule.setCount(qbs);
            rule.setLimitApp("default");
            rules.add(rule);
        });
        ParamFlowRuleManager.loadRules(rules);
        log.info("初始化配置限流热点参数规则成功！");
    }
}
