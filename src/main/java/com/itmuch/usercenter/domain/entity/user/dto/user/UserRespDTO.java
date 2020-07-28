package com.itmuch.usercenter.domain.entity.user.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRespDTO {
    private String id;
    /**
     * 头像
     */
    private String avatarUrl;
    /**
     * 积分
     */
    private String bonus;
    /**
     * 微信昵称
     */
    private String wxNickname;
}
