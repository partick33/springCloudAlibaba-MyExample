package com.partick.articleservice.db.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.partick.articleservice.feignclient.entity.Video;
import lombok.Data;

import java.util.Date;

/**
 * @author partick_peng
 */
@Data
public class Article {
    private Long articleId;
    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private Video video;
}
