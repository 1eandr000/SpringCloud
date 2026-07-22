package com.leandro.client;


import com.leandro.breaker.UserBreaker;
import com.leandro.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="SERVICE-USER8081",fallback = UserBreaker.class)
public interface UserClient {

    @RequestMapping("/find")
    public User findById(@RequestParam("id") int id);
}
