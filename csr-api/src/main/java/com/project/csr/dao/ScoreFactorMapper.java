package com.project.csr.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.csr.cache.MybatisRedisCache;
import com.project.csr.model.po.ScoreFactorPo;
import com.project.csr.model.vo.ScoreFactorVo;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 分因子成绩统计表 Mapper 接口
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-05
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface ScoreFactorMapper extends BaseMapper<ScoreFactorPo> {

    List<ScoreFactorVo> findVoList(Map<String, Object> params);

}
