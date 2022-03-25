package com.partick.videoservice.service;

import com.partick.videoservice.db.pojo.Video;

/**
 * @author partick_peng
 */
public interface VideoService {

    /**
     * 按文章编号查询视频对象
     * @param articleId
     * @return
     */
    Video findByArticleId(Long articleId);
}
