package com.project.csr.dao;

import com.project.csr.model.po.ScoreChannelPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.CacheNamespace;
import com.project.csr.cache.MybatisRedisCache;

/**
 * <p>
 * 分渠道成绩统计表 Mapper 接口
 * </p>
 *
 * @author bin.tong
 * @since 2020-10-30
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface ScoreChannelMapper extends BaseMapper<ScoreChannelPo> {

}
