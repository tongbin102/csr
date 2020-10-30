package com.project.csr.api;

import com.project.csr.model.vo.QuestionAssistanceVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.csr.service.QuestionAssistanceService;
import com.project.csr.utils.ConvertUtils;
import com.project.csr.model.po.QuestionAssistancePo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * TSS-4 服务助手及道路救援 前端控制器
 * </p>
 *
 * @author bin.tong
 * @since 2020-10-30
 * @version v1.0
 */
@Slf4j
@Api(tags = {"QuestionAssistanceApi"},value = "TSS-4 服务助手及道路救援")
@RestController
@RequestMapping("/questionAssistanceApi")
public class QuestionAssistanceApi {

    @Autowired
    private QuestionAssistanceService questionAssistanceService;

    @ApiOperation(value = "查询分页TSS-4 服务助手及道路救援数据")
    @PostMapping(value = "/findListByPage")
    public IPage<QuestionAssistancePo> findListByPage(@RequestBody QuestionAssistanceVo questionAssistanceVo){
        return questionAssistanceService.findListByPage(questionAssistanceVo);
    }

    @ApiOperation(value = "根据id查询TSS-4 服务助手及道路救援数据")
    @GetMapping(value = "/findById/{id}")
    public QuestionAssistanceVo findById(@PathVariable("id") String id){
        QuestionAssistancePo po = questionAssistanceService.getById(id);
        return ConvertUtils.convert(po, QuestionAssistanceVo.class);
    }

    @ApiOperation(value = "新增TSS-4 服务助手及道路救援数据")
    @PostMapping(value = "/add")
    public QuestionAssistanceVo add(@RequestBody QuestionAssistanceVo questionAssistanceVo){
        QuestionAssistancePo po = ConvertUtils.convert(questionAssistanceVo, QuestionAssistancePo.class);
        questionAssistanceService.save(po);
        return ConvertUtils.convert(po, QuestionAssistanceVo.class);
    }

    @ApiOperation(value = "删除TSS-4 服务助手及道路救援数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id){
        return questionAssistanceService.removeById(id);
    }

    @ApiOperation(value = "更新TSS-4 服务助手及道路救援数据")
    @PutMapping(value = "/update")
    public QuestionAssistanceVo update(@RequestBody QuestionAssistanceVo questionAssistanceVo){
        QuestionAssistancePo po = ConvertUtils.convert(questionAssistanceVo, QuestionAssistancePo.class);
        questionAssistanceService.updateById(po);
        return ConvertUtils.convert(po, QuestionAssistanceVo.class);
    }

    @ApiOperation("根据ID禁用TSS-4 服务助手及道路救援数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return questionAssistanceService.prohibitById(id);
    }
}
