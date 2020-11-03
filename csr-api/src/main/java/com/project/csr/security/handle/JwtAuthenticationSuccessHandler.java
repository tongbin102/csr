package com.project.csr.security.handle;

import com.alibaba.fastjson.JSON;
import com.project.csr.common.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: bin.tong
 * @date: 2020/11/3 11:43
 **/
@Slf4j
@Component
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("Login Success!" + authentication.getPrincipal());
        response.getWriter().write(JSON.toJSONString(BaseResponse.success(authentication.getPrincipal())));
    }
}
