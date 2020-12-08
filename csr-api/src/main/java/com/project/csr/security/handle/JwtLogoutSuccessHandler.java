package com.project.csr.security.handle;

import com.alibaba.fastjson.JSON;
import com.project.csr.common.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登出成功的调用类
 *
 * @author bin.tong
 * @since 2020/11/3 11:47
 **/
@Slf4j
@Component
public class JwtLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("注销登录成功！");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(BaseResponse.success("注销登录成功！")));
    }
}
