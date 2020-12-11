package com.project.csr.utils;

import com.project.csr.properties.JwtProperties;
import com.project.csr.security.model.JwtUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: bin.tong
 * @date: 2020/10/30 15:53
 **/
@Component
public class JwtTokenUtils implements Serializable {

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_ROLES = "roles";
    private static final String CLAIM_KEY_CREATED = "created";
    private static final String secret = "bcdefgh";
    private static final Long expiration = 1800L;
    private static final String tokenHead = "Bearer ";

    @Autowired
    private JwtProperties jwtProperties;

    private Clock clock = DefaultClock.INSTANCE;

    /**
     * 根据用户信息生成token
     *
     * @param userDetails
     * @return
     */
    // public String generateToken(UserDetails userDetails) {
    //     Map<String, Object> claims = new HashMap<>();
    //     return doGenerateToken(claims, userDetails.getUsername());
    // }
    public Map<String, String> generateToken(UserDetails userDetails) {
        Map<String, String> rst = new HashMap<>();

        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        rst.put(getAccessTokenKey(), generateToken(claims, userDetails.getUsername(), jwtProperties.getAccessExpiration()));
        rst.put(getRefreshTokenKey(), generateToken(claims, userDetails.getUsername(), jwtProperties.getRefreshExpiration()));
        claims.put(CLAIM_KEY_ROLES, userDetails.getAuthorities());
        rst.put(getRoleTokenKey(), generateToken(claims, userDetails.getUsername(), jwtProperties.getRolesExpiration()));

        return rst;
    }

    private String generateToken(Map<String, Object> claims, String subject, Integer seconds) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setExpiration(calculateExpirationDate(seconds))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }

    /**
     * 计算token的过期时间
     *
     * @param seconds
     * @return
     */
    private Date calculateExpirationDate(Integer seconds) {
        return new Date(System.currentTimeMillis() + (int) (seconds * 1000));
    }


    public Boolean validateToken(String token, UserDetails userDetails) {
        JwtUserDetails user = (JwtUserDetails) userDetails;
        final String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername())
                && !isTokenExpired(token)
        );
    }

    public String refreshToken(String refreshToken, String accessToken) {
        if (StringUtils.isEmpty(refreshToken)) {
            return null;
        }
        String username = getUsernameFromToken(refreshToken);
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        // 如果token在30分钟之内刚刷新过，返回原token
        // if (accessToken != null && tokenRefreshJustBefore(accessToken, 30 * 60)) {
        //     return "";
        // } else {
        Map<String, Object> accessClaims = new HashMap<>();
        accessClaims.put(CLAIM_KEY_USERNAME, username);
        accessClaims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(accessClaims, username, jwtProperties.getAccessExpiration());
        // }
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //根据token获得roles
    public List<GrantedAuthority> getRolesFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        List<HashMap> roles = (List<HashMap>) claims.get(CLAIM_KEY_ROLES);
        List<GrantedAuthority> authority = roles.stream().map(i -> new SimpleGrantedAuthority((String) i.get("authority"))).collect(Collectors.toList());
        return authority;
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(clock.now());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    //几个key及生成方式
    public String getAccessTokenKey() {
        return "accessToken";
    }

    public String getAccessTokenKey(String username) {
        return username + ": accessToken";
    }

    public String getRefreshTokenKey() {
        return "refreshToken";
    }

    public String getRefreshTokenKey(String username) {
        return username + ": refreshToken";
    }

    public String getRoleTokenKey() {
        return "roleToken";
    }

    public String getRoleTokenKey(String username) {
        return username + ": roleToken";
    }
}
