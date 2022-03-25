package com.partick.videoservice.mapper;

import com.partick.videoservice.db.pojo.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author partick_peng
 */
@Mapper
public interface VideoMapper {
    /**
     * 按文章编号查询视频对象
     * @param articleId
     * @return
     */
    @Select("select * from video where article_id = #{value}")
    public Video findByArticleId(Long articleId);
}
