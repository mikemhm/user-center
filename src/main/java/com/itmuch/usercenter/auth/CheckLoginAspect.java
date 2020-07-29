package com.itmuch.usercenter.auth;

import com.itmuch.usercenter.util.JwtOperator;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Aspect
@Component
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class CheckLoginAspect {

    private final JwtOperator jwtOperator;

    @Around("@@annotation(com.itmuch.usercenter.auth.CheckLogin)")
    public Object checkLogin(ProceedingJoinPoint point) throws Throwable {
        //1.从header里获取token
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String token = request.getHeader("X-Token");
        //2. 校验token 正常，放行，失效，抛异常
        Boolean isValid = jwtOperator.validateToken(token);
        if (!isValid){
            throw new SecurityException("token 不合法");
        }
        return point.proceed();
    }
}
