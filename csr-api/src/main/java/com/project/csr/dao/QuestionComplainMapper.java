package com.project.csr.dao;

import com.project.csr.model.po.QuestionComplainPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.CacheNamespace;
import com.project.csr.cache.MybatisRedisCache;

/**
 * <p>
 * 投诉单表 Mapper 接口
 * </p>
 *
 * @author bin.tong
 * @since 2020-12-01
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface QuestionComplainMapper extends BaseMapper<QuestionComplainPo> {

}
