package com.project.csr.security.config;

import com.project.csr.properties.JwtProperties;
import com.project.csr.security.entrypoint.JwtAuthenticationEntryPoint;
import com.project.csr.security.filter.JwtAuthorizationTokenFilter;
import com.project.csr.security.handle.JwtAccessDeniedHandler;
import com.project.csr.security.handle.JwtAuthenticationFailureHandler;
import com.project.csr.security.handle.JwtAuthenticationSuccessHandler;
import com.project.csr.security.service.impl.JwtUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * WebSecurityConfig
 *
 * @author bin.tong
 * @since 2020/10/30 17:17
 **/
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // 控制@Secured权限注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtAuthorizationTokenFilter jwtAuthorizationTokenFilter;

    @Autowired
    private JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler;

    @Autowired
    private JwtAuthenticationFailureHandler jwtAuthenticationFailureHandler;

    @Autowired
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 该方法定义认证用户信息获取的来源、密码校验的规则
     *
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoderBean());
    }

    /**
     * 拦截在这配
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * antMatchers: ant的通配符规则
         * ?	匹配任何单字符
         * *	匹配0或者任意数量的字符，不包含"/"
         * **	匹配0或者更多的目录，包含"/"
         */
        log.info("authenticationPath: " + jwtProperties.getPath());
        http
                .headers()
                .frameOptions().disable();
        http
                // 登陆后，访问没有权限的处理类
                .exceptionHandling().accessDeniedHandler(jwtAccessDeniedHandler)
                //匿名访问，没有权限的处理类
                .authenticationEntryPoint(jwtAuthenticationEntryPoint);
        http
                .addFilterBefore(jwtAuthorizationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http
                .authorizeRequests()
                // 这里表示"/any"和"/ignore"不需要权限校验
                .antMatchers().permitAll()
                .anyRequest().authenticated()
                // 其余所有请求都需要校验认证
                .and()
                // 配置登录，检查到用户未登录时跳转的url地址，登录放行
                .formLogin()
                // 需要跟前端表单的接口地址一直
                .loginProcessingUrl(jwtProperties.getPath())
                .successHandler(jwtAuthenticationSuccessHandler)
                .failureHandler(jwtAuthenticationFailureHandler)
                .permitAll()

                // 配置取消session管理，由Jwt来获取用户状态，否则即使token无效，也会有session信息，依旧判断用户为登录状态
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // 配置登出，登出房型
                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll()

                // 禁用 Spring Security 自带的跨域处理
                .and()
                .csrf().disable();

        // 定制我们自己的 session 策略：调整为让 Spring Security 不创建和使用 session
        // http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        // http.headers().frameOptions().sameOrigin().cacheControl();
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // AuthenticationTokenFilter will ignore the below paths
        web
                .ignoring()
                .antMatchers(HttpMethod.POST, jwtProperties.getPath())

                // allow anonymous resource requests
                .and()
                .ignoring()
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                );
    }

}
