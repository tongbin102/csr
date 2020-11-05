package com.project.csr.api;

import com.project.csr.model.vo.ElementScoreChannelVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.csr.service.ElementScoreChannelService;
import com.project.csr.utils.ConvertUtils;
import com.project.csr.model.po.ElementScoreChannelPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 分渠道因子要素分布表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-05
 * @version v1.0
 */
@Slf4j
@Api(tags = {"ElementScoreChannelApi"},value = "分渠道因子要素分布表")
@RestController
@RequestMapping("/elementScoreChannelApi")
public class ElementScoreChannelApi {

    @Autowired
    private ElementScoreChannelService elementScoreChannelService;

    @ApiOperation(value = "查询分页分渠道因子要素分布表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<ElementScoreChannelPo> findListByPage(@RequestBody ElementScoreChannelVo elementScoreChannelVo){
        return elementScoreChannelService.findListByPage(elementScoreChannelVo);
    }

    @ApiOperation(value = "根据id查询分渠道因子要素分布表数据")
    @GetMapping(value = "/findById/{id}")
    public ElementScoreChannelVo findById(@PathVariable("id") String id){
        ElementScoreChannelPo po = elementScoreChannelService.getById(id);
        return ConvertUtils.convert(po, ElementScoreChannelVo.class);
    }

    @ApiOperation(value = "新增分渠道因子要素分布表数据")
    @PostMapping(value = "/add")
    public ElementScoreChannelVo add(@RequestBody ElementScoreChannelVo elementScoreChannelVo){
        ElementScoreChannelPo po = ConvertUtils.convert(elementScoreChannelVo, ElementScoreChannelPo.class);
        elementScoreChannelService.save(po);
        return ConvertUtils.convert(po, ElementScoreChannelVo.class);
    }

    @ApiOperation(value = "删除分渠道因子要素分布表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id){
        return elementScoreChannelService.removeById(id);
    }

    @ApiOperation(value = "更新分渠道因子要素分布表数据")
    @PutMapping(value = "/update")
    public ElementScoreChannelVo update(@RequestBody ElementScoreChannelVo elementScoreChannelVo){
        ElementScoreChannelPo po = ConvertUtils.convert(elementScoreChannelVo, ElementScoreChannelPo.class);
        elementScoreChannelService.updateById(po);
        return ConvertUtils.convert(po, ElementScoreChannelVo.class);
    }

    @ApiOperation("根据ID禁用分渠道因子要素分布表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return elementScoreChannelService.prohibitById(id);
    }
}
