package com.example.shiro.service;

import com.example.shiro.pojo.User;

public interface UserService {

    /**
     * 用户注册
     * @param user
     */
    void register(User user);

    /**
     * 根据用户名获取用户
     * @param userName
     * @return
     */
    User findUserByUserName(String userName);
}
