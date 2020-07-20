package com.kuls.service;

import com.kuls.mapper.CommentMapper;
import com.kuls.po.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/15 9:34 上午
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> listComments(Long blogId) {

        List<Comment> comments = commentMapper.listComments(blogId);
        List<Comment> comments1 = eachComment(comments);
        System.out.println("这是    " + comments1);
        return comments1;
    }

    @Override
    public List<Comment> listAllComments() {
        return commentMapper.listAllComments();
    }

    //返回给后台
    @Override
    public List<Comment> listAdminComments() {
        return commentMapper.listAdminComments();
    }

    /**
     * 循环每个顶级的评论节点
     *
     * @param comments
     * @return
     */
    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment, c);
            commentsView.add(c);
        }
        //合并评论的各层子代到第一级子代集合中
        combineChildren(commentsView);
        return commentsView;
    }

    /**
     * @param comments root根节点，blog不为空的对象集合
     * @return
     */
    private void combineChildren(List<Comment> comments) {

        for (Comment comment : comments) {
            List<Comment> replys1 = comment.getReplyComments();
            for (Comment reply1 : replys1) {
                //循环迭代，找出子代，存放在tempReplys中
                recursively(reply1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setReplyComments(tempReplys);
            //清除临时存放区
            tempReplys = new ArrayList<>();
        }
    }

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();

    /**
     * 递归迭代，剥洋葱
     *
     * @param comment 被迭代的对象
     * @return
     */
    private void recursively(Comment comment) {
        tempReplys.add(comment);//顶节点添加到临时存放集合
        if (comment.getReplyComments().size() > 0) {
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys) {
                tempReplys.add(reply);
                if (reply.getReplyComments().size() > 0) {
                    recursively(reply);
                }
            }
        }
    }


    @Transactional
    @Override
    public int saveComments(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        System.out.println("这里是id" + parentCommentId);
        if (parentCommentId != -1) {
            System.out.println("这里是  " + commentMapper.getCommentByParentId(parentCommentId));
            comment.setParentComment(commentMapper.getCommentByParentId(parentCommentId));
        } else {
            comment.setParentComment(null);
        }

        comment.setCreateTime(new Date());
        return commentMapper.saveComments(comment);
    }

    @Override
    public int deleteComments(Long id) {
        return commentMapper.deleteComments(id);
    }
}
