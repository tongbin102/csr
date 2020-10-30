package com.project.csr.api;

import com.project.csr.model.vo.QuestionSurveyVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.csr.service.QuestionSurveyService;
import com.project.csr.utils.ConvertUtils;
import com.project.csr.model.po.QuestionSurveyPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * TSS-2 调研问卷评分规则配置表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @since 2020-10-30
 * @version v1.0
 */
@Slf4j
@Api(tags = {"QuestionSurveyApi"},value = "TSS-2 调研问卷评分规则配置表")
@RestController
@RequestMapping("/questionSurveyApi")
public class QuestionSurveyApi {

    @Autowired
    private QuestionSurveyService questionSurveyService;

    @ApiOperation(value = "查询分页TSS-2 调研问卷评分规则配置表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<QuestionSurveyPo> findListByPage(@RequestBody QuestionSurveyVo questionSurveyVo){
        return questionSurveyService.findListByPage(questionSurveyVo);
    }

    @ApiOperation(value = "根据id查询TSS-2 调研问卷评分规则配置表数据")
    @GetMapping(value = "/findById/{id}")
    public QuestionSurveyVo findById(@PathVariable("id") String id){
        QuestionSurveyPo po = questionSurveyService.getById(id);
        return ConvertUtils.convert(po, QuestionSurveyVo.class);
    }

    @ApiOperation(value = "新增TSS-2 调研问卷评分规则配置表数据")
    @PostMapping(value = "/add")
    public QuestionSurveyVo add(@RequestBody QuestionSurveyVo questionSurveyVo){
        QuestionSurveyPo po = ConvertUtils.convert(questionSurveyVo, QuestionSurveyPo.class);
        questionSurveyService.save(po);
        return ConvertUtils.convert(po, QuestionSurveyVo.class);
    }

    @ApiOperation(value = "删除TSS-2 调研问卷评分规则配置表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id){
        return questionSurveyService.removeById(id);
    }

    @ApiOperation(value = "更新TSS-2 调研问卷评分规则配置表数据")
    @PutMapping(value = "/update")
    public QuestionSurveyVo update(@RequestBody QuestionSurveyVo questionSurveyVo){
        QuestionSurveyPo po = ConvertUtils.convert(questionSurveyVo, QuestionSurveyPo.class);
        questionSurveyService.updateById(po);
        return ConvertUtils.convert(po, QuestionSurveyVo.class);
    }

    @ApiOperation("根据ID禁用TSS-2 调研问卷评分规则配置表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return questionSurveyService.prohibitById(id);
    }
}
