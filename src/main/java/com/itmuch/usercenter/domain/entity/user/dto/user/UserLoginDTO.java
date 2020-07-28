package com.itmuch.usercenter.domain.entity.user.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    /**
     * 微信code
     */
    private String code;
    /**
     * 头像地址
     */
    private String avatarUrl;
    /**
     * 微信昵称
     */
    private String wxNickname;
}
