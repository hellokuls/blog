package com.kuls;

import com.kuls.po.Blog;
import com.kuls.po.Comment;
import com.kuls.service.CommentService;
import com.kuls.service.admin.BlogService;
import com.kuls.service.admin.TagService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;

@SpringBootTest
class SpringblogApplicationTests {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RedisTemplate<String, Object> template;

    @Test
    void contextLoads() {
        ValueOperations<String, Object> redisString = template.opsForValue();
        redisString.set("kuls", "hello,kuls");
//        template.opsForHash().put();

        String kuls = (String) redisString.get("kuls");
        System.out.println(kuls);

    }


}
