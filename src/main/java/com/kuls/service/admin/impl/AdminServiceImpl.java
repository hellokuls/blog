package com.kuls.service.admin.impl;

import com.kuls.mapper.AdminMapper;
import com.kuls.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/6/7 18:50
 */
@Service
public class AdminServiceImpl implements AdminService {


    @Autowired
    private AdminMapper adminMapper;


    @Override
    public Long getBlogsCount() {
        return adminMapper.getBlogsCount();
    }

    @Override
    public Long getViewsSum() {
        return adminMapper.getViewsSum();
    }

    @Override
    public Long getCommentsCount() {
        return adminMapper.getCommentsCount();
    }
}
