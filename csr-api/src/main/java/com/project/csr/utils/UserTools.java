package com.project.csr.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 获取用户信息工具
 *
 * @author: bin.tong
 * @date: 2020/7/8 17:04
 **/
@Slf4j
@Component
public class UserTools {

    public static String BEARER_TYPE = "Bearer";
    public static String ACCESS_TOKEN = "access_token";

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    /**
     * 获取当前用户 CODE
     *
     * @return
     */
    public String getCurrentUserName() {
        String token = this.getAccessToken();
        if (token == null) {
            return null;
        }
        return this.getUsername(token);
    }

    /**
     * 获取当前用户角色 CODE
     *
     * @return
     */
    // public List<String> getCurrentRoleCode() {
    //     String token = this.getAccessToken();
    //     if (token == null) {
    //         return new ArrayList<>();
    //     }
    //     return this.getRoleCode(token);
    // }

    /**
     * 通过 token 获取用户 CODE
     *
     * @param token
     * @return
     */
    public String getUsername(String token) {
        return jwtTokenUtils.getUsernameFromToken(token);
    }

    /**
     * 通过 token 获取用户角色 CODE
     *
     * @param token
     * @return
     */
    // public List<String> getRoleCode(String token) {
    //     return jwtTokenUtils.getRoleCodeByJwt(token);
    // }

    /**
     * 获取 token
     *
     * @return
     */
    public String getAccessToken() {
        String token = this.getTokenByHeader();
        if (token == null) {
            token = this.getTokenByParameter();
        }
        if (token == null) {
            log.warn("token is null");
        }
        return token;
    }

    /**
     * 获取 url 路径中的 access_token
     *
     * @return
     */
    private String getTokenByParameter() {
        if (request == null) {
            return null;
        }
        return request.getParameter(ACCESS_TOKEN);
    }

    /**
     * 获取 Header 中的 Authorization
     *
     * @return
     */
    private String getTokenByHeader() {
        if (request == null) {
            return null;
        }
        Enumeration<String> headers = request.getHeaders("Authorization");
        // 通常只有一个（大多数服务器都会强制这样做）
        while (headers.hasMoreElements()) {
            String value = headers.nextElement();
            if ((value.toLowerCase().startsWith(BEARER_TYPE.toLowerCase()))) {
                String authHeaderValue = value.substring(BEARER_TYPE.length()).trim();
                int commaIndex = authHeaderValue.indexOf(',');
                if (commaIndex > 0) {
                    authHeaderValue = authHeaderValue.substring(0, commaIndex);
                }
                return authHeaderValue;
            }
        }
        return null;
    }
}
