package com.kuls.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuls.po.Blog;
import com.kuls.po.User;
import com.kuls.service.admin.AdminService;
import com.kuls.service.admin.UserService;
import com.kuls.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/4 5:37 下午
 */
@Controller
@RequestMapping("/admin")
public class LoginAdminController {


    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    /**
     * 判断是否登录，若没登录跳转至登录页面
     *
     * @param httpSession
     * @return
     */
    @GetMapping
    public String toLogin(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            return "redirect:/admin/index";
        }

        return "admin/login";
    }


    /**
     * 跳转至后台管理首页
     *
     * @param
     * @return
     */
    @GetMapping("/index")
    public String toAdminIndex() {

        return "admin/index";
    }

    @GetMapping("/main")
    public String toAdminMain(Model model) {
        model.addAttribute("blog_count", adminService.getBlogsCount());
        model.addAttribute("comment_count", adminService.getCommentsCount());
        model.addAttribute("views_sum", adminService.getViewsSum());
        return "admin/main";
    }

    /**
     * 登录操作
     *
     * @param username
     * @param password
     * @param httpSession
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession httpSession, RedirectAttributes redirectAttributes) {
        User user = userService.checkUser(username, password);
        if (user != null) {
            user.setPassword(null);
            httpSession.setAttribute("user", user);
            return "redirect:/admin/index";
        } else {
            redirectAttributes.addFlashAttribute("msg", "用户名或者密码错误");

            return "redirect:/admin";
        }

    }

    /**
     * 注销登录
     *
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }


}
