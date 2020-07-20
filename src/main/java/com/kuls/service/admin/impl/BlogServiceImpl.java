package com.kuls.service.admin.impl;

import com.kuls.exception.BlogNotFoundException;
import com.kuls.mapper.BlogMapper;
import com.kuls.mapper.BlogTagMapper;
import com.kuls.mapper.TagMapper;
import com.kuls.po.Blog;
import com.kuls.po.Tag;
import com.kuls.service.RedisService;
import com.kuls.service.admin.BlogService;
import com.kuls.service.admin.TagService;
import com.kuls.util.MarkdownUtils;
import com.kuls.util.MyBeanUtils;
import com.kuls.vo.BlogLikedCount;
import com.kuls.vo.BlogQuery;
import com.kuls.vo.BlogViewCount;
import io.lettuce.core.ScriptOutputType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/7 9:18 上午
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogTagMapper blogTagMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private RedisService redisService;

    @Transactional
    @Override
    public Blog getBlogByID(Long id) {
        Blog blog = blogMapper.getBlogByID(id);
        List<Long> longList = blogTagMapper.selectByBlogId(id);
        List<Tag> tags = tagMapper.listTag1(longList);
        String s = tagsToIds(tags);
        blog.setTagIds(s);
        return blog;
    }

    @Override
    public Blog getBlogOnfront(Long id) {
        Blog blog = blogMapper.getBlogByID(id);
        if (blog == null) {
            throw new BlogNotFoundException("博客不存在！");
        }
        Blog blog1 = new Blog();
        BeanUtils.copyProperties(blog, blog1);
        String content = blog1.getContent();
        blog1.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return blog1;
    }


    //1,2,3
    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public int save(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setLikes(1);
        blog.setView(1);
        int save = blogMapper.save(blog);
        handlerBlogTag(blog.getId(), blog.getTages());
        return save;
    }

    void handlerBlogTag(Long blogId, List<Tag> tags) {
        for (Tag tag : tags) {
            blogTagMapper.insertBlogTag(blogId, tag.getId());
        }
    }


    @Transactional
    @Override
    public List<Blog> listBlog(BlogQuery blog) {

        return blogMapper.listBlog(blog);
    }

    @Transactional
    @Override
    public List<Blog> listBlogOnFront(BlogQuery blog) {
        return blogMapper.listBlogOnFront(blog);
    }

    @Override
    public List<Blog> listBlogOnFrontHot(BlogQuery blog) {
        return blogMapper.listBlogOnFrontHot(blog);
    }


    @Transactional
    @Override
    public int incView(long id) {
        Blog blog = new Blog();
        blog.setId(id);
        return blogMapper.incView(blog);
    }

    @Transactional
    @Override
    public int incLikes() {
        List<BlogLikedCount> likedCountsList = redisService.getLikedCountFromRedis();
        for (BlogLikedCount count : likedCountsList) {
            Blog blog = new Blog();
            Integer blogId = count.getBlogId();
            long l = blogId.longValue();
            blog.setId(l);
            blog.setLikes(count.getAddLikedCount());
            System.out.println("这里是like的值：" + count.getAddLikedCount());
            blogMapper.incLikes(blog);
        }
        return 1;
    }


    @Transactional
    @Override
    public int updateBlog(Blog blog) {
        Blog blog1 = blogMapper.getBlogByID(blog.getId());
        BeanUtils.copyProperties(blog, blog1, MyBeanUtils.getNullPropertyNames(blog));
        blog1.setUpdateTime(new Date());

        int update = blogMapper.updateBlog(blog1);
        //删除所有标签（之后优化）
        blogTagMapper.delete(blog.getId());
        //插入新的标签
        handlerBlogTag(blog.getId(), blog.getTages());
        return update;
    }

    @Transactional
    @Override
    public int deleteBlog(Long id) {
        return blogMapper.deleteBlog(id);
    }


}
