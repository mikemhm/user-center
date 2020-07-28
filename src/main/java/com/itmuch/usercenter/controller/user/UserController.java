package com.itmuch.usercenter.controller.user;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.itmuch.usercenter.domain.entity.user.User;
import com.itmuch.usercenter.domain.entity.user.dto.user.LoginRespDTO;
import com.itmuch.usercenter.domain.entity.user.dto.user.UserLoginDTO;
import com.itmuch.usercenter.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private WxMaService wxMaService;

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
    public LoginRespDTO login(UserLoginDTO userLoginDTO) throws WxErrorException {
        //微信小程序端 判断是否登录
        WxMaJscode2SessionResult result = this.wxMaService.getUserService().getSessionInfo(userLoginDTO.getCode());
        //获取微信的openid
        String openid = result.getOpenid();
        //看用户是否注册，如果没有注册就插入
        //已经登录，颁发token
        User login = this.userService.login(userLoginDTO, openid);

        return null;
    }
}
