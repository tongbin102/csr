package com.project.csr.api;

import com.project.csr.model.vo.ElementVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.csr.service.ElementService;
import com.project.csr.utils.ConvertUtils;
import com.project.csr.model.po.ElementPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 因子要素表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-05
 */
@Slf4j
@Api(tags = {"ElementApi"}, value = "因子要素表")
@RestController
@RequestMapping("/elementApi")
public class ElementApi {

    @Autowired
    private ElementService elementService;

    @ApiOperation(value = "查询分页因子要素表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<ElementPo> findListByPage(@RequestBody ElementVo elementVo) {
        return elementService.findListByPage(elementVo);
    }

    @ApiOperation(value = "根据id查询因子要素表数据")
    @GetMapping(value = "/findById/{id}")
    public ElementVo findById(@PathVariable("id") String id) {
        ElementPo po = elementService.getById(id);
        return ConvertUtils.convert(po, ElementVo.class);
    }

    @ApiOperation(value = "新增因子要素表数据")
    @PostMapping(value = "/add")
    public ElementVo add(@RequestBody ElementVo elementVo) {
        ElementPo po = ConvertUtils.convert(elementVo, ElementPo.class);
        elementService.save(po);
        return ConvertUtils.convert(po, ElementVo.class);
    }

    @ApiOperation(value = "删除因子要素表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id) {
        return elementService.removeById(id);
    }

    @ApiOperation(value = "更新因子要素表数据")
    @PutMapping(value = "/update")
    public ElementVo update(@RequestBody ElementVo elementVo) {
        ElementPo po = ConvertUtils.convert(elementVo, ElementPo.class);
        elementService.updateById(po);
        return ConvertUtils.convert(po, ElementVo.class);
    }

    @ApiOperation("根据ID禁用因子要素表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return elementService.prohibitById(id);
    }
}
