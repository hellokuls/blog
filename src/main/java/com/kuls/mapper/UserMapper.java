package com.kuls.mapper;

import com.kuls.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/4 5:29 下午
 */
@Mapper
@Repository
public interface UserMapper {

    User userCheck(@PathVariable String username, @PathVariable String password);
}
