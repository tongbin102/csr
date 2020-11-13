package com.project.csr.dao;

import com.project.csr.model.po.ProvincePo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.CacheNamespace;
import com.project.csr.cache.MybatisRedisCache;

/**
 * <p>
 * 省份表 Mapper 接口
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-13
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface ProvinceMapper extends BaseMapper<ProvincePo> {

}
