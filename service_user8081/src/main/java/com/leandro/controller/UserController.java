package com.leandro.controller;

import com.leandro.entity.User;
import com.leandro.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }


    @RequestMapping("/find")
    public User findById(@RequestParam("id") int id){
        return userService.findById(id);
    }

}
