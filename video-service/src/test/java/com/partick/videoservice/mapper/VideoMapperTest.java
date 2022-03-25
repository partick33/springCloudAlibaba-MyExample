package com.partick.videoservice.mapper;

import com.partick.videoservice.db.pojo.Video;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VideoMapperTest {
    @Autowired
    private VideoMapper videoMapper;

    @Test
    public void testFindByArticleId() {
        Video video = videoMapper.findByArticleId(1648l);
        System.out.println(video.getVideoUrl());
    }
}