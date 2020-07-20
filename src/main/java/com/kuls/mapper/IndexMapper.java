package com.kuls.mapper;

import com.kuls.po.Blog;
import com.kuls.vo.BlogQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface IndexMapper {

    Blog getBlogByID(Long id);

    //查出所有博客
    List<Blog> listBlog(BlogQuery blog);

    //查出所有博客
    List<Blog> listBlogOnFront(Blog blog);


}
