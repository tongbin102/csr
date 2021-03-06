
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.ChannelPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.vo.ChannelVo;

import java.util.List;

/**
 * <p>
 * 渠道表 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-05
 */
public interface ChannelService extends IService<ChannelPo> {

    /**
     * 分页查询
     *
     * @param channelVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.ChannelPo>
     * @author bin.tong
     * @since 2020-11-05
     */
    IPage<ChannelPo> findListByPage(ChannelVo channelVo);

    /**
     * 根据ID禁用数据
     *
     * @param id 主键id
     * @return boolean
     * @author bin.tong
     * @since 2020-11-05
     */
    boolean prohibitById(String id);

    List<ChannelPo> findListByCtype(Integer ctype);
}