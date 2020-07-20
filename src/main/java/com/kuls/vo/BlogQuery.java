package com.kuls.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/8 2:49 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogQuery {

    private String title;

    private Long typeId;

    private boolean recommend;
}
