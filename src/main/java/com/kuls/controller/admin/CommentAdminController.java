package com.kuls.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuls.po.Comment;
import com.kuls.service.CommentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/22 10:57
 */
@Controller
@RequestMapping("/admin")
public class CommentAdminController {

    @Autowired
    private CommentService commentService;

    /**
     * 后台评论管理页面
     *
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @GetMapping("/comment")
    public String toComment(@RequestParam(defaultValue = "1") int pageNum,
                            @RequestParam(defaultValue = "10") int pageSize,
                            Model model) {
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> comments = commentService.listAdminComments();
        PageInfo<Comment> commentPageInfo = new PageInfo<>(comments);

        model.addAttribute("comments", commentPageInfo);
        return "admin/comments";
    }

    /**
     * 删除评论
     *
     * @param id
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/comment/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        int i = commentService.deleteComments(id);
        if (i > 0) {
            redirectAttributes.addFlashAttribute("msg", "删除成功！");
        } else {
            redirectAttributes.addFlashAttribute("errormsg", "删除失败！");
        }
        return "redirect:/admin/comment";
    }


}
