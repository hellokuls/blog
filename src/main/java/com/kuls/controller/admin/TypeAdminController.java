package com.kuls.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuls.po.Type;
import com.kuls.service.admin.TypeService;
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
public class TypeAdminController {

    @Autowired
    private TypeService typeService;


    /**
     * 分类页面
     *
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @GetMapping("/types")
    public String toTypes(@RequestParam(defaultValue = "1") int pageNum,
                          @RequestParam(defaultValue = "10") int pageSize,
                          Model model) {
        PageHelper.startPage(pageNum, pageSize);
        List<Type> list = typeService.listType();
        PageInfo<Type> typePageInfo = new PageInfo<>(list);

        model.addAttribute("page", typePageInfo);
        return "admin/types";
    }


    /**
     * 保存分类
     *
     * @param type
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/saveType")
    public String save(Type type, RedirectAttributes redirectAttributes) {
        Type t = typeService.getTypeByName(type.getName());
        if (t != null) {
            redirectAttributes.addFlashAttribute("msg", "不能存在相同的分类");
            return "redirect:/admin/types/input";
        }

        if (type == null) {
            redirectAttributes.addFlashAttribute("errormsg", "操作失败！！！");
        } else {
            redirectAttributes.addFlashAttribute("msg", "操作成功！！！");
        }

        int save = typeService.save(type);
        if (save > 0) {
            System.out.println("save success!!!!");
        }
        return "redirect:/admin/types";
    }

    /**
     * 更新分类
     *
     * @param type
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/updateTypes/{id}")
    public String updateTypes(Type type, RedirectAttributes redirectAttributes) {
//        Type t = typeService.getTypeByName((String) param.get("name"));
//        if (t != null){
//            redirectAttributes.addFlashAttribute("msg","不能存在相同的分类");
//            return "redirect:/admin/types/input";
//        }

        if (type == null) {
            redirectAttributes.addFlashAttribute("errormsg", "更新失败！！！");
        } else {
            redirectAttributes.addFlashAttribute("msg", "更新成功！！！");
        }


        int update = typeService.updateType(type);
        if (update > 0) {
            System.out.println("save success!!!!");
        }
        return "redirect:/admin/types";
    }


    /**
     * 跳转到新增页面
     *
     * @param model
     * @return
     */
    @GetMapping("/types/input")
    public String toInput(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }


    /**
     * 更新分类信息
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/types/{id}/update")
    public String toUpdate(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";
    }

    /**
     * 删除分类信息
     *
     * @param id
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        int i = typeService.deleteType(id);
        if (i > 0) {
            redirectAttributes.addFlashAttribute("msg", "删除成功！");
        } else {
            redirectAttributes.addFlashAttribute("errormsg", "删除失败！");
        }
        return "redirect:/admin/types";
    }

}
