package com.kuls.mapper;

import com.kuls.po.Link;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/26 20:25
 */
@Mapper
@Repository
public interface LinkMapper {

    // 返回给后台
    @Transactional
    @Select("select * from t_link")
    List<Link> listAll();

    //返回前端显示
    @Transactional
    @Select("select * from t_link  where canshow = true")
    List<Link> listAllOnFront();

    //申请友链
    @Transactional
    @Insert("insert into t_link(name,url,canshow) values(#{name},#{url},false)")
    int addLink(Link link);

    //后台编辑
    @Transactional
    @Update("update t_link set name = #{name},url = #{url},canshow = #{canshow} where id = #{id}")
    int updateLink(Link link);

    //删除友链
    @Transactional
    @Delete("delete from t_link where id = #{id}")
    int deleteLink(@Param("id") int id);

    //根据id获取友链
    @Transactional
    @Select("select * from t_link where id = #{id}")
    Link getLinkByID(@Param("id") int id);


}
