package com.kuls.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/26 20:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Link {


    private Integer id;
    private String name;
    private String url;
    private boolean canshow;
}
