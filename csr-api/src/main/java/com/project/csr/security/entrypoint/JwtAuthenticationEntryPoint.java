package com.project.csr.security.entrypoint;

import com.alibaba.fastjson.JSON;
import com.project.csr.common.enums.ResCodeEnum;
import com.project.csr.common.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author: bin.tong
 * @date: 2020/10/30 17:18
 **/
@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException, ServletException {
        log.info("JwtAuthenticationEntryPoint: " + authException.getMessage());

        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        BaseResponse responseBody = new BaseResponse(ResCodeEnum.RESCODE_UNAUTHORIZED, "UNAUTHORIZED");
        response.getWriter().write(JSON.toJSONString(responseBody));
        // response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");

    }
}
