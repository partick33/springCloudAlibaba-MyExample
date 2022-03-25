package com.partick.articleservice.feignclient.fallback;

import com.partick.articleservice.dto.ResponseObject;
import com.partick.articleservice.feignclient.VideoFeignClient;
import com.partick.articleservice.feignclient.entity.Video;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author partick_peng
 * OpenFeign Fallback Factory书写规则
 * 1. 实现FallbackFactory接口,且泛型为对应的FeignClient
 * 2. 增加@Component让Spring对其实例化并IOC管理
 * 3. 在create方法中实现FeignClient,说明限流/熔断时的操作
 */
@Component
@Slf4j
public class VideoFeignClientFallbackFactory implements FallbackFactory<VideoFeignClient> {
    @Override
    public VideoFeignClient create(Throwable throwable) {
        return new VideoFeignClient() {
            /**
             * 视频服务：按文章编号查询视频对象
             *
             * @param articleId
             * @return
             */
            @Override
            public ResponseObject<Video> findByArticleId(Long articleId) {
                log.error("触发异常：" + throwable.getClass().getSimpleName());
                /*
                可根据throwable判断是限流还是熔断
                if(throwable instanceof FlowException){//限流异常
                    msg = "接口已被限流";
                }else if(throwable instanceof DegradeException){//熔断异常
                    msg = "接口已被熔断,请稍后再试";
                }else if(throwable instanceof ParamFlowException){ //热点参数限流
                    msg = "热点参数限流";
                }else if(throwable instanceof SystemBlockException){ //系统规则异常
                    msg = "系统规则(负载/....不满足要求)";
                }else if(throwable instanceof AuthorityException){ //授权规则异常
                    msg = "授权规则不通过";
                }
                 */
                throwable.printStackTrace();
                return new ResponseObject<>(throwable.getClass().getSimpleName(), "限流或熔断");
            }
        };
    }
}
