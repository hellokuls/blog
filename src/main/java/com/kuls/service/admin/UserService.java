package com.kuls.service.admin;

import com.kuls.po.User;
import org.springframework.stereotype.Service;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/4 5:27 下午
 */

public interface UserService {

    /*
    检查用户名和密码
     */
    User checkUser(String name, String password);
}
