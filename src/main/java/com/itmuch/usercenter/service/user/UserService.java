package com.itmuch.usercenter.service.user;

import com.itmuch.usercenter.dao.user.UserMapper;
import com.itmuch.usercenter.domain.entity.user.User;
import com.itmuch.usercenter.domain.dto.user.UserLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findById(Integer id){
        return this.userMapper.selectByPrimaryKey(id);
    }

    public User login(UserLoginDTO loginDTO,String openId){
        //看用户是否注册，如果没有注册就插入
        User user = userMapper.selectOne(User.builder().wxId(openId).build());
        if (user == null) {
            User userToSave = User.builder()
                    .wxId(openId)
                    .bonus(0)
                    .wxNickname(loginDTO.getWxNickname())
                    .avatarUrl(loginDTO.getAvatarUrl())
                    .roles("user")
                    .createTime(new Date())
                    .updateTime(new Date())
                    .build();
            userMapper.insertSelective(userToSave);
            return userToSave;
        }
        return user;

    }
}
