package com.project.csr.dao;

import com.project.csr.model.po.SpecificPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.CacheNamespace;
import com.project.csr.cache.MybatisRedisCache;

/**
 * <p>
 * 细则 Mapper 接口
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-09
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface SpecificMapper extends BaseMapper<SpecificPo> {

}
