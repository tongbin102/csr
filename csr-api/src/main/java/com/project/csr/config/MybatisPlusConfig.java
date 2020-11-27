package com.project.csr.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.project.csr.handler.AuditMetaObjectHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author: bin.tong
 * @date: 2020/10/29 16:59
 **/
@EnableTransactionManagement
@Configuration
@MapperScan("com.project.csr.dao")
public class MybatisPlusConfig {
    /**
     * mybatis-plus分页插件
     * 新的分页插件，一级缓存和二级缓存遵循mybatis的规则，需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题（该属性会在旧插件移除后一同移除）
     *
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor innerInterceptor = new PaginationInnerInterceptor();
        innerInterceptor.setDbType(DbType.MYSQL);
        innerInterceptor.setOverflow(true);
        interceptor.addInnerInterceptor(innerInterceptor);
        return interceptor;
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setUseDeprecatedExecutor(false);
    }
    @Bean
    @ConditionalOnMissingBean(name = "auditMetaObjectHandler")
    public AuditMetaObjectHandler auditMetaObjectHandler(){
        return new AuditMetaObjectHandler();
    }
}
