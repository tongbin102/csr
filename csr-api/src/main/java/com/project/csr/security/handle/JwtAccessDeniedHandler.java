package com.project.csr.security.handle;

import com.alibaba.fastjson.JSON;
import com.project.csr.common.enums.ResCodeEnum;
import com.project.csr.common.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 没有权限,被拒绝访问时的调用类
 *
 * @author bin.tong
 * @since 2020/12/8 10:41
 **/
@Slf4j
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("JwtAccessDeniedHandler: " + accessDeniedException.getMessage());

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");

        response.getWriter().write(JSON.toJSONString(new BaseResponse<>(ResCodeEnum.RESCODE_FORBIDDEN)));
    }
}
