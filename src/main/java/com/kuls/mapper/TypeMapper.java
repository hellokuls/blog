package com.kuls.mapper;

import com.github.pagehelper.PageInfo;
import com.kuls.po.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/5 7:21 下午
 */
@Mapper
@Repository
public interface TypeMapper {

    @Transactional
    int save(Type type);

    @Transactional
    Type getType(Long id);

    @Transactional
    Type getTypeByName(@Param("name") String name);

    @Transactional
    @Select("select * from t_type")
    List<Type> listType();

    @Transactional
    int updateType(Type type);

    @Transactional
    int deleteType(Long id);
}
