package com.project.csr.api;

import com.project.csr.model.vo.ElementScoreVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.csr.service.ElementScoreService;
import com.project.csr.utils.ConvertUtils;
import com.project.csr.model.po.ElementScorePo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 分项成绩统计表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-05
 * @version v1.0
 */
@Slf4j
@Api(tags = {"ElementScoreApi"},value = "分项成绩统计表")
@RestController
@RequestMapping("/elementScoreApi")
public class ElementScoreApi {

    @Autowired
    private ElementScoreService elementScoreService;

    @ApiOperation(value = "查询分页分项成绩统计表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<ElementScorePo> findListByPage(@RequestBody ElementScoreVo elementScoreVo){
        return elementScoreService.findListByPage(elementScoreVo);
    }

    @ApiOperation(value = "根据id查询分项成绩统计表数据")
    @GetMapping(value = "/findById/{id}")
    public ElementScoreVo findById(@PathVariable("id") String id){
        ElementScorePo po = elementScoreService.getById(id);
        return ConvertUtils.convert(po, ElementScoreVo.class);
    }

    @ApiOperation(value = "新增分项成绩统计表数据")
    @PostMapping(value = "/add")
    public ElementScoreVo add(@RequestBody ElementScoreVo elementScoreVo){
        ElementScorePo po = ConvertUtils.convert(elementScoreVo, ElementScorePo.class);
        elementScoreService.save(po);
        return ConvertUtils.convert(po, ElementScoreVo.class);
    }

    @ApiOperation(value = "删除分项成绩统计表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id){
        return elementScoreService.removeById(id);
    }

    @ApiOperation(value = "更新分项成绩统计表数据")
    @PutMapping(value = "/update")
    public ElementScoreVo update(@RequestBody ElementScoreVo elementScoreVo){
        ElementScorePo po = ConvertUtils.convert(elementScoreVo, ElementScorePo.class);
        elementScoreService.updateById(po);
        return ConvertUtils.convert(po, ElementScoreVo.class);
    }

    @ApiOperation("根据ID禁用分项成绩统计表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return elementScoreService.prohibitById(id);
    }
}
