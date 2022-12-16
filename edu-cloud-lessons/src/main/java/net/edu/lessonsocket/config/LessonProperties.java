package net.edu.lessonsocket.config;

import io.netty.channel.ChannelHandlerContext;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author 马佳浩
 * @date 2022/12/16 09:37:39
 * @description
 */
public class LessonProperties {

    /**
     * 存储用户对应的通道
     * 一个用户一个通道
     */
    public static   Map<Long, ChannelHandlerContext> USER_CHANNEL = new ConcurrentHashMap<>(16);

    /**
     * 存储用户对应的课堂
     * 一个用户一个课堂
     */
    public static  Map<Long, Long> USER_LESSON = new ConcurrentHashMap<>(16);


    /**
     * 存储课堂用户
     */
    public static   Map<Long, HashSet<Long>> LESSON_USERS = new ConcurrentHashMap<>(16);


    /**
     * 存储当前连接上的通道
     */
    public static  List<ChannelHandlerContext> LIST_LOBBY = new CopyOnWriteArrayList<>();

}
