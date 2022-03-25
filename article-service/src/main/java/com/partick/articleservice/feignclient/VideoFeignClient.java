package com.partick.articleservice.feignclient;

import com.partick.articleservice.dto.ResponseObject;
import com.partick.articleservice.feignclient.entity.Video;
import com.partick.articleservice.feignclient.fallback.VideoFeignClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 视频服务OpenFeign客户端
 * @author partick_peng
 */
@FeignClient(value = "video-service",fallbackFactory = VideoFeignClientFallbackFactory.class)
public interface VideoFeignClient {

    /**
     * 视频服务：按文章编号查询视频对象
     * @param articleId
     * @return
     */
    @GetMapping("/video")
    public ResponseObject<Video> findByArticleId(@RequestParam("articleId") Long articleId);
}
