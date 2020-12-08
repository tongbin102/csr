package com.project.csr.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author bin.tong
 * @since 2020/12/8 16:01
 **/
@Slf4j
@Component
public class RedisUtils {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 指定缓存的失效时间
     *
     * @param key      键
     * @param time     失效时间
     * @param timeUnit 时间单位
     * @return true成功 false失败
     */
    public boolean expire(final String key, final long time, final TimeUnit timeUnit) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, timeUnit);
            }
            return true;
        } catch (final Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean setString(final String key, final String value) {
        Assert.notNull(key, "key is not empty");
        Assert.notNull(value, "value is not empty");

        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (final Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 普通缓存放入并设置失效时间
     *
     * @param key      键
     * @param value    值
     * @param time     失效时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @param timeUnit 时间单位
     * @return true成功 false 失败
     */
    public boolean setString(final String key, final String value, final long time, final TimeUnit timeUnit) {
        Assert.notNull(key, "key is not empty");
        Assert.notNull(value, "value is not empty");
        Assert.notNull(timeUnit, "timeUnit is not empty");

        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, timeUnit);
            } else {
                setString(key, value);
            }
            return true;
        } catch (final Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 插入object类型的数据（转json）
     *
     * @param key   键
     * @param value 值
     * @param <T>   值类型
     * @return true成功 false 失败
     */
    public <T> boolean setObj(String key, T value) {
        Assert.notNull(key, "key is not empty");
        Assert.notNull(value, "value is not empty");

        try {
            redisTemplate.opsForValue().set(key, JSONObject.toJSONString(value));
            return true;
        } catch (final Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 插入object类型的数据，设置失效时间（转json）
     *
     * @param key      键
     * @param value    值
     * @param time     失效时间
     * @param timeUnit 时间单位
     * @param <T>      值类型
     * @return true成功 false 失败
     */
    public <T> boolean setObj(String key, T value, long time, TimeUnit timeUnit) {
        Assert.notNull(key, "key is not empty");
        Assert.notNull(value, "value is not empty");
        Assert.notNull(timeUnit, "timeUnit is not empty");

        try {
            redisTemplate.opsForValue().set(key, JSONObject.toJSONString(value), time, timeUnit);
            return true;
        } catch (final Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return true成功 false 失败
     */
    public String getString(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取object类型的数据（转对象）
     *
     * @param key   键
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getObj(String key, Class<T> clazz) {
        return JSONObject.parseObject(redisTemplate.opsForValue().get(key), clazz);
    }

    /**
     * 获取List类型的数据（转List对象）
     *
     * @param key   键
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> getArray(String key, Class<T> clazz) {
        return JSONObject.parseArray(redisTemplate.opsForValue().get(key), clazz);
    }
}
