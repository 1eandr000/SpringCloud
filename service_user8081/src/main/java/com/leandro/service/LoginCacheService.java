package com.leandro.service;

import com.alibaba.fastjson.JSON;
import com.leandro.entity.User;
import com.leandro.util.RedisUtil;
import org.springframework.stereotype.Service;

/**
 * 登录缓存服务
 * 不直接操作 RedisTemplate，统一通过 RedisUtil
 *
 * key：login:session:{sessionId}
 * value：用户 JSON（不含密码）
 * 过期：30 分钟
 */
@Service
public class LoginCacheService {

    private static final String KEY_PREFIX = "login:session:";
    private static final long EXPIRE_MINUTES = 30;

    private final RedisUtil redisUtil;

    public LoginCacheService(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    private String buildKey(String sessionId) {
        return KEY_PREFIX + sessionId;
    }

    /** 登录成功：写入 Redis */
    public void saveLoginUser(String sessionId, User user) {
        String key = buildKey(sessionId);
        String json = JSON.toJSONString(user);
        redisUtil.set(key, json, EXPIRE_MINUTES);
    }

    /** 读取登录用户 */
    public User getLoginUser(String sessionId) {
        String json = redisUtil.get(buildKey(sessionId));
        if (json == null || json.isEmpty()) {
            return null;
        }
        return JSON.parseObject(json, User.class);
    }

    /** 退出：删除缓存 */
    public void removeLoginUser(String sessionId) {
        redisUtil.del(buildKey(sessionId));
    }

    /** 是否已登录 */
    public boolean isLogin(String sessionId) {
        return redisUtil.hasKey(buildKey(sessionId));
    }
}
