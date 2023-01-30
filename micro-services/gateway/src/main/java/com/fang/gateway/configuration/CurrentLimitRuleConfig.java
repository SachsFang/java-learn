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
        List<String> resourceList = new ArrayList<>();
        resourceList.add("getUser");
        resourceList.add("insertUser");
        initCurrentLimitRuleConfig(resourceList, 1);
    }

    public void initCurrentLimitRuleConfig(List<String> resources, int qbs) {
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
        log.info("初始化配置限流策略成功！");
    }
}
