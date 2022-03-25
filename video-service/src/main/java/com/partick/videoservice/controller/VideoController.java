package com.partick.videoservice.controller;

import com.partick.videoservice.db.pojo.Video;
import com.partick.videoservice.dto.ResponseObject;
import com.partick.videoservice.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author partick_peng
 */
@RestController
public class VideoController {
    @Autowired
    private VideoService videoService;

    @GetMapping("/video")
    public ResponseObject<Video> findByArticleId(@RequestParam Long articleId) {
        return new ResponseObject<Video>(videoService.findByArticleId(articleId));
    }
}
