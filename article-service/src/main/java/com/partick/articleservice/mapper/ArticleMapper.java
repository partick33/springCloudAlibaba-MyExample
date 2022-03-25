package com.partick.articleservice.mapper;

import com.partick.articleservice.db.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author partick_peng
 */
@Mapper
public interface ArticleMapper {

    /**
     * 查询文章列表
     * @return
     */
    @Select("select * from article order by create_time desc")
    public List<Article> list();
}
