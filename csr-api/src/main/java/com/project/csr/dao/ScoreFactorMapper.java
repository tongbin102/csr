package com.project.csr.dao;

import com.project.csr.model.po.ScoreFactorPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.CacheNamespace;
import com.project.csr.cache.MybatisRedisCache;

/**
 * <p>
 * 分因子成绩统计表 Mapper 接口
 * </p>
 *
 * @author bin.tong
 * @since 2020-10-30
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface ScoreFactorMapper extends BaseMapper<ScoreFactorPo> {

}
