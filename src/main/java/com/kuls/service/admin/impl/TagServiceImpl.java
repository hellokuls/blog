package com.kuls.service.admin.impl;

import com.kuls.mapper.TagMapper;
import com.kuls.po.Tag;
import com.kuls.service.admin.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/7 8:36 上午
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public int save(Tag tag) {
        return tagMapper.save(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagMapper.getTag(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagMapper.getTagByName(name);
    }

    @Override
    public List<Tag> listTag() {
        return tagMapper.listTag();
    }

    @Override
    public List<Tag> listTag(String ids) {

        return tagMapper.listTag1(convertToLong(ids));
    }

    @Override
    public List<Tag> listTag(List<Long> ids) {
        return tagMapper.listTag1(ids);
    }


    public List<Long> convertToLong(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] split = ids.split(",");
            for (int i = 0; i < split.length; i++) {
                list.add(new Long(split[i]));
            }
        }

        return list;
    }

    @Override
    public int updateTag(Tag tag) {
        return tagMapper.updateTag(tag);
    }

    @Override
    public int deleteTag(Long id) {
        return tagMapper.deleteTag(id);
    }
}
