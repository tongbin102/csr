package com.project.csr.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.csr.cache.MybatisRedisCache;
import com.project.csr.model.po.RegulationScorePo;
import com.project.csr.model.vo.RegulationScoreVo;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 细则-分数关系表 Mapper 接口
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-11
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface RegulationScoreMapper extends BaseMapper<RegulationScorePo> {

    List<RegulationScoreVo> findVoList(Map<String, Object> params);

}
