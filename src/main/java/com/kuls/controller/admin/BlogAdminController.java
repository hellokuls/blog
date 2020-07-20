package com.kuls.controller.admin;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuls.po.Blog;
import com.kuls.po.User;
import com.kuls.service.admin.BlogService;
import com.kuls.service.admin.TagService;
import com.kuls.service.admin.TypeService;
import com.kuls.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/7 9:19 上午
 */
@Controller
@RequestMapping("/admin")
public class BlogAdminController {


    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;


    @GetMapping("/sys/blogs")
    public String blogs() {

        return "admin/blogs";
    }

    /**
     * 后台显示博客列表
     *
     * @param pageNum  显示第几页
     * @param pageSize 每页展示的数量
     * @param model 前端model
     * @return
     */
    @GetMapping("/blogs")
    public String toBlogs(@RequestParam(defaultValue = "1") int pageNum,
                          @RequestParam(defaultValue = "10") int pageSize,

                          Model model) {

        PageHelper.startPage(pageNum, pageSize);
        //传递一个空对象即可
        BlogQuery blog = new BlogQuery();
        List<Blog> lists = blogService.listBlog(blog);
        PageInfo<Blog> blogPageInfo = new PageInfo<>(lists);
        model.addAttribute("blogs", blogPageInfo);
        model.addAttribute("types", typeService.listType());

        return "admin/blogs";
    }

    /**
     * 后台博客搜索功能
     *
     * @param pageNum
     * @param pageSize
     * @param blog
     * @param model
     * @return
     */
    @PostMapping("/blogs/search")
    public String search(@RequestParam(defaultValue = "1") int pageNum,
                         @RequestParam(defaultValue = "10") int pageSize,
                         BlogQuery blog,
                         Model model) {

        PageHelper.startPage(pageNum, pageSize);
        // 根据BlogQuery对象的属性来进行查询
        List<Blog> lists = blogService.listBlog(blog);
        PageInfo<Blog> blogPageInfo = new PageInfo<>(lists);
        model.addAttribute("blogs", blogPageInfo);
        model.addAttribute("types", typeService.listType());

        return "admin/blogs :: blogList";
    }

    /**
     * 博客编辑和发表
     *
     * @param model
     * @return
     */
    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        model.addAttribute("blog", new Blog());
        return "admin/blogs-input";
    }

    /**
     * 更新编辑博客
     *
     * @param id    指定博客的id
     * @param model
     * @return
     */
    @GetMapping("/blogs/{id}/update")
    public String editBlog(@PathVariable long id, Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        Blog blog = blogService.getBlogByID(id);
        model.addAttribute("blog", blog);
        return "admin/blogs-input";
    }

    /**
     * 保存或者发布博客
     *
     * @param blog
     * @param session
     * @param attributes
     * @return
     */
    @PostMapping("/blogs/save")
    public String save(Blog blog, HttpSession session, RedirectAttributes attributes) {
        // 获取当前用户
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTages(tagService.listTag(blog.getTagIds()));
        int save = blogService.save(blog);
        if (save > 0) {
            attributes.addFlashAttribute("msg", "操作成功");
        } else {
            attributes.addFlashAttribute("errormsg", "操作失败");
        }

        return "redirect:/admin/blogs";
    }

    /**
     * 更新博客
     *
     * @param blog
     * @param session
     * @param attributes
     * @return
     */
    @PostMapping("/updateBlogs")
    public String updateBlogs(Blog blog, HttpSession session, RedirectAttributes attributes) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTages(tagService.listTag(blog.getTagIds()));
        int update = blogService.updateBlog(blog);
        if (update > 0) {
            attributes.addFlashAttribute("msg", "操作成功");

        } else {
            attributes.addFlashAttribute("errormsg", "操作失败");
        }

        return "redirect:/admin/blogs";
    }

    /**
     * 删除博客
     *
     * @param id
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        int i = blogService.deleteBlog(id);
        if (i > 0) {
            redirectAttributes.addFlashAttribute("msg", "删除成功！");
        } else {
            redirectAttributes.addFlashAttribute("errormsg", "删除失败！");
        }
        return "redirect:/admin/blogs";
    }

    /**
     * 博客内容中插入的图片
     * @param file
     * @param request
     * @return
     */
    @PostMapping(value = "/uploadfile")
    @ResponseBody
    public Map<String,Object> demo(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file, HttpServletRequest request) {

        Map<String,Object> resultMap = new HashMap<String,Object>();
        //保存
        try {

            File imageFolder= new File(request.getServletContext().getRealPath("img/upload/blog/"));

            File targetFile = new File(imageFolder,file.getOriginalFilename());
            if(!targetFile.getParentFile().exists()){
                targetFile.mkdirs();
            }
            // 将文件写入targetFile中
            file.transferTo(targetFile);

            resultMap.put("success", 1);
            resultMap.put("message", "上传成功！");
            resultMap.put("url","/img/upload/blog/"+file.getOriginalFilename());
        } catch (Exception e) {
            resultMap.put("success", 0);
            resultMap.put("message", "上传失败！");
            e.printStackTrace();
        }
        return resultMap;
    }

}
