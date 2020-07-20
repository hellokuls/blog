package com.kuls.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/9 3:35 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogTag {

    private Long id;

    private Long blog_id;

    private Integer tags_id;

}
