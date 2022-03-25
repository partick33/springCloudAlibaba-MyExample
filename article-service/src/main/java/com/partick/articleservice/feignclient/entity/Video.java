package com.partick.articleservice.feignclient.entity;

import lombok.Data;

/**
 * @author partick_peng
 */
@Data
public class Video {
    private Long videoId;
    private String sn;
    private Float videoLength;
    private String cover;
    private String videoUrl;
}
