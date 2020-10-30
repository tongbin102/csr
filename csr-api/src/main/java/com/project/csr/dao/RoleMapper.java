package com.project.csr.dao;

import com.project.csr.model.po.RolePo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.CacheNamespace;
import com.project.csr.cache.MybatisRedisCache;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author bin.tong
 * @since 2020-10-30
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface RoleMapper extends BaseMapper<RolePo> {

}
