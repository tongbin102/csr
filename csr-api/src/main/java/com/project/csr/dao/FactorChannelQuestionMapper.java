package com.project.csr.dao;

import com.project.csr.model.po.FactorChannelQuestionPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.CacheNamespace;
import com.project.csr.cache.MybatisRedisCache;

/**
 * <p>
 * TSS-1 因子要素映射配置表 Mapper 接口
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-05
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface FactorChannelQuestionMapper extends BaseMapper<FactorChannelQuestionPo> {

}
