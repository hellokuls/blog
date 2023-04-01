package com.kuls.controller.front;

import com.kuls.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/29 09:53
 */
@Controller
public class BlogLikeController {

    @Autowired
    private RedisService redisService;

    @GetMapping("/clickLike/{id}")
    @ResponseBody
    public String clickLike(@PathVariable long id) {

        redisService.incrementLikedCount(id);
        String result = "success";

        return result;
    }

    @GetMapping("/clickUnLike/{id}")
    @ResponseBody
    public String clickUnLike(@PathVariable long id) {

        redisService.decrementLikedCount(id);
        String result = "success";

        return result;
    }


}
