package com.project.csr.dao;

import com.project.csr.model.po.SpecificScoreChannelPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.CacheNamespace;
import com.project.csr.cache.MybatisRedisCache;

/**
 * <p>
 * 细则-分渠道得分关系表 Mapper 接口
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-09
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface SpecificScoreChannelMapper extends BaseMapper<SpecificScoreChannelPo> {

}
