package com.project.csr.api;

import com.project.csr.model.vo.SpecificVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.csr.service.SpecificService;
import com.project.csr.utils.ConvertUtils;
import com.project.csr.model.po.SpecificPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 细则 前端控制器
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-09
 * @version v1.0
 */
@Slf4j
@Api(tags = {"SpecificApi"},value = "细则")
@RestController
@RequestMapping("/specificApi")
public class SpecificApi {

    @Autowired
    private SpecificService specificService;

    @ApiOperation(value = "查询分页细则数据")
    @PostMapping(value = "/findListByPage")
    public IPage<SpecificPo> findListByPage(@RequestBody SpecificVo specificVo){
        return specificService.findListByPage(specificVo);
    }

    @ApiOperation(value = "根据id查询细则数据")
    @GetMapping(value = "/findById/{id}")
    public SpecificVo findById(@PathVariable("id") String id){
        SpecificPo po = specificService.getById(id);
        return ConvertUtils.convert(po, SpecificVo.class);
    }

    @ApiOperation(value = "新增细则数据")
    @PostMapping(value = "/add")
    public SpecificVo add(@RequestBody SpecificVo specificVo){
        SpecificPo po = ConvertUtils.convert(specificVo, SpecificPo.class);
        specificService.save(po);
        return ConvertUtils.convert(po, SpecificVo.class);
    }

    @ApiOperation(value = "删除细则数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id){
        return specificService.removeById(id);
    }

    @ApiOperation(value = "更新细则数据")
    @PutMapping(value = "/update")
    public SpecificVo update(@RequestBody SpecificVo specificVo){
        SpecificPo po = ConvertUtils.convert(specificVo, SpecificPo.class);
        specificService.updateById(po);
        return ConvertUtils.convert(po, SpecificVo.class);
    }

    @ApiOperation("根据ID禁用细则数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return specificService.prohibitById(id);
    }
}
