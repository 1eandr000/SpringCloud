package com.leandro.client;


import com.leandro.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="SERVICE-USER8081")
public interface UserClient {

    @RequestMapping("/find")
    public User findById(@RequestParam("id") int id);
}
