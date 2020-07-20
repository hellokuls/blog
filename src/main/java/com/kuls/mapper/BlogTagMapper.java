package com.kuls.mapper;

import com.kuls.po.BlogTag;
import com.kuls.po.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/9 3:38 下午
 */
@Mapper
@Repository
public interface BlogTagMapper {

    int insertBlogTag(@Param("blogId") Long blogId, @Param("tagId") Long tagId);

    int delete(@Param("blogId") Long blogId);

    List<Long> selectByBlogId(@Param("blogId") long blogId);
}
