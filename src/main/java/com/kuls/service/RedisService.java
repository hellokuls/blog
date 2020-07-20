package com.kuls.service;

import com.kuls.vo.BlogLikedCount;
import com.kuls.vo.BlogViewCount;

import java.util.List;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/29 09:04
 */
public interface RedisService {


    /**
     * 点赞 ---- 状态为1
     *
     * @param likedUserId 点赞用户，这里使用cookie
     * @param likedBlogId 点赞的博客
     */
    void saveLiked2Redis(String likedUserId, long likedBlogId);

    /**
     * 取消点赞  ---- 状态为0
     *
     * @param likedUserId
     * @param likeBlogId
     */
    void unlikeFromRedis(String likedUserId, long likeBlogId);

    /**
     * 从Redis中删除一条点赞数据
     *
     * @param likedUserId
     * @param likeBlogId
     */
    void deleteLikedFromRedis(String likedUserId, long likeBlogId);

    /**
     * 该博客的点赞数加1
     *
     * @param likeBlogId
     */
    void incrementLikedCount(long likeBlogId);

    /**
     * 该博客的点赞数减1
     *
     * @param likeBlogId
     */
    void decrementLikedCount(long likeBlogId);


    List<BlogLikedCount> getLikedCountFromRedis();

    List<BlogViewCount> getViewCountFromRedis();
}
