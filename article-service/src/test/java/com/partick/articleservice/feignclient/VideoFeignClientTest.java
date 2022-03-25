package com.partick.articleservice.feignclient;

import com.partick.articleservice.dto.ResponseObject;
import com.partick.articleservice.feignclient.entity.Video;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VideoFeignClientTest {
    @Autowired
    private VideoFeignClient videoFeignClient;

    @Test
    public void testFindArticleId() {
        ResponseObject<Video> video = videoFeignClient.findByArticleId(1649l);
        System.out.println(video);
    }

    @Test
    public void testFindArticleIds() {
        for (int i = 0; i < 10; i++) {
            videoFeignClient.findByArticleId(1649l);

        }
    }
}