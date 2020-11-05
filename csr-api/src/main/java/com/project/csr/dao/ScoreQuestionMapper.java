package com.project.csr.dao;

import com.project.csr.model.po.ScoreQuestionPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.CacheNamespace;
import com.project.csr.cache.MybatisRedisCache;

/**
 * <p>
 * 分项成绩统计表 Mapper 接口
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-05
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface ScoreQuestionMapper extends BaseMapper<ScoreQuestionPo> {

}
