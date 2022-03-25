package com.partick.articleservice.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleMapperTest {

    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void testList() {
        articleMapper.list().forEach(i -> {
            System.out.println(i.getTitle());
        });
    }

}