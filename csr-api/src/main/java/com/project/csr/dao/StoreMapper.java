package com.project.csr.dao;

import com.project.csr.model.po.StorePo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.CacheNamespace;
import com.project.csr.cache.MybatisRedisCache;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-06
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface StoreMapper extends BaseMapper<StorePo> {

}
