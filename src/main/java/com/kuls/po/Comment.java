package com.kuls.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/4 2:59 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    private Date createTime;

    private boolean adminComment;

    private Blog blog;

    private List<Comment> replyComments = new ArrayList<>();

    private Comment parentComment;

}
