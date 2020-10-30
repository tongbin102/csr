package com.project.csr.api;

import com.project.csr.model.vo.ScopeVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.csr.service.ScopeService;
import com.project.csr.utils.ConvertUtils;
import com.project.csr.model.po.ScopePo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 范围表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @since 2020-10-30
 * @version v1.0
 */
@Slf4j
@Api(tags = {"ScopeApi"},value = "范围表")
@RestController
@RequestMapping("/scopeApi")
public class ScopeApi {

    @Autowired
    private ScopeService scopeService;

    @ApiOperation(value = "查询分页范围表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<ScopePo> findListByPage(@RequestBody ScopeVo scopeVo){
        return scopeService.findListByPage(scopeVo);
    }

    @ApiOperation(value = "根据id查询范围表数据")
    @GetMapping(value = "/findById/{id}")
    public ScopeVo findById(@PathVariable("id") String id){
        ScopePo po = scopeService.getById(id);
        return ConvertUtils.convert(po, ScopeVo.class);
    }

    @ApiOperation(value = "新增范围表数据")
    @PostMapping(value = "/add")
    public ScopeVo add(@RequestBody ScopeVo scopeVo){
        ScopePo po = ConvertUtils.convert(scopeVo, ScopePo.class);
        scopeService.save(po);
        return ConvertUtils.convert(po, ScopeVo.class);
    }

    @ApiOperation(value = "删除范围表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id){
        return scopeService.removeById(id);
    }

    @ApiOperation(value = "更新范围表数据")
    @PutMapping(value = "/update")
    public ScopeVo update(@RequestBody ScopeVo scopeVo){
        ScopePo po = ConvertUtils.convert(scopeVo, ScopePo.class);
        scopeService.updateById(po);
        return ConvertUtils.convert(po, ScopeVo.class);
    }

    @ApiOperation("根据ID禁用范围表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return scopeService.prohibitById(id);
    }
}
