package com.itmuch.usercenter.domain.entity.user.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRespDTO {
    private JwtTokenRespDTO token;
    private UserRespDTO user;
}
