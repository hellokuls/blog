package com.kuls.mapper;

import com.kuls.po.Blog;
import com.kuls.vo.BlogQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/7 9:18 上午
 */
@Mapper
@Repository
public interface BlogMapper {

    Blog getBlogByID(Long id);

    //查出所有博客
    List<Blog> listBlog(BlogQuery blog);

    //查出所有博客显示在前端页面
    List<Blog> listBlogOnFront(BlogQuery blog);

    //热门文章
    List<Blog> listBlogOnFrontHot(BlogQuery blog);

    //实现博客阅读数的增加
    int incView(Blog blog);

    //实现博客喜欢数的增加
    int incLikes(Blog blog);

    int save(Blog blog);

    int updateBlog(Blog blog);

    int deleteBlog(@Param("id") Long id);

}
