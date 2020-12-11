package com.project.csr.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author bin.tong
 * @since 2020/10/30 17:20
 **/
@Component
@ConfigurationProperties(prefix = "jwt.security")
@Data
public class JwtProperties {

    /**
     * 认证路径
     */
    private String path = "/auth";

    /**
     * 请求投中携带token的名称
     */
    private String tokenHeader = "Authorization";

    /**
     * secret
     */
    private String secret = "secret";

    /**
     * 过期时长，单位为秒,可以通过配置写入
     */
    // private int expiration = 60 * 60 * 24 * 30;

    private Integer accessExpiration = 60 * 60;

    private Integer rolesExpiration = 60 * 5;

    private Integer refreshExpiration = 60 * 60 * 24 * 7;

    // public String getPath() {
    //     return path;
    // }
    //
    // public JwtProperties setPath(String path) {
    //     this.path = path;
    //     return this;
    // }
    //
    // public String getTokenHeader() {
    //     return tokenHeader;
    // }
    //
    // public JwtProperties setTokenHeader(String tokenHeader) {
    //     this.tokenHeader = tokenHeader;
    //     return this;
    // }
    //
    // public String getSecret() {
    //     return secret;
    // }
    //
    // public JwtProperties setSecret(String secret) {
    //     this.secret = secret;
    //     return this;
    // }
    //
    // public int getExpiration() {
    //     return expiration;
    // }
    //
    // public JwtProperties setExpiration(int expiration) {
    //     this.expiration = expiration;
    //     return this;
    // }
}
