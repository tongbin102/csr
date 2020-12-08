package com.project.csr.security.handle;

import com.alibaba.fastjson.JSON;
import com.project.csr.common.enums.ResCodeEnum;
import com.project.csr.common.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录账号密码错误等情况下,会调用的处理类
 *
 * @author bin.tong
 * @since 2020/11/3 11:37
 **/
@Slf4j
@Component
public class JwtAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("Login failure! JwtAuthenticationFailureHandler:" + authException.getMessage());

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        String resMsg = "";
        if (authException instanceof LockedException) {
            resMsg = "账户被锁定，登录失败！";
        } else if (authException instanceof BadCredentialsException) {
            resMsg = "用户名或密码输入错误，登录失败！";
        } else if (authException instanceof DisabledException) {
            resMsg = "账户被禁用，登录失败！";
        } else if (authException instanceof AccountExpiredException) {
            resMsg = "账户过期，登录失败！";
        } else if (authException instanceof CredentialsContainer) {
            resMsg = "密码过期，登录失败";
        } else {
            resMsg = "登录失败！";
        }
        response.getWriter().write(JSON.toJSONString(new BaseResponse<>(ResCodeEnum.RESCODE_UNAUTHORIZED, resMsg)));
    }
}
