package com.project.csr.api;

import com.project.csr.model.vo.ProvinceVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.csr.service.ProvinceService;
import com.project.csr.utils.ConvertUtils;
import com.project.csr.model.po.ProvincePo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 省份表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-13
 * @version v1.0
 */
@Slf4j
@Api(tags = {"ProvinceApi"},value = "省份表")
@RestController
@RequestMapping("/provinceApi")
public class ProvinceApi {

    @Autowired
    private ProvinceService provinceService;

    @ApiOperation(value = "查询分页省份表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<ProvincePo> findListByPage(@RequestBody ProvinceVo provinceVo){
        return provinceService.findListByPage(provinceVo);
    }

    @ApiOperation(value = "根据id查询省份表数据")
    @GetMapping(value = "/findById/{id}")
    public ProvinceVo findById(@PathVariable("id") String id){
        ProvincePo po = provinceService.getById(id);
        return ConvertUtils.convert(po, ProvinceVo.class);
    }

    @ApiOperation(value = "新增省份表数据")
    @PostMapping(value = "/add")
    public ProvinceVo add(@RequestBody ProvinceVo provinceVo){
        ProvincePo po = ConvertUtils.convert(provinceVo, ProvincePo.class);
        provinceService.save(po);
        return ConvertUtils.convert(po, ProvinceVo.class);
    }

    @ApiOperation(value = "删除省份表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id){
        return provinceService.removeById(id);
    }

    @ApiOperation(value = "更新省份表数据")
    @PutMapping(value = "/update")
    public ProvinceVo update(@RequestBody ProvinceVo provinceVo){
        ProvincePo po = ConvertUtils.convert(provinceVo, ProvincePo.class);
        provinceService.updateById(po);
        return ConvertUtils.convert(po, ProvinceVo.class);
    }

    @ApiOperation("根据ID禁用省份表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return provinceService.prohibitById(id);
    }
}
