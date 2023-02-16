package com.example.library.service;

import com.example.library.entity.Users;
import com.example.library.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *@program: library
 *@description: 用户业务类
 *@author: yubin
 *@create: 2023-02-16 15:45
 */
@Service
public class UserService  {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户名查询用户信息
     * @param username 用户名
     */
    public Users findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    /**
     *  根据用户id查询用户信息
     * @param id
     * @return
     */
    public Users userDetail(Integer id) {
        return userMapper.selectById(id);
    }
}