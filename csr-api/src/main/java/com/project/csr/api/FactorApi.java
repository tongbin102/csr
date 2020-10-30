package com.project.csr.api;

import com.project.csr.model.vo.FactorVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.csr.service.FactorService;
import com.project.csr.utils.ConvertUtils;
import com.project.csr.model.po.FactorPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 因子表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @since 2020-10-30
 * @version v1.0
 */
@Slf4j
@Api(tags = {"FactorApi"},value = "因子表")
@RestController
@RequestMapping("/factorApi")
public class FactorApi {

    @Autowired
    private FactorService factorService;

    @ApiOperation(value = "查询分页因子表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<FactorPo> findListByPage(@RequestBody FactorVo factorVo){
        return factorService.findListByPage(factorVo);
    }

    @ApiOperation(value = "根据id查询因子表数据")
    @GetMapping(value = "/findById/{id}")
    public FactorVo findById(@PathVariable("id") String id){
        FactorPo po = factorService.getById(id);
        return ConvertUtils.convert(po, FactorVo.class);
    }

    @ApiOperation(value = "新增因子表数据")
    @PostMapping(value = "/add")
    public FactorVo add(@RequestBody FactorVo factorVo){
        FactorPo po = ConvertUtils.convert(factorVo, FactorPo.class);
        factorService.save(po);
        return ConvertUtils.convert(po, FactorVo.class);
    }

    @ApiOperation(value = "删除因子表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id){
        return factorService.removeById(id);
    }

    @ApiOperation(value = "更新因子表数据")
    @PutMapping(value = "/update")
    public FactorVo update(@RequestBody FactorVo factorVo){
        FactorPo po = ConvertUtils.convert(factorVo, FactorPo.class);
        factorService.updateById(po);
        return ConvertUtils.convert(po, FactorVo.class);
    }

    @ApiOperation("根据ID禁用因子表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return factorService.prohibitById(id);
    }
}
