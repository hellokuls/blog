package com.kuls.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/29 09:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogViewCount {

    private Integer blogId;

    private int viewCount;
}
