package com.project.csr.security.handle;

import com.alibaba.fastjson.JSON;
import com.project.csr.common.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功处理类,登录成功后会调用里面的方法
 *
 * @author bin.tong
 * @since 2020/11/3 11:43
 **/
@Slf4j
@Component
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("Login Success!" + authentication.getPrincipal());
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(BaseResponse.success(authentication.getPrincipal())));
    }
}
