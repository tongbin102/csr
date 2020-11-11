package com.project.csr.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.csr.cache.MybatisRedisCache;
import com.project.csr.model.po.RegulationScoreChannelPo;
import com.project.csr.model.vo.RegulationScoreChannelVo;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 细则-分渠道得分关系表 Mapper 接口
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-11
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface RegulationScoreChannelMapper extends BaseMapper<RegulationScoreChannelPo> {

    List<RegulationScoreChannelVo> findVoList(Map<String, Object> params);

}
