package com.leandro.client;


import com.leandro.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@FeignClient(name="SERVICE-USER8081")
public interface UserClient {

    @RequestMapping("/find")
    public User findById(@RequestParam("id") int id);

    @RequestMapping ("/login")
    public Map<String, Object> login(@RequestParam String username, @RequestParam String password);
}
