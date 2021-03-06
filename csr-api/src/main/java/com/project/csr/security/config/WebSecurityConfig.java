package com.project.csr.security.config;

import com.project.csr.properties.JwtProperties;
import com.project.csr.security.entrypoint.JwtAuthenticationEntryPoint;
import com.project.csr.security.filter.JwtAuthorizationTokenFilter;
import com.project.csr.security.handle.JwtAuthenticationFailureHandler;
import com.project.csr.security.handle.JwtAuthenticationSuccessHandler;
import com.project.csr.security.provider.JwtAuthenticationProvider;
import com.project.csr.security.service.impl.JwtUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
 * @author: bin.tong
 * @date: 2020/10/30 17:17
 **/
@Slf4j
@Configuration
@EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private JwtAuthorizationTokenFilter jwtAuthorizationTokenFilter;

    @Autowired
    private JwtAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private JwtAuthenticationFailureHandler authenticationFailureHandler;

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
    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    //     // auth.authenticationProvider(jwtAuthenticationProvider);
    //     auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoderBean());
    // }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(jwtAuthenticationProvider);
        // auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoderBean());
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
                .csrf().disable()                      // 禁用 Spring Security 自带的跨域处理
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(jwtProperties.getPath()).permitAll()
                .antMatchers("/token/**").permitAll()
                .antMatchers("/validateApi/sendValidationEmail").permitAll()
                .antMatchers("/validateApi/resetPassword").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").anonymous()
                .anyRequest().authenticated()       // 剩下所有的验证都需要验证.and()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler)
                .permitAll();

        // 定制我们自己的 session 策略：调整为让 Spring Security 不创建和使用 session
        // http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        http.headers().frameOptions().sameOrigin().cacheControl();
        http.addFilterBefore(jwtAuthorizationTokenFilter, UsernamePasswordAuthenticationFilter.class);
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
                        "/**/*.js",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/v2/**",
                        "/swagger-resources/**"

                );
    }


}
