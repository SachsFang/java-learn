package com.fang.gateway.configuration;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
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
        initCurrentLimitRuleConfig();
    }

    public void initCurrentLimitRuleConfig() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule ruleOne = new FlowRule();
        ruleOne.setResource("/getUser");
        ruleOne.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Qps控制在1
        ruleOne.setCount(1);
        ruleOne.setLimitApp("default");
        rules.add(ruleOne);
        FlowRuleManager.loadRules(rules);
        log.info("初始化配置限流策略成功！");
    }
}
