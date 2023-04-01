package com.kuls.controller.front;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuls.mapper.BlogTagMapper;
import com.kuls.po.Blog;
import com.kuls.po.Type;
import com.kuls.po.User;
import com.kuls.service.CommentService;
import com.kuls.service.admin.BlogService;
import com.kuls.service.admin.LinkService;
import com.kuls.service.admin.TagService;
import com.kuls.service.admin.TypeService;
import com.kuls.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author kuls
 * @Desc
 * @date 2020/5/3 10:02 上午
 */
@Controller
public class  IndexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogTagMapper blogTagMapper;
    @Autowired
    private CommentService commentService;
    @Autowired
    private LinkService linkService;


    @GetMapping({"/blog", "/"})
    public String blog(@RequestParam(defaultValue = "1") int pageNum,
                       @RequestParam(defaultValue = "10") int pageSize,
                       BlogQuery blog,
                       Model model) {

        PageHelper.startPage(pageNum, pageSize);
        List<Blog> lists = blogService.listBlogOnFront(blog);
        PageInfo<Blog> blogPageInfo = new PageInfo<>(lists);
        model.addAttribute("blogs", blogPageInfo);
        model.addAttribute("tags", tagService.listTag());
        model.addAttribute("comments", commentService.listAllComments());
        model.addAttribute("hotBlogs", blogService.listBlogOnFrontHot(blog));
        model.addAttribute("links", linkService.listAllOnFront());
        return "index";
    }

    public  String getCookies(HttpServletRequest request){
        //HttpServletRequest 装请求信息类
        //HttpServletRespionse 装相应信息的类
        //   Cookie cookie=new Cookie("sessionId","CookieTestInfo");
        Cookie[] cookies =  request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("sessionId")){
                    return cookie.getValue();
                }
            }
        }

        return  null;
    }

    public  String setCookies(HttpServletResponse response) {
        //HttpServerletRequest 装请求信息类
        //HttpServerletRespionse 装相应信息的类
        Cookie cookie = new Cookie("sessionId", "CookieTestInfo");
        response.addCookie(cookie);
        return "添加cookies信息成功";
    }

    @GetMapping("/category")
    public String toCategory(@RequestParam(defaultValue = "1") int pageNum,
                             @RequestParam(defaultValue = "10") int pageSize,
                             BlogQuery blog,
                             Model model) {
        List<Type> types = typeService.listType();
        Type type = types.get(0);
        blog.setTypeId(type.getId());

        PageHelper.startPage(pageNum, pageSize);
        List<Blog> blogs = blogService.listBlogOnFront(blog);
        PageInfo<Blog> blogPageInfo = new PageInfo<>(blogs);

        model.addAttribute("types", types);
        model.addAttribute("blogs", blogPageInfo);
        model.addAttribute("typeid", type.getId());
        model.addAttribute("hotBlogs", blogService.listBlogOnFrontHot(blog));
        model.addAttribute("links", linkService.listAllOnFront());
        return "category";

    }

    @GetMapping("/category/{id}")
    public String category(@RequestParam(defaultValue = "1") int pageNum,
                           @RequestParam(defaultValue = "10") int pageSize,
                           @PathVariable long id,
                           Model model) {
        BlogQuery blog = new BlogQuery();
        blog.setTypeId(id);

        PageHelper.startPage(pageNum, pageSize);
        List<Blog> blogs = blogService.listBlogOnFront(blog);
        PageInfo<Blog> blogPageInfo = new PageInfo<>(blogs);
        List<Type> types = typeService.listType();
        model.addAttribute("types", types);
        model.addAttribute("blogs", blogPageInfo);
        model.addAttribute("typeid", id);
        model.addAttribute("typeid1", id);
        model.addAttribute("hotBlogs", blogService.listBlogOnFrontHot(blog));
        return "category :: blogList";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("links", linkService.listAllOnFront());
        return "about";
    }

    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "1") int pageNum,
                         @RequestParam(defaultValue = "10") int pageSize,
                         @RequestParam("keyword") String keyword,
                         Model model) {
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTitle(keyword);
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> blogs = blogService.listBlogOnFront(blogQuery);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("blogs", pageInfo);
        model.addAttribute("keyword", keyword);
        model.addAttribute("hotBlogs", blogService.listBlogOnFrontHot(new BlogQuery()));
        model.addAttribute("links", linkService.listAllOnFront());
        return "search";
    }
}
