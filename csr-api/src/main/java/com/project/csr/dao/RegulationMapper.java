package com.project.csr.dao;

import com.project.csr.model.po.RegulationPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.CacheNamespace;
import com.project.csr.cache.MybatisRedisCache;

/**
 * <p>
 * 细则表 Mapper 接口
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-11
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface RegulationMapper extends BaseMapper<RegulationPo> {

}