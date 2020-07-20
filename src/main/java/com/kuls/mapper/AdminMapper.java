package com.kuls.mapper;

import com.kuls.po.Blog;
import com.kuls.vo.BlogQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kuls
 * @Desc 后台管理首页数据
 * @date 2020/5/7 9:18 上午
 */
@Mapper
@Repository
public interface AdminMapper {


    @Select("select count(*) from t_blog")
    Long getBlogsCount();

    @Select("select sum(view) from t_blog")
    Long getViewsSum();

    @Select("select count(*) from t_comment")
    Long getCommentsCount();

}
