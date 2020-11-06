package com.project.csr.api;

import com.project.csr.model.vo.StoreVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.csr.service.StoreService;
import com.project.csr.utils.ConvertUtils;
import com.project.csr.model.po.StorePo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-06
 */
@Slf4j
@Api(tags = {"StoreApi"}, value = "")
@RestController
@RequestMapping("/storeApi")
public class StoreApi {

    @Autowired
    private StoreService storeService;

    @ApiOperation(value = "查询分页数据")
    @PostMapping(value = "/findListByPage")
    public IPage<StorePo> findListByPage(@RequestBody StoreVo storeVo) {
        return storeService.findListByPage(storeVo);
    }

    @ApiOperation(value = "根据id查询数据")
    @GetMapping(value = "/findById/{id}")
    public StoreVo findById(@PathVariable("id") String id) {
        StorePo po = storeService.getById(id);
        return ConvertUtils.convert(po, StoreVo.class);
    }

    @ApiOperation(value = "新增数据")
    @PostMapping(value = "/add")
    public StoreVo add(@RequestBody StoreVo storeVo) {
        StorePo po = ConvertUtils.convert(storeVo, StorePo.class);
        storeService.save(po);
        return ConvertUtils.convert(po, StoreVo.class);
    }

    @ApiOperation(value = "删除数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id) {
        return storeService.removeById(id);
    }

    @ApiOperation(value = "更新数据")
    @PutMapping(value = "/update")
    public StoreVo update(@RequestBody StoreVo storeVo) {
        StorePo po = ConvertUtils.convert(storeVo, StorePo.class);
        storeService.updateById(po);
        return ConvertUtils.convert(po, StoreVo.class);
    }

    @ApiOperation("根据ID禁用数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return storeService.prohibitById(id);
    }

    @ApiOperation("根据ID禁用数据")
    @GetMapping("/findByParentId/{parent_id}")
    public List<StorePo> findByParentId(@PathVariable("parent_id") Integer parentId) {
        return storeService.findByParentId(parentId);
    }
}
