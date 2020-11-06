package com.project.csr.utils;

import com.project.csr.common.exceptions.NullBeanException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 以静态变量保存Spring ApplicationContext, 可在任何代码任何地方任何时候中取出ApplicaitonContext.
 *
 * @author: bin.tong
 * @date: 2020/7/9 11:25
 **/
@Component
public class SpringContextUtils {
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量
     */
    public static void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }

    /**
     * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量
     */
    public static ApplicationContext getApplicationContext() throws BeansException {
        return applicationContext;
    }

    /**
     * 检查applicationContext是否注入
     * <p>
     * <br>
     * added by liuyongtao<br>
     *
     * @return
     */
    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义ContextUtils");
        }
    }

    /**
     * 通过name和clazz返回指定的Bean
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        checkApplicationContext();
        T bean = applicationContext.getBean(name, clazz);
        if (bean == null) {
            throw new NullBeanException(name);
        }
        return bean;
    }

    /**
     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
     * <p>
     * <br>
     * added by liuyongtao<br>
     *
     * @param name
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        checkApplicationContext();
        Object bean = applicationContext.getBean(name);
        if (bean == null) {
            throw new NullBeanException(name);
        }
        return (T) bean;
    }

    /**
     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
     * <p>
     * <br>
     * added by liuyongtao<br>
     *
     * @param clazz
     * @return
     * @date 2017年4月18日 下午4:04:09
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz) throws Exception {
        checkApplicationContext();
        Map<String, T> bean = applicationContext.getBeansOfType(clazz);
        if (bean == null) {
            throw new NullBeanException(clazz);
        }
        return (T) bean;
    }

    /**
     * 获取指定资源
     *
     * @param name
     * @return org.springframework.core.io.Resource
     */
    public static Resource getResource(String name) {
        checkApplicationContext();
        return applicationContext.getResource(name);
    }

}
