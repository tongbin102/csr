package com.project.csr.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.QuestionDataPo;
import com.project.csr.model.vo.QuestionDataVo;
import com.project.csr.service.QuestionDataService;
import com.project.csr.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 道路救援评分规则表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-15
 */
@Slf4j
@Api(tags = {"QuestionDataApi"}, value = "道路救援评分规则表")
@RestController
@RequestMapping("/questionDataApi")
public class QuestionDataApi {

    @Autowired
    private QuestionDataService questionDataService;

    @ApiOperation(value = "查询分页道路救援评分规则表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<QuestionDataPo> findListByPage(@RequestBody QuestionDataVo questionDataVo) {
        return questionDataService.findListByPage(questionDataVo);
    }

    @ApiOperation(value = "根据id查询道路救援评分规则表数据")
    @GetMapping(value = "/findById/{id}")
    public QuestionDataVo findById(@PathVariable("id") String id) {
        QuestionDataPo po = questionDataService.getById(id);
        return ConvertUtils.convert(po, QuestionDataVo.class);
    }

    @ApiOperation(value = "新增道路救援评分规则表数据")
    @PostMapping(value = "/add")
    public QuestionDataVo add(@RequestBody QuestionDataVo questionDataVo) {
        QuestionDataPo po = ConvertUtils.convert(questionDataVo, QuestionDataPo.class);
        questionDataService.save(po);
        return ConvertUtils.convert(po, QuestionDataVo.class);
    }

    @ApiOperation(value = "删除道路救援评分规则表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id) {
        return questionDataService.removeById(id);
    }

    @ApiOperation(value = "更新道路救援评分规则表数据")
    @PutMapping(value = "/update")
    public QuestionDataVo update(@RequestBody QuestionDataVo questionDataVo) {
        QuestionDataPo po = ConvertUtils.convert(questionDataVo, QuestionDataPo.class);
        questionDataService.updateById(po);
        return ConvertUtils.convert(po, QuestionDataVo.class);
    }

    @ApiOperation("根据ID禁用道路救援评分规则表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return questionDataService.prohibitById(id);
    }

    @ApiOperation("获取客户调研评分规则列表")
    @RequestMapping("/findList")
    public List<QuestionDataVo> findList(@RequestParam("period") String period,
                                         @RequestParam("store_id") Long storeId,
                                         @RequestParam(value = "regulation_id", required = false) Long regulationId) {
        if (null != regulationId) {
            return questionDataService.findListByRegulationId(period, storeId, regulationId);
        }
        return ConvertUtils.convert(questionDataService.list(), QuestionDataVo.class);
    }
}
