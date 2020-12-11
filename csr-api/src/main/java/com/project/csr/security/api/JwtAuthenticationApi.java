package com.project.csr.security.api;

import com.project.csr.common.enums.ResCodeEnum;
import com.project.csr.common.exceptions.GlobalException;
import com.project.csr.common.exceptions.ServiceException;
import com.project.csr.constants.SecurityConstant;
import com.project.csr.model.po.UserPo;
import com.project.csr.properties.JwtProperties;
import com.project.csr.security.model.JwtUserDetails;
import com.project.csr.utils.JwtTokenUtils;
import com.project.csr.utils.RedisUtils;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: bin.tong
 * @date: 2020/11/3 9:21
 **/
@Slf4j
@RestController
public class JwtAuthenticationApi {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private RedisUtils redisUtils;

    @PostMapping("${jwt.security.path}")
    public Map<String, String> createAuthenticationToken(@RequestBody UserPo user) throws Exception {
        String username = user.getUsername();
        String password = user.getPassword();
        log.info("username: " + username + ", password: " + password);
        Authentication authentication = authenticate(username, password);

        String accessToken = ((JwtUserDetails) authentication.getPrincipal()).getAccessToken();
        String refreshToken = ((JwtUserDetails) authentication.getPrincipal()).getRefreshToken();
        Map<String, String> tokens = new HashMap<>();
        tokens.put(jwtTokenUtils.getAccessTokenKey(), accessToken);
        tokens.put(jwtTokenUtils.getRefreshTokenKey(), refreshToken);
        return tokens;
    }

    @GetMapping("/token/refresh/{token}")
    public String refreshToken(@PathVariable(value = "token") String token) {
        if (token == null) {
            log.error("令牌不能为空！");
            throw new GlobalException(ResCodeEnum.RESCODE_BAD_REQUEST);
        }
        String refreshToken = token.substring(SecurityConstant.TOKEN_PREFIX.length());
        String username = jwtTokenUtils.getUsernameFromToken(refreshToken);
        if (username == null) {
            log.error("令牌格式有误！");
            throw new GlobalException(ResCodeEnum.RESCODE_BAD_REQUEST);
        }
        String accessToken = redisUtils.getString(jwtTokenUtils.getAccessTokenKey(username));
        String newAccessToken = jwtTokenUtils.refreshToken(refreshToken, accessToken);
        if (newAccessToken == null) {
            log.error("令牌格式有误！");
            throw new GlobalException(ResCodeEnum.RESCODE_BAD_REQUEST);
        }
        if ("".equals(newAccessToken)) {
            log.error("令牌不要频繁刷新！");
            throw new GlobalException(ResCodeEnum.RESCODE_BAD_REQUEST);
        }
        redisUtils.setString(jwtTokenUtils.getAccessTokenKey(username), newAccessToken);
        redisUtils.expire(jwtTokenUtils.getAccessTokenKey(username),jwtProperties.getAccessExpiration());
        return newAccessToken;
    }

    @GetMapping("/token")
    public JwtUserDetails getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(jwtProperties.getTokenHeader()).substring(SecurityConstant.TOKEN_PREFIX.length());
        String username = null;
        try {
            username = jwtTokenUtils.getUsernameFromToken(token);
        } catch (ExpiredJwtException e) {
            throw new ServiceException(ResCodeEnum.RESCODE_UNAUTHORIZED);
        }
        if (null != username) {
            return (JwtUserDetails) userDetailsService.loadUserByUsername(username);
        } else {
            throw new ServiceException(ResCodeEnum.RESCODE_BAD_REQUEST);
        }
    }

    @DeleteMapping(value="/token/{token}")
    public void deleteToken(@PathVariable(value="token")String token){
        if (token == null) {
            throw new ServiceException(ResCodeEnum.RESCODE_BAD_REQUEST, "令牌不能为空");
        }
        String refreshToken = token.substring(SecurityConstant.TOKEN_PREFIX.length());
        String username = jwtTokenUtils.getUsernameFromToken(refreshToken);
        if (null == username) {
            throw new ServiceException(ResCodeEnum.RESCODE_BAD_REQUEST, "令牌格式有误");
        }
        redisUtils.expire(jwtTokenUtils.getAccessTokenKey(username), 1);
        redisUtils.expire(jwtTokenUtils.getRefreshTokenKey(username), 1);
        redisUtils.expire(jwtTokenUtils.getRoleTokenKey(username), 1);
    }

    private Authentication authenticate(String username, String password) {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (DisabledException e) {
            throw new ServiceException(ResCodeEnum.RESCODE_UNAUTHORIZED, "USER_DISABLED");
            // throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new ServiceException(ResCodeEnum.RESCODE_UNAUTHORIZED, "INVALID_CREDENTIALS");
            // throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
