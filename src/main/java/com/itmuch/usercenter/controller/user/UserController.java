package com.itmuch.usercenter.controller.user;

import com.itmuch.usercenter.domain.entity.user.User;
import com.itmuch.usercenter.domain.entity.user.dto.user.LoginRespDTO;
import com.itmuch.usercenter.domain.entity.user.dto.user.UserLoginDTO;
import com.itmuch.usercenter.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id){
        log.info("我被请求了");
        return this.userService.findById(id);
    }

    @GetMapping("/a")
    public User getUser(User user){
        return user;
    }

    @PostMapping("/login")
    public LoginRespDTO login(UserLoginDTO userLoginDTO){

        return null;
    }
}
