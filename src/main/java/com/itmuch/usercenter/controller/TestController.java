package com.itmuch.usercenter.controller;

import com.itmuch.usercenter.dao.user.UserMapper;
import com.itmuch.usercenter.domain.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/test")
    public User testInsert(){
        User user = new User();
        user.setAvatarUrl("xxx");
        user.setBonus(1);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        //insert 插入所有字段
        //insertSelective 有哪些字段 插入哪些
        this.userMapper.insertSelective(user);
        return user;
    }
}
