package com.partick.articleservice.controller.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.partick.articleservice.dto.ResponseObject;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 重写sentinel异常处理
 * @author partick_peng
 */
@Component
public class UrlBlockHandler implements BlockExceptionHandler {

    /**
     * 流控与异常处理控制器
     * @param httpServletRequest
     * @param httpServletResponse
     * @param e
     * @throws Exception
     */
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        String message = null;
        if (e instanceof FlowException) {
            //限流异常
            message = "接口已被限流";
        } else if (e instanceof DegradeException) {
            //熔断异常
            message = "接口已被熔断";
        } else if(e instanceof ParamFlowException){
            //热点参数限流
            //例如:id参数=5不开启限流,id=10开启限流,针对不同的参数进行不同的限流策略
            message = "热点参数限流";
        }else if(e instanceof SystemBlockException){
            //系统规则异常
            //例如:ＣＰＵ负载超过８０％则不允许访问
            message = "系统规则(负载/....不满足要求)";
        }else if(e instanceof AuthorityException){
            //授权规则异常
            //例如:服务A不允许服务B进行访问,服务B当发起调用后就会触发授权异常
            message = "授权规则不通过";
        }
        httpServletResponse.setStatus(500);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        //ObjectMapper是内置Jackson的序列化工具类,这用于将对象转为JSON字符串
        ObjectMapper mapper = new ObjectMapper();
        //某个对象属性为null时不进行序列化输出
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.writeValue(httpServletResponse.getWriter(),
                new ResponseObject(e.getClass().getSimpleName(),message));
    }
}
