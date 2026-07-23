package com.leandro.controller;

import com.leandro.entity.User;
import com.leandro.service.LoginCacheService;
import com.leandro.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Session + Redis 登录缓存
 * Controller → LoginCacheService → RedisUtil → Redis
 */
@RestController
public class UserController {

    private static final String SESSION_USER = "loginUser";

    private final UserService userService;
    private final LoginCacheService loginCacheService;

    public UserController(UserService userService, LoginCacheService loginCacheService) {
        this.userService = userService;
        this.loginCacheService = loginCacheService;
    }

    @RequestMapping("/find")
    public User findById(@RequestParam("id") int id) {
        return userService.findById(id);
    }

    /**
     * 登录
     * POST /login?username=u001&password=123
     */
    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        Map<String, Object> map = new HashMap<>();

        User user = userService.login(username, password);
        if (user == null) {
            map.put("code", 0);
            map.put("msg", "用户名或密码错误");
            return map;
        }

        user.setPassword(null);
        session.setAttribute(SESSION_USER, user);

        // 通过 LoginCacheService → RedisUtil 写入 Redis
        loginCacheService.saveLoginUser(session.getId(), user);

        map.put("code", 1);
        map.put("msg", "登录成功");
        map.put("data", user);
        map.put("sessionId", session.getId());
        map.put("cache", "redis-util");
        return map;
    }

    /**
     * 获取当前登录用户（优先读 Redis）
     * GET /getLoginUser
     */
    @GetMapping("/getLoginUser")
    public Map<String, Object> getLoginUser(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        String sessionId = session.getId();

        User user = loginCacheService.getLoginUser(sessionId);
        if (user == null) {
            user = (User) session.getAttribute(SESSION_USER);
            if (user != null) {
                map.put("from", "session-fallback");
            }
        } else {
            map.put("from", "redis");
        }

        if (user == null) {
            map.put("code", 0);
            map.put("msg", "未登录");
            return map;
        }

        map.put("code", 1);
        map.put("msg", "已登录");
        map.put("data", user);
        return map;
    }

    /**
     * 退出登录
     * POST /logout
     */
    @PostMapping("/logout")
    public Map<String, Object> logout(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        loginCacheService.removeLoginUser(session.getId());
        session.invalidate();
        map.put("code", 1);
        map.put("msg", "已退出登录");
        return map;
    }
}
