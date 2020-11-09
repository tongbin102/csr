package com.project.csr.api;

import com.project.csr.model.vo.SpecificScoreChannelVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.csr.service.SpecificScoreChannelService;
import com.project.csr.utils.ConvertUtils;
import com.project.csr.model.po.SpecificScoreChannelPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 细则-分渠道得分关系表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-09
 * @version v1.0
 */
@Slf4j
@Api(tags = {"SpecificScoreChannelApi"},value = "细则-分渠道得分关系表")
@RestController
@RequestMapping("/specificScoreChannelApi")
public class SpecificScoreChannelApi {

    @Autowired
    private SpecificScoreChannelService specificScoreChannelService;

    @ApiOperation(value = "查询分页细则-分渠道得分关系表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<SpecificScoreChannelPo> findListByPage(@RequestBody SpecificScoreChannelVo specificScoreChannelVo){
        return specificScoreChannelService.findListByPage(specificScoreChannelVo);
    }

    @ApiOperation(value = "根据id查询细则-分渠道得分关系表数据")
    @GetMapping(value = "/findById/{id}")
    public SpecificScoreChannelVo findById(@PathVariable("id") String id){
        SpecificScoreChannelPo po = specificScoreChannelService.getById(id);
        return ConvertUtils.convert(po, SpecificScoreChannelVo.class);
    }

    @ApiOperation(value = "新增细则-分渠道得分关系表数据")
    @PostMapping(value = "/add")
    public SpecificScoreChannelVo add(@RequestBody SpecificScoreChannelVo specificScoreChannelVo){
        SpecificScoreChannelPo po = ConvertUtils.convert(specificScoreChannelVo, SpecificScoreChannelPo.class);
        specificScoreChannelService.save(po);
        return ConvertUtils.convert(po, SpecificScoreChannelVo.class);
    }

    @ApiOperation(value = "删除细则-分渠道得分关系表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id){
        return specificScoreChannelService.removeById(id);
    }

    @ApiOperation(value = "更新细则-分渠道得分关系表数据")
    @PutMapping(value = "/update")
    public SpecificScoreChannelVo update(@RequestBody SpecificScoreChannelVo specificScoreChannelVo){
        SpecificScoreChannelPo po = ConvertUtils.convert(specificScoreChannelVo, SpecificScoreChannelPo.class);
        specificScoreChannelService.updateById(po);
        return ConvertUtils.convert(po, SpecificScoreChannelVo.class);
    }

    @ApiOperation("根据ID禁用细则-分渠道得分关系表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return specificScoreChannelService.prohibitById(id);
    }
}
