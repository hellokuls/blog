package com.kuls.mapper;

import com.kuls.po.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/15 9:29 上午
 */
@Mapper
@Repository
public interface CommentMapper {

    List<Comment> listComments(@Param("blogId") Long blogId);


    List<Comment> listAllComments();

    List<Comment> listAdminComments();

    int saveComments(Comment comment);

    Comment getCommentByParentId(@Param("parentId") Long parentId);

    int deleteComments(@Param("id") Long id);
}
