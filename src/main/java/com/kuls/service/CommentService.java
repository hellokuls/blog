package com.kuls.service;

import com.kuls.po.Comment;

import java.util.List;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/15 9:33 上午
 */
public interface CommentService {


    List<Comment> listComments(Long blogId);

    List<Comment> listAllComments();

    List<Comment> listAdminComments();

    int saveComments(Comment comment);

    int deleteComments(Long id);
}
