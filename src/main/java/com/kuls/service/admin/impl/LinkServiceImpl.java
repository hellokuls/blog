package com.kuls.service.admin.impl;

import com.kuls.mapper.LinkMapper;
import com.kuls.po.Link;
import com.kuls.service.admin.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/26 20:40
 */
@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkMapper linkMapper;


    @Override
    public List<Link> listAll() {
        return linkMapper.listAll();
    }

    @Override
    public List<Link> listAllOnFront() {
        return linkMapper.listAllOnFront();
    }

    @Override
    public int addLink(Link link) {
        return linkMapper.addLink(link);
    }

    @Override
    public int updateLink(Link link) {
        return linkMapper.updateLink(link);
    }

    @Override
    public Link getLinkByID(int id) {
        return linkMapper.getLinkByID(id);
    }


    @Override
    public int deleteLink(int id) {
        return linkMapper.deleteLink(id);
    }
}
