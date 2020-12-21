package com.project.csr.security.provider;

import com.project.csr.properties.JwtProperties;
import com.project.csr.security.model.JwtUserDetails;
import com.project.csr.security.service.impl.JwtUserDetailsService;
import com.project.csr.utils.JwtTokenUtils;
import com.project.csr.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author bin.tong
 * @since 2020/12/9 9:41
 **/
@Slf4j
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private JwtUserDetailsService jwtUserDetailsService;

    // @Resource
    // PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 根据输入的用户名密码，读取数据库中的信息
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        JwtUserDetails userDetails = (JwtUserDetails) jwtUserDetailsService.loadUserByUsername(username);

        // 判断是否有效用户
        if (!userDetails.isEnabled()) {
            throw new DisabledException("用户未处于激活状态！");
        } else if (!userDetails.isAccountNonLocked()) {
            throw new LockedException("用户处于锁定状态！");
        } else if (!userDetails.isAccountNonExpired()) {
            throw new AccountExpiredException("用户已过期！");
        } else if (!userDetails.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException("用户已登出！");
        }

        // 验证密码
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!encoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("密码错误！");
        }
        log.info(String.format("用户%s登陆成功", username));
        // 生成新token
        Map<String, String> tokens = jwtTokenUtils.generateToken(userDetails);
        String accessToken = tokens.get(jwtTokenUtils.getAccessTokenKey());
        String refreshToken = tokens.get(jwtTokenUtils.getRefreshTokenKey());
        String rolesToken = tokens.get(jwtTokenUtils.getRoleTokenKey());
        // 保存到 redis
        redisUtils.setString(jwtTokenUtils.getAccessTokenKey(username), accessToken);
        redisUtils.expire(jwtTokenUtils.getAccessTokenKey(username), jwtProperties.getAccessExpiration());
        redisUtils.setString(jwtTokenUtils.getRefreshTokenKey(username), refreshToken);
        redisUtils.expire(jwtTokenUtils.getRefreshTokenKey(username), jwtProperties.getRefreshExpiration());
        redisUtils.setString(jwtTokenUtils.getRoleTokenKey(username), rolesToken);
        redisUtils.expire(jwtTokenUtils.getRoleTokenKey(username), jwtProperties.getRolesExpiration());
        // 绑定到当前用户
        userDetails.setAccessToken(accessToken);
        userDetails.setRefreshToken(refreshToken);

        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
