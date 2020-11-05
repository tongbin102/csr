package com.project.csr.api;

import com.project.csr.model.vo.QuestionMonitorVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.csr.service.QuestionMonitorService;
import com.project.csr.utils.ConvertUtils;
import com.project.csr.model.po.QuestionMonitorPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * TSS-3 过程监控要求 前端控制器
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-05
 * @version v1.0
 */
@Slf4j
@Api(tags = {"QuestionMonitorApi"},value = "TSS-3 过程监控要求")
@RestController
@RequestMapping("/questionMonitorApi")
public class QuestionMonitorApi {

    @Autowired
    private QuestionMonitorService questionMonitorService;

    @ApiOperation(value = "查询分页TSS-3 过程监控要求数据")
    @PostMapping(value = "/findListByPage")
    public IPage<QuestionMonitorPo> findListByPage(@RequestBody QuestionMonitorVo questionMonitorVo){
        return questionMonitorService.findListByPage(questionMonitorVo);
    }

    @ApiOperation(value = "根据id查询TSS-3 过程监控要求数据")
    @GetMapping(value = "/findById/{id}")
    public QuestionMonitorVo findById(@PathVariable("id") String id){
        QuestionMonitorPo po = questionMonitorService.getById(id);
        return ConvertUtils.convert(po, QuestionMonitorVo.class);
    }

    @ApiOperation(value = "新增TSS-3 过程监控要求数据")
    @PostMapping(value = "/add")
    public QuestionMonitorVo add(@RequestBody QuestionMonitorVo questionMonitorVo){
        QuestionMonitorPo po = ConvertUtils.convert(questionMonitorVo, QuestionMonitorPo.class);
        questionMonitorService.save(po);
        return ConvertUtils.convert(po, QuestionMonitorVo.class);
    }

    @ApiOperation(value = "删除TSS-3 过程监控要求数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id){
        return questionMonitorService.removeById(id);
    }

    @ApiOperation(value = "更新TSS-3 过程监控要求数据")
    @PutMapping(value = "/update")
    public QuestionMonitorVo update(@RequestBody QuestionMonitorVo questionMonitorVo){
        QuestionMonitorPo po = ConvertUtils.convert(questionMonitorVo, QuestionMonitorPo.class);
        questionMonitorService.updateById(po);
        return ConvertUtils.convert(po, QuestionMonitorVo.class);
    }

    @ApiOperation("根据ID禁用TSS-3 过程监控要求数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return questionMonitorService.prohibitById(id);
    }
}
