package com.leandro.breaker;

import com.leandro.client.UserClient;
import com.leandro.entity.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserBreaker implements UserClient {
    @Override
    public User findById(int id) {
        User user = new User();
        user.setId(id);
        user.setName("系统异常，请联系管理员！");
        return user;
    }

    @Override
    public Map<String, Object> login(String username, String password) {
        Map<String,Object> map=new HashMap<>();
        map.put("msg","登录失败，请联系管理员！");
        return map;
    }

}
