package com.leandro.controller;

import com.leandro.client.UserClient;
import com.leandro.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserClient userClient;

    @RequestMapping("/find")
    public User findById(@RequestParam("id") int id){
        return userClient.findById(id);
    }
}
