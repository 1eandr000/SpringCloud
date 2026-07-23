package com.leandro.mapper;

import com.leandro.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User findById(@Param("id") int id);

    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
