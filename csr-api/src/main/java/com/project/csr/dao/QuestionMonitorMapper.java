package com.project.csr.dao;

import com.project.csr.model.po.QuestionMonitorPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.CacheNamespace;
import com.project.csr.cache.MybatisRedisCache;

/**
 * <p>
 * 过程监控-题目明细表 Mapper 接口
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-10
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface QuestionMonitorMapper extends BaseMapper<QuestionMonitorPo> {

}
