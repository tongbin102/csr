package com.project.csr.api;

import com.project.csr.model.vo.RegionVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.csr.service.RegionService;
import com.project.csr.utils.ConvertUtils;
import com.project.csr.model.po.RegionPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 大区表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-13
 * @version v1.0
 */
@Slf4j
@Api(tags = {"RegionApi"},value = "大区表")
@RestController
@RequestMapping("/regionApi")
public class RegionApi {

    @Autowired
    private RegionService regionService;

    @ApiOperation(value = "查询分页大区表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<RegionPo> findListByPage(@RequestBody RegionVo regionVo){
        return regionService.findListByPage(regionVo);
    }

    @ApiOperation(value = "根据id查询大区表数据")
    @GetMapping(value = "/findById/{id}")
    public RegionVo findById(@PathVariable("id") String id){
        RegionPo po = regionService.getById(id);
        return ConvertUtils.convert(po, RegionVo.class);
    }

    @ApiOperation(value = "新增大区表数据")
    @PostMapping(value = "/add")
    public RegionVo add(@RequestBody RegionVo regionVo){
        RegionPo po = ConvertUtils.convert(regionVo, RegionPo.class);
        regionService.save(po);
        return ConvertUtils.convert(po, RegionVo.class);
    }

    @ApiOperation(value = "删除大区表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id){
        return regionService.removeById(id);
    }

    @ApiOperation(value = "更新大区表数据")
    @PutMapping(value = "/update")
    public RegionVo update(@RequestBody RegionVo regionVo){
        RegionPo po = ConvertUtils.convert(regionVo, RegionPo.class);
        regionService.updateById(po);
        return ConvertUtils.convert(po, RegionVo.class);
    }

    @ApiOperation("根据ID禁用大区表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return regionService.prohibitById(id);
    }
}
