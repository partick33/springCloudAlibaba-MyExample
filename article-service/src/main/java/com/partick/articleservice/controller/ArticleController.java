package com.partick.articleservice.controller;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.partick.articleservice.annotation.JwtToken;
import com.partick.articleservice.db.pojo.User;
import com.partick.articleservice.dto.ResponseObject;
import com.partick.articleservice.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author partick_peng
 */
@RestController
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/list")
    @JwtToken(required = false)
    public ResponseObject list(@RequestAttribute(value = "userInfo",required = false)User user) {
        System.out.println(user);
        return new ResponseObject("0", "success", articleService.list());
    }

    @GetMapping("/rule_setting")
    public String initFlowRule() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule("/list");
        //设置流控模式
        rule.setStrategy(RuleConstant.STRATEGY_DIRECT);
        //设置阈值类型
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //设置阈值数量
        rule.setCount(100);
        //流控效果设置为"预热"
        rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_WARM_UP);
        //预热时间20秒
        rule.setWarmUpPeriodSec(20);
        //来源设置为默认所有default
        rule.setLimitApp("default");
        rules.add(rule);
        //设置,FlowRuleManager是Sentinel规则管理器
        FlowRuleManager.loadRules(rules);
        return "SUCCESS";
    }

    @GetMapping("/dosth")
    @JwtToken //doSth进行拦截
    public ResponseObject doSth() {
        return new ResponseObject("处理成功");
    }
}
