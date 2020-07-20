package com.kuls.vo;

import lombok.Data;
import lombok.Getter;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/29 09:26
 */
@Getter
public enum LikedStatusEnum {

    LIKE(1, "点赞"),
    UNLIKE(0, "取消点赞/未点赞"),
    ;

    private Integer code;

    private String msg;

    LikedStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
