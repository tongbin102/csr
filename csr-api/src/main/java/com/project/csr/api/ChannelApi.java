package com.project.csr.api;

import com.project.csr.model.vo.ChannelVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.csr.service.ChannelService;
import com.project.csr.utils.ConvertUtils;
import com.project.csr.model.po.ChannelPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 渠道表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @since 2020-10-30
 * @version v1.0
 */
@Slf4j
@Api(tags = {"ChannelApi"},value = "渠道表")
@RestController
@RequestMapping("/channelApi")
public class ChannelApi {

    @Autowired
    private ChannelService channelService;

    @ApiOperation(value = "查询分页渠道表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<ChannelPo> findListByPage(@RequestBody ChannelVo channelVo){
        return channelService.findListByPage(channelVo);
    }

    @ApiOperation(value = "根据id查询渠道表数据")
    @GetMapping(value = "/findById/{id}")
    public ChannelVo findById(@PathVariable("id") String id){
        ChannelPo po = channelService.getById(id);
        return ConvertUtils.convert(po, ChannelVo.class);
    }

    @ApiOperation(value = "新增渠道表数据")
    @PostMapping(value = "/add")
    public ChannelVo add(@RequestBody ChannelVo channelVo){
        ChannelPo po = ConvertUtils.convert(channelVo, ChannelPo.class);
        channelService.save(po);
        return ConvertUtils.convert(po, ChannelVo.class);
    }

    @ApiOperation(value = "删除渠道表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id){
        return channelService.removeById(id);
    }

    @ApiOperation(value = "更新渠道表数据")
    @PutMapping(value = "/update")
    public ChannelVo update(@RequestBody ChannelVo channelVo){
        ChannelPo po = ConvertUtils.convert(channelVo, ChannelPo.class);
        channelService.updateById(po);
        return ConvertUtils.convert(po, ChannelVo.class);
    }

    @ApiOperation("根据ID禁用渠道表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return channelService.prohibitById(id);
    }
}
