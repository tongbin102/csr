package com.project.csr.security.service.impl;

import com.project.csr.model.po.UserPo;
import com.project.csr.security.model.JwtUserDetails;
import com.project.csr.service.UserService;
import com.project.csr.utils.ConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
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
 * @author bin.tong
 * @since 2020/10/30 17:18
 **/
@Slf4j
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    /**
     * 获取用户信息,然后交给spring去校验权限
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("JwtUserDetailsService:" + username);

        UserPo userPo = userService.findByUsername(username);
        if (null == userPo) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        JwtUserDetails userDetails = ConvertUtils.convert(userPo, JwtUserDetails.class);

        List<GrantedAuthority> authorityList = new ArrayList<>();
        // 用于添加用户的权限，把用户权限添加到authorities中
        authorityList.add(new SimpleGrantedAuthority("ROLE_" + userDetails.getRoleName()));
        userDetails.setAuthorities(authorityList);
        return userDetails;
        // return new JwtUserDetails(username, authorityList);
    }
}
