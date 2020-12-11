package com.project.csr.dao;

import com.project.csr.model.po.ValidatePo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.CacheNamespace;
import com.project.csr.cache.MybatisRedisCache;

/**
 * <p>
 * 验证表 Mapper 接口
 * </p>
 *
 * @author bin.tong
 * @since 2020-12-11
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface ValidateMapper extends BaseMapper<ValidatePo> {

}
