package com.project.csr.dao;

import com.project.csr.model.po.QuestionAssistancePo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.CacheNamespace;
import com.project.csr.cache.MybatisRedisCache;

/**
 * <p>
 * TSS-4 服务助手及道路救援 Mapper 接口
 * </p>
 *
 * @author bin.tong
 * @since 2020-10-30
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface QuestionAssistanceMapper extends BaseMapper<QuestionAssistancePo> {

}
