package com.kuls.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuls.po.Tag;


import com.kuls.service.admin.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/5 7:40 下午
 */
@Controller
@RequestMapping("/admin")
public class TagAdminController {

    @Autowired
    private TagService tagService;

    /**
     * 标签列表
     *
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @GetMapping("/tags")
    public String toTags(@RequestParam(defaultValue = "1") int pageNum,
                         @RequestParam(defaultValue = "10") int pageSize,
                         Model model) {
        PageHelper.startPage(pageNum, pageSize);
        List<Tag> list = tagService.listTag();
        PageInfo<Tag> tagPageInfo = new PageInfo<>(list);

        model.addAttribute("page", tagPageInfo);
        return "admin/tags";
    }

    /**
     * 保存新增标签
     *
     * @param tag
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/saveTag")
    public String save(Tag tag, RedirectAttributes redirectAttributes) {
        Tag t = tagService.getTagByName(tag.getName());
        if (t != null) {
            redirectAttributes.addFlashAttribute("msg", "不能存在相同的标签");
            return "redirect:/admin/tags/input";
        }

        if (tag == null) {
            redirectAttributes.addFlashAttribute("errormsg", "操作失败！！！");
        } else {
            redirectAttributes.addFlashAttribute("msg", "操作成功！！！");
        }
        int save = tagService.save(tag);
        if (save > 0) {
            System.out.println("save success!!!!");
        }
        return "redirect:/admin/tags";
    }

    /**
     * 更新标签
     *
     * @param tag
     * @param id
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/updateTags/{id}")
    public String updateTags(Tag tag, @PathVariable Long id, RedirectAttributes redirectAttributes) {
//        Tag t = tagService.getTagByName(tag.getName());
//        if (t != null){
//            redirectAttributes.addFlashAttribute("msg","不能存在相同的分类");
//            return "redirect:/admin/tags/input";
//        }

        if (tag == null) {
            redirectAttributes.addFlashAttribute("errormsg", "更新失败！！！");
        } else {
            redirectAttributes.addFlashAttribute("msg", "更新成功！！！");
        }
        int update = tagService.updateTag(tag);
        if (update > 0) {
            System.out.println("save success!!!!");
        }
        return "redirect:/admin/tags";
    }


    /**
     * 跳转编辑标签页面
     *
     * @param model
     * @return
     */
    @GetMapping("/tags/input")
    public String toInput(Model model) {
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }


    /**
     * 标签更新
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/tags/{id}/update")
    public String toUpdate(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tags-input";
    }

    /**
     * 删除标签
     *
     * @param id
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        int i = tagService.deleteTag(id);
        if (i > 0) {
            redirectAttributes.addFlashAttribute("msg", "删除成功！");
        } else {
            redirectAttributes.addFlashAttribute("errormsg", "删除失败！");
        }
        return "redirect:/admin/tags";
    }

}
