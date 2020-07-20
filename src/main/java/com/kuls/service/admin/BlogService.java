package com.kuls.service.admin;

import com.kuls.po.Blog;
import com.kuls.vo.BlogQuery;


import java.util.List;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/7 9:14 上午
 */
public interface BlogService {

    Blog getBlogByID(Long id);

    Blog getBlogOnfront(Long id);

    int save(Blog blog);

    //查出所有博客
    List<Blog> listBlog(BlogQuery blog);

    List<Blog> listBlogOnFront(BlogQuery blog);

    //热门文章
    List<Blog> listBlogOnFrontHot(BlogQuery blog);

    //实现博客阅读数的增加
    int incView(long id);

    //批量增加博客喜欢数
    int incLikes();

    int updateBlog(Blog blog);

    int deleteBlog(Long id);

}
