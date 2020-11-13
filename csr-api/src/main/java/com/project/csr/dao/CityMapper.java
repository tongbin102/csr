package com.project.csr.dao;

import com.project.csr.model.po.CityPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.CacheNamespace;
import com.project.csr.cache.MybatisRedisCache;

/**
 * <p>
 * 城市表 Mapper 接口
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-13
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface CityMapper extends BaseMapper<CityPo> {

}
