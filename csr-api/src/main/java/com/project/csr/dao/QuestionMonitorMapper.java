package com.project.csr.dao;

import com.project.csr.model.po.QuestionMonitorPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.CacheNamespace;
import com.project.csr.cache.MybatisRedisCache;

/**
 * <p>
 * TSS-3 过程监控要求 Mapper 接口
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-05
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface QuestionMonitorMapper extends BaseMapper<QuestionMonitorPo> {

}
