package com.project.csr.security.filter;

import com.project.csr.constants.SecurityConstant;
import com.project.csr.properties.JwtProperties;
import com.project.csr.security.model.JwtUserDetails;
import com.project.csr.security.service.impl.JwtUserDetailsService;
import com.project.csr.utils.JwtTokenUtils;
import com.project.csr.utils.RedisUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 过滤器，在请求过来的时候，解析请求头中的token，再解析token得到用户信息，再存到SecurityContextHolder中
 *
 * @author bin.tong
 * @since 2020/10/30 17:19
 **/
@Slf4j
@Component
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final String requestHeader = request.getHeader(jwtProperties.getTokenHeader());
        String username = null;
        String tokenBody = null;
        // JWT报文表头的格式是"Bearer token". 去除"Bearer ",直接获取token
        if (requestHeader != null && requestHeader.startsWith(SecurityConstant.TOKEN_PREFIX)) {
            tokenBody = requestHeader.substring(SecurityConstant.TOKEN_PREFIX.length());
            try {
                username = jwtTokenUtils.getUsernameFromToken(tokenBody);
            } catch (IllegalArgumentException e) {
                log.error("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                // token过期
                log.error("JWT Token has expired");
            }
        } else {
            log.warn("JWT Token does not begin with Bearer String");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            String accessToken = redisUtils.getString(jwtTokenUtils.getAccessTokenKey(username));
            if (accessToken.equals(tokenBody)) {
                String rolesToken = redisUtils.getString(jwtTokenUtils.getRoleTokenKey(username));
                List<GrantedAuthority> authorities = null;
                UserDetails userDetails = null;
                // if (null != rolesToken) {
                //     //缓存内有权限
                //     authorities = jwtTokenUtils.getRolesFromToken(rolesToken);
                //     userDetails = new JwtUserDetails(username, false, authorities);
                // } else {
                // 提取数据，并存入缓存
                userDetails = userDetailsService.loadUserByUsername(username);
                authorities = (List<GrantedAuthority>) userDetails.getAuthorities();
                //生成三个token，只有用一个
                String newRoleToken = jwtTokenUtils.generateToken(userDetails).get(jwtTokenUtils.getRoleTokenKey());
                redisUtils.setString(jwtTokenUtils.getRoleTokenKey(username), newRoleToken);
                redisUtils.expire(jwtTokenUtils.getRoleTokenKey(username), jwtProperties.getRolesExpiration());
                // }
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            // UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            // if (jwtTokenUtils.validateToken(tokenBody, userDetails)) {
            //     UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
            //     authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            //     SecurityContextHolder.getContext().setAuthentication(authentication);
            // }
        }
        chain.doFilter(request, response);
    }
    // private boolean isSameTimestampToken(String username, Claims claims){
    //     Long timestamp = user
    // }
}
