package com.fang.gateway.configuration;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
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
 *
 * @author shaobin
 * @date 2023/1/21 21:01
 */
@Configuration
@Slf4j
public class CurrentLimitRuleConfig {

    public CurrentLimitRuleConfig() {
        // 这里只是示例，通常不会硬编码在代码中，生产环境通常我们更倾向于使用控制台进行动态配置
        // 配置流控规则
        List<String> resourceList = new ArrayList<>();
        resourceList.add("getUser");
//        resourceList.add("insertUser");
        initFlowRuleConfig(resourceList, 1);
        // 对热点参数限流
//        initParamFlowRuleConfig(Collections.singletonList("insertUser"), 1);
        // 配置降级规则
        initDeGradeRuleConfig(Collections.singletonList("degrade"));
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

    public void initDeGradeRuleConfig(List<String> resources) {
        List<DegradeRule> degradeRuleList = new ArrayList<>();
        resources.forEach(resource -> {
            // 配置熔断降级规则（同样只是示例）
            DegradeRule degradeRule = new DegradeRule(); // 创建一个降级规则对象
            // 设置需要进行降级保护的资源名
            degradeRule.setResource(resource);
            // 设置降级的阈值类型，这里是基于异常次数的降级
            // 注意：如果你想要基于异常比例的降级，应该使用 RuleConstant.DEGRADE_GRADE_EXCEPTION_RATIO，如果是慢调用比例使用RuleConstant.DEGRADE_GRADE_RT
            degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
            // 设置熔断时长，统计时长不能在配置汇总设置，默认为1s，这个参数可在Sentinel控制台设置
            degradeRule.setTimeWindow(5);
            // 设置触发阈值
            degradeRule.setCount(2);
            degradeRule.setMinRequestAmount(2);
            // 所以以上的配置意思是在1s内如果失败次数达2次，则熔断，熔断时间为5s
            degradeRuleList.add(degradeRule);
        });
        // 加载降级规则到 Sentinel 的运行时环境中
        DegradeRuleManager.loadRules(degradeRuleList);
    }
}
