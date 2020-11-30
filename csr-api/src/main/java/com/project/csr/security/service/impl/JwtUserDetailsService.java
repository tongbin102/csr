package com.project.csr.security.service.impl;

import com.project.csr.model.po.UserPo;
import com.project.csr.security.model.JwtUserDetails;
import com.project.csr.service.UserService;
import com.project.csr.utils.ConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: bin.tong
 * @date: 2020/10/30 17:18
 **/
@Slf4j
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("JwtUserDetailsService:" + username);
        UserPo user = userService.findByUsername(username);
        JwtUserDetails userDetails = ConvertUtils.convert(user, JwtUserDetails.class);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_" + userDetails.getRoleName()));
        return userDetails.setAuthorities(authorityList);
        // return new JwtUserDetails(username, authorityList);
    }
}
