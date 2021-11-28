package com.test.timer.Utils;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 *
 * @author libo
 * @date 2021/11/28 20:58
 **/
public class RedisUtils {

    /**
     * 静态注入redisTemplate
     */
    private static RedisTemplate redisTemplate = SpringUtils.getBean("redisTemplate", RedisTemplate.class);

    /**
     * 普通缓存放入
     *
     * @param key   键 不能为null
     * @param value 值 不能为null
     */
    @SuppressWarnings("unchecked")
    public static void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 普通缓存放入并设置缓存失效时间
     *
     * @param key   键 不能为null
     * @param value 值 不能为null
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     */
    @SuppressWarnings("unchecked")
    public static void set(String key, Object value, long time) {
        if (time > 0) {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        } else {
            set(key, value);
        }
    }

}
