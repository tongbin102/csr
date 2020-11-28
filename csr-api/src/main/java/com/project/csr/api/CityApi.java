package com.project.csr.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.project.csr.model.po.CityPo;
import com.project.csr.model.vo.CityVo;
import com.project.csr.service.CityService;
import com.project.csr.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 城市表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-13
 */
@Slf4j
@Api(tags = {"CityApi"}, value = "城市表")
@RestController
@RequestMapping("/cityApi")
public class CityApi {

    @Autowired
    private CityService cityService;

    @ApiOperation(value = "查询分页城市表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<CityPo> findListByPage(@RequestBody CityVo cityVo) {
        return cityService.findListByPage(cityVo);
    }

    @ApiOperation(value = "根据id查询城市表数据")
    @GetMapping(value = "/findById/{id}")
    public CityVo findById(@PathVariable("id") String id) {
        CityPo po = cityService.getById(id);
        return ConvertUtils.convert(po, CityVo.class);
    }

    @ApiOperation(value = "新增城市表数据")
    @PostMapping(value = "/add")
    public CityVo add(@RequestBody CityVo cityVo) {
        CityPo po = ConvertUtils.convert(cityVo, CityPo.class);
        cityService.save(po);
        return ConvertUtils.convert(po, CityVo.class);
    }

    @ApiOperation(value = "删除城市表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id) {
        return cityService.removeById(id);
    }

    @ApiOperation(value = "更新城市表数据")
    @PutMapping(value = "/update")
    public CityVo update(@RequestBody CityVo cityVo) {
        CityPo po = ConvertUtils.convert(cityVo, CityPo.class);
        cityService.updateById(po);
        return ConvertUtils.convert(po, CityVo.class);
    }

    @ApiOperation("根据ID禁用城市表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return cityService.prohibitById(id);
    }

    @ApiOperation("根据code查询城市表数据")
    @GetMapping("/findByCode")
    public CityVo findByCode(@RequestParam("code") String code) {
        LambdaQueryWrapper<CityPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(CityPo::getCode, code);
        return ConvertUtils.convert(cityService.getOne(wrapper), CityVo.class);
    }
}
