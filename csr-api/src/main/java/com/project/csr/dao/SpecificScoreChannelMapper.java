package com.project.csr.dao;

import com.project.csr.model.po.SpecificScoreChannelPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.project.csr.model.vo.SpecificScoreChannelVo;
import com.project.csr.model.vo.SpecificScoreVo;
import org.apache.ibatis.annotations.CacheNamespace;
import com.project.csr.cache.MybatisRedisCache;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 细则-分渠道得分关系表 Mapper 接口
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-09
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface SpecificScoreChannelMapper extends BaseMapper<SpecificScoreChannelPo> {

    List<SpecificScoreChannelVo> findVoList(Map<String, Object> params);

}
