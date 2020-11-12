package com.project.csr.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.QuestionAssistancePo;
import com.project.csr.model.vo.QuestionAssistanceVo;
import com.project.csr.service.QuestionAssistanceService;
import com.project.csr.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 服务助手评分规则表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-10
 */
@Slf4j
@Api(tags = {"QuestionAssistanceApi"}, value = "服务助手评分规则表")
@RestController
@RequestMapping("/questionAssistanceApi")
public class QuestionAssistanceApi {

    @Autowired
    private QuestionAssistanceService questionAssistanceService;

    @ApiOperation(value = "查询分页服务助手评分规则表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<QuestionAssistancePo> findListByPage(@RequestBody QuestionAssistanceVo questionAssistanceVo) {
        return questionAssistanceService.findListByPage(questionAssistanceVo);
    }

    @ApiOperation(value = "根据id查询服务助手评分规则表数据")
    @GetMapping(value = "/findById/{id}")
    public QuestionAssistanceVo findById(@PathVariable("id") String id) {
        QuestionAssistancePo po = questionAssistanceService.getById(id);
        return ConvertUtils.convert(po, QuestionAssistanceVo.class);
    }

    @ApiOperation(value = "新增服务助手评分规则表数据")
    @PostMapping(value = "/add")
    public QuestionAssistanceVo add(@RequestBody QuestionAssistanceVo questionAssistanceVo) {
        QuestionAssistancePo po = ConvertUtils.convert(questionAssistanceVo, QuestionAssistancePo.class);
        questionAssistanceService.save(po);
        return ConvertUtils.convert(po, QuestionAssistanceVo.class);
    }

    @ApiOperation(value = "删除服务助手评分规则表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id) {
        return questionAssistanceService.removeById(id);
    }

    @ApiOperation(value = "更新服务助手评分规则表数据")
    @PutMapping(value = "/update")
    public QuestionAssistanceVo update(@RequestBody QuestionAssistanceVo questionAssistanceVo) {
        QuestionAssistancePo po = ConvertUtils.convert(questionAssistanceVo, QuestionAssistancePo.class);
        questionAssistanceService.updateById(po);
        return ConvertUtils.convert(po, QuestionAssistanceVo.class);
    }

    @ApiOperation("根据ID禁用服务助手评分规则表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return questionAssistanceService.prohibitById(id);
    }

    @ApiOperation("获取服务助手评分规则表数据")
    @RequestMapping("/findList")
    public List<QuestionAssistanceVo> findList(@RequestParam("period") String period,
                                               @RequestParam("store_id") Long storeId,
                                               @RequestParam(value = "regulation_id", required = false) Long regulationId) {
        if (null != regulationId) {
            return questionAssistanceService.findListByRegulationId(period, storeId, regulationId);
        }
        return ConvertUtils.convert(questionAssistanceService.list(), QuestionAssistanceVo.class);
    }
}
