package com.kuls.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuls.po.Link;
import com.kuls.po.Tag;
import com.kuls.po.Type;
import com.kuls.service.admin.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/27 07:13
 */
@Controller
@RequestMapping("/admin")
public class  LinkAdminController {

    @Autowired
    private LinkService linkService;

    /**
     * 后台友情链接管理页面
     *
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @GetMapping("/links")
    public String toLink(@RequestParam(defaultValue = "1") int pageNum,
                         @RequestParam(defaultValue = "10") int pageSize,
                         Model model) {

        PageHelper.startPage(pageNum, pageSize);
        List<Link> links = linkService.listAll();
        PageInfo<Link> pageInfo = new PageInfo<>(links);
        model.addAttribute("linkslist", pageInfo);

        return "admin/links";
    }

    /**
     * 跳转到新增页面
     *
     * @param model
     * @return
     */
    @GetMapping("/links/input")
    public String toInput(Model model) {
        model.addAttribute("link", new Link());
        return "admin/links-input";
    }

    /**
     * 保存分类
     *
     * @param link
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/saveLinks")
    public String save(Link link, RedirectAttributes redirectAttributes) {

        if (link == null) {
            redirectAttributes.addFlashAttribute("errormsg", "操作失败！！！");
        } else {
            redirectAttributes.addFlashAttribute("msg", "操作成功！！！");
        }

        int save = linkService.addLink(link);
        if (save > 0) {
            System.out.println("save success!!!!");
        }
        return "redirect:/admin/links";
    }


    /**
     * 删除友情链接
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/links/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        int i = linkService.deleteLink(id);
        if (i > 0) {
            model.addAttribute("msg", "操作成功！");
        } else {
            model.addAttribute("errormsg", "操作失败！");
        }

        return "redirect:/admin/links";
    }


    /**
     * 跳转更新界面
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/links/{id}/update")
    public String toUpdateLink(@PathVariable int id, Model model) {
        model.addAttribute("link", linkService.getLinkByID(id));
        return "admin/links-input";
    }


    /**
     * 更新友情链接操作
     *
     * @param link
     * @param id
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/updateLink/{id}")
    public String updateLink(Link link, @PathVariable int id, RedirectAttributes redirectAttributes) {

        int update = linkService.updateLink(link);
        if (update > 0) {
            redirectAttributes.addFlashAttribute("msg", "更新成功！！！");
        } else {

            redirectAttributes.addFlashAttribute("errormsg", "更新失败！！！");
        }
        return "redirect:/admin/links";
    }
}
