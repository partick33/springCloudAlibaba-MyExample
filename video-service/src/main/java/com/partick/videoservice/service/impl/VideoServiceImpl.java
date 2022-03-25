package com.partick.videoservice.service.impl;

import com.partick.videoservice.db.pojo.Video;
import com.partick.videoservice.mapper.VideoMapper;
import com.partick.videoservice.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author partick_peng
 */
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    /**
     * 按文章编号查询视频对象
     *
     * @param articleId
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
    public Video findByArticleId(Long articleId) {
        return videoMapper.findByArticleId(articleId);
    }
}
