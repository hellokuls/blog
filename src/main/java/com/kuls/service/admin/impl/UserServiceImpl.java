package com.kuls.service.admin.impl;

import com.kuls.mapper.UserMapper;
import com.kuls.po.User;
import com.kuls.service.admin.UserService;
import com.kuls.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/4 5:28 下午
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUser(String name, String password) {
        User user = userMapper.userCheck(name, MD5Utils.code(password));

        return user;
    }
}
