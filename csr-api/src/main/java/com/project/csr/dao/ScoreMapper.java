package com.project.csr.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.csr.cache.MybatisRedisCache;
import com.project.csr.model.po.ScorePo;
import com.project.csr.model.vo.ScoreVo;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 成绩排名表 Mapper 接口
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-05
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface ScoreMapper extends BaseMapper<ScorePo> {

    List<ScoreVo> findVoList(Map<String, Object> params);

    List<ScoreVo> findNationalVoList(Map<String, Object> params);

    List<ScoreVo> findRegionVoList(Map<String, Object> params);

    List<ScoreVo> findProvinceVoList(Map<String, Object> params);

    List<ScoreVo> findCityVoList(Map<String, Object> params);

    List<ScoreVo> findStoreVoList(Map<String, Object> params);

}
