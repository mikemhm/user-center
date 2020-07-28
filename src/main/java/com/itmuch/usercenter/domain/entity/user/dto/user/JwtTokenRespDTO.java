package com.itmuch.usercenter.domain.entity.user.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtTokenRespDTO {
    private String token;
    /** 过期时间 */
    private Long expirationTime;
}
