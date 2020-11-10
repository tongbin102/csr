package com.project.csr.dao;

import com.project.csr.model.po.QuestionAssistancePo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.CacheNamespace;
import com.project.csr.cache.MybatisRedisCache;

/**
 * <p>
 * 服务助手-题目明细表 Mapper 接口
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-10
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface QuestionAssistanceMapper extends BaseMapper<QuestionAssistancePo> {

}
