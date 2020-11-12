package com.project.csr.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.QuestionSurveyPo;
import com.project.csr.model.vo.QuestionSurveyVo;
import com.project.csr.service.QuestionSurveyService;
import com.project.csr.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 客户调研评分规则表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-10
 */
@Slf4j
@Api(tags = {"QuestionSurveyApi"}, value = "客户调研评分规则表")
@RestController
@RequestMapping("/questionSurveyApi")
public class QuestionSurveyApi {

    @Autowired
    private QuestionSurveyService questionSurveyService;

    @ApiOperation(value = "查询分页客户调研评分规则表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<QuestionSurveyPo> findListByPage(@RequestBody QuestionSurveyVo questionSurveyVo) {
        return questionSurveyService.findListByPage(questionSurveyVo);
    }

    @ApiOperation(value = "根据id查询客户调研评分规则表数据")
    @GetMapping(value = "/findById/{id}")
    public QuestionSurveyVo findById(@PathVariable("id") String id) {
        QuestionSurveyPo po = questionSurveyService.getById(id);
        return ConvertUtils.convert(po, QuestionSurveyVo.class);
    }

    @ApiOperation(value = "新增客户调研评分规则表数据")
    @PostMapping(value = "/add")
    public QuestionSurveyVo add(@RequestBody QuestionSurveyVo questionSurveyVo) {
        QuestionSurveyPo po = ConvertUtils.convert(questionSurveyVo, QuestionSurveyPo.class);
        questionSurveyService.save(po);
        return ConvertUtils.convert(po, QuestionSurveyVo.class);
    }

    @ApiOperation(value = "删除客户调研评分规则表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id) {
        return questionSurveyService.removeById(id);
    }

    @ApiOperation(value = "更新客户调研评分规则表数据")
    @PutMapping(value = "/update")
    public QuestionSurveyVo update(@RequestBody QuestionSurveyVo questionSurveyVo) {
        QuestionSurveyPo po = ConvertUtils.convert(questionSurveyVo, QuestionSurveyPo.class);
        questionSurveyService.updateById(po);
        return ConvertUtils.convert(po, QuestionSurveyVo.class);
    }

    @ApiOperation("根据ID禁用客户调研评分规则表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return questionSurveyService.prohibitById(id);
    }

    @ApiOperation("获取客户调研评分规则列表")
    @RequestMapping("/findList")
    public List<QuestionSurveyVo> findList(@RequestParam(value = "regulation_id", required = false) Long regulationId) {
        if (null != regulationId) {
            return questionSurveyService.findListByRegulationId(regulationId);
        }
        return ConvertUtils.convert(questionSurveyService.list(), QuestionSurveyVo.class);
    }
}
