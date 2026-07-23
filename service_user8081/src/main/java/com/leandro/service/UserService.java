package com.leandro.service;

import com.leandro.entity.User;
import com.leandro.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper){
        this.userMapper=userMapper;
    }

    public User findById(int id){
        return userMapper.findById(id);
    }

    public User login(String username, String password) {
        return userMapper.findByUsernameAndPassword(username, password);
    }
}
