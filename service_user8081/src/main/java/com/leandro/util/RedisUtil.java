package com.leandro.util;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Redis 工具类（课程常见写法）
 * 封装常用的 set / get / del / expire，业务层直接调用即可
 */
@Component
public class RedisUtil {

    private final StringRedisTemplate stringRedisTemplate;

    public RedisUtil(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 写入字符串，并设置过期时间（分钟）
     */
    public void set(String key, String value, long minutes) {
        stringRedisTemplate.opsForValue().set(key, value, minutes, TimeUnit.MINUTES);
    }

    /**
     * 写入字符串，并设置过期时间（自定义单位）
     */
    public void set(String key, String value, long timeout, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    /**
     * 写入字符串（不过期）
     */
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 读取
     */
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 删除
     */
    public Boolean del(String key) {
        return stringRedisTemplate.delete(key);
    }

    /**
     * 是否存在
     */
    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(stringRedisTemplate.hasKey(key));
    }

    /**
     * 设置过期时间（分钟）
     */
    public Boolean expire(String key, long minutes) {
        return stringRedisTemplate.expire(key, minutes, TimeUnit.MINUTES);
    }

    /**
     * 查看剩余过期时间（秒），-1 永不过期，-2 key 不存在
     */
    public Long getExpire(String key) {
        return stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
    }
}
