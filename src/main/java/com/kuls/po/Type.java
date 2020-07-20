package com.kuls.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/4 2:58 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type {
    private Long id;
    @NotBlank(message = "分类名称不能为空！")
    private String name;
    private String typeImg;
    private List<Blog> blogs;

}
