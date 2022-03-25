package com.partick.articleservice.service;

import com.partick.articleservice.db.pojo.Article;

import java.util.List;

/**
 * @author partick_peng
 */
public interface ArticleService {

    /**
     * 文章列表
     * @return
     */
    List<Article> list();
}
