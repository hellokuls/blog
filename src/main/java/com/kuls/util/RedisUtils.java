package com.kuls.util;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/29 09:23
 */
public class RedisUtils {

    //保存用户点赞数据的key
    public static final String MAP_KEY_USER_LIKED = "MAP_USER_LIKED";
    //保存用户被点赞数量的key
    public static final String MAP_KEY_USER_LIKED_COUNT = "MAP_USER_LIKED_COUNT";
    //保存用户阅读次数的key
    public static final String MAP_KEY_USER_VIEW_COUNT = "MAP_KEY_USER_VIEW_COUNT";

    /**
     * 拼接被点赞的用户id和被点赞的博客id作为key。格式 222222::333333
     *
     * @param likedUserId 被点赞的人id
     * @param likedBlogId 被点赞的博客id
     * @return
     */
    public static String getLikedKey(String likedUserId, long likedBlogId) {
        StringBuilder builder = new StringBuilder();
        builder.append(likedUserId);
        builder.append("::");
        String blogId = String.valueOf(likedBlogId);
        builder.append(blogId);
        return builder.toString();
    }


    public static String getViewKey(String viewUserId, long BlogId) {
        StringBuilder builder = new StringBuilder();
        builder.append(viewUserId);
        builder.append("::");
        String blogId = String.valueOf(BlogId);
        builder.append(blogId);
        return builder.toString();
    }

}
