package com.project.csr.security.model;

import com.project.csr.constants.CsrConstant;
import com.project.csr.constants.DictionaryType;
import com.project.csr.model.vo.UserVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;

/**
 * JwtUserDetails
 *
 * @author bin.tong
 * @since 2020/10/30 17:25
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class JwtUserDetails extends UserVo implements UserDetails {

    private Collection<? extends GrantedAuthority> authorities;

    public JwtUserDetails() {
    }

    public JwtUserDetails(String userName, Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
        this.setUsername(userName);
        String encode = new BCryptPasswordEncoder().encode(CsrConstant.DEFAULT_RAW_PASSWORD);
        this.setPassword(encode);
        this.setAuthorities(authorities);
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    /**
     * 添加用户拥有的权限和角色
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }


    /**
     * 账户是否过期
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 是否禁用
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否过期
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否启用
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return DictionaryType.VALID_IND_VALID.equals(this.getValidInd());
    }
}
