package com.project.csr.security.api;

import com.project.csr.common.response.BaseResponse;
import com.project.csr.model.po.UserPo;
import com.project.csr.properties.JwtProperties;
import com.project.csr.security.model.JwtUserDetails;
import com.project.csr.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("${jwt.security.path}")
    public BaseResponse<?> createAuthenticationToken(@RequestBody UserPo user) throws Exception{
        log.info("username: "+user.getUsername() + ", password: "+user.getPassword());
        authenticate(user.getUsername(),user.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return BaseResponse.success(token);
    }

    @GetMapping("/token")
    public JwtUserDetails getAuthenticatedUser(HttpServletRequest request){
        String token= request.getHeader(jwtProperties.getTokenHeader()).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        return (JwtUserDetails)userDetailsService.loadUserByUsername(username);
    }

    private void authenticate(String username, String password)throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e){
            throw new Exception("USER_DISABLED",e);
        }catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS",e);
        }
    }
}
