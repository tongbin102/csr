package com.project.csr.dao;

import com.project.csr.model.po.QuestionDataPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.CacheNamespace;
import com.project.csr.cache.MybatisRedisCache;

/**
 * <p>
 * 道路救援评分规则表 Mapper 接口
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-15
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface QuestionDataMapper extends BaseMapper<QuestionDataPo> {

}
