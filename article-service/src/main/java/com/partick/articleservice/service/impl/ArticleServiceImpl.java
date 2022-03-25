package com.partick.articleservice.service.impl;

import com.partick.articleservice.db.pojo.Article;
import com.partick.articleservice.dto.ResponseObject;
import com.partick.articleservice.feignclient.VideoFeignClient;
import com.partick.articleservice.feignclient.entity.Video;
import com.partick.articleservice.mapper.ArticleMapper;
import com.partick.articleservice.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * @author partick_peng
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private VideoFeignClient videoFeignClient;

    /**
     * 文章列表
     *
     * @return
     */
    @Override
    public List<Article> list() {

        List<Article> list = articleMapper.list();

        list.forEach(article -> {
            ResponseObject<Video> videoResponseObject = videoFeignClient.findByArticleId(article.getArticleId());
            article.setVideo(videoResponseObject.getData());
        });

        return list;
    }
}
