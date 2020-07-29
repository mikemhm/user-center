package com.itmuch.usercenter.controller.user;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.itmuch.usercenter.domain.dto.user.JwtTokenRespDTO;
import com.itmuch.usercenter.domain.dto.user.LoginRespDTO;
import com.itmuch.usercenter.domain.dto.user.UserLoginDTO;
import com.itmuch.usercenter.domain.dto.user.UserRespDTO;
import com.itmuch.usercenter.domain.entity.user.User;
import com.itmuch.usercenter.service.user.UserService;
import com.itmuch.usercenter.util.JwtOperator;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private WxMaService wxMaService;
    @Autowired
    private JwtOperator jwtOperator;

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
    public LoginRespDTO login(@RequestBody UserLoginDTO userLoginDTO) throws WxErrorException {
        //微信小程序端 判断是否登录
        WxMaJscode2SessionResult result = this.wxMaService.getUserService().getSessionInfo(userLoginDTO.getCode());
        //获取微信的openid
        String openid = result.getOpenid();
        //看用户是否注册，如果没有注册就插入
        //已经登录，颁发token
        User user = this.userService.login(userLoginDTO, openid);

        HashMap<String, Object> userInfo = new HashMap<>();
        userInfo.put("id",user.getId());
        userInfo.put("wxNickname",user.getWxNickname());
        userInfo.put("role",user.getRoles());
        String token = jwtOperator.generateToken(userInfo);
        log.info("用户：{} 登录成功，token={}，有效期到={}",user.getWxNickname(),token,jwtOperator.getExpirationDateFromToken(token));
        return LoginRespDTO.builder()
                .user(UserRespDTO.builder()
                        .id(user.getId())
                        .avatarUrl(user.getAvatarUrl())
                        .bonus(user.getBonus())
                        .wxNickname(user.getWxNickname())
                        .build())
                .token(JwtTokenRespDTO.builder()
                        .token(token)
                        .expirationTime(jwtOperator.getExpirationDateFromToken(token).getTime())
                        .build())
                .build();
    }
}
