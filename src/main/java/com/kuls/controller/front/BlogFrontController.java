package com.kuls.controller.front;

import com.kuls.exception.BlogNotFoundException;
import com.kuls.mapper.BlogTagMapper;
import com.kuls.po.Blog;
import com.kuls.service.CommentService;
import com.kuls.service.admin.BlogService;
import com.kuls.service.admin.LinkService;
import com.kuls.service.admin.TagService;
import com.kuls.service.admin.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/30 09:20
 */

@Controller
public class BlogFrontController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogTagMapper blogTagMapper;
    @Autowired
    private LinkService linkService;

    /**
     * 显示指定博客详情信息
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping({"/blog/{id}"})
    public String blogDetail(
            @PathVariable long id,
            Model model) {

        Blog blog = blogService.getBlogOnfront(id);
        if (blog == null){
            throw new BlogNotFoundException("很抱歉，这篇博客不存在~");
        }
        blogService.incView(id);
        model.addAttribute("blog", blog);
        model.addAttribute("tags", tagService.listTag(blogTagMapper.selectByBlogId(id)));
        model.addAttribute("links", linkService.listAllOnFront());
        return "blog";
    }

}
