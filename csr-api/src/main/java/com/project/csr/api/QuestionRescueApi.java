package com.project.csr.api;

import com.project.csr.model.vo.QuestionRescueVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.csr.service.QuestionRescueService;
import com.project.csr.utils.ConvertUtils;
import com.project.csr.model.po.QuestionRescuePo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 道路救援评分规则表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-15
 * @version v1.0
 */
@Slf4j
@Api(tags = {"QuestionRescueApi"},value = "道路救援评分规则表")
@RestController
@RequestMapping("/questionRescueApi")
public class QuestionRescueApi {

    @Autowired
    private QuestionRescueService questionRescueService;

    @ApiOperation(value = "查询分页道路救援评分规则表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<QuestionRescuePo> findListByPage(@RequestBody QuestionRescueVo questionRescueVo){
        return questionRescueService.findListByPage(questionRescueVo);
    }

    @ApiOperation(value = "根据id查询道路救援评分规则表数据")
    @GetMapping(value = "/findById/{id}")
    public QuestionRescueVo findById(@PathVariable("id") String id){
        QuestionRescuePo po = questionRescueService.getById(id);
        return ConvertUtils.convert(po, QuestionRescueVo.class);
    }

    @ApiOperation(value = "新增道路救援评分规则表数据")
    @PostMapping(value = "/add")
    public QuestionRescueVo add(@RequestBody QuestionRescueVo questionRescueVo){
        QuestionRescuePo po = ConvertUtils.convert(questionRescueVo, QuestionRescuePo.class);
        questionRescueService.save(po);
        return ConvertUtils.convert(po, QuestionRescueVo.class);
    }

    @ApiOperation(value = "删除道路救援评分规则表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id){
        return questionRescueService.removeById(id);
    }

    @ApiOperation(value = "更新道路救援评分规则表数据")
    @PutMapping(value = "/update")
    public QuestionRescueVo update(@RequestBody QuestionRescueVo questionRescueVo){
        QuestionRescuePo po = ConvertUtils.convert(questionRescueVo, QuestionRescuePo.class);
        questionRescueService.updateById(po);
        return ConvertUtils.convert(po, QuestionRescueVo.class);
    }

    @ApiOperation("根据ID禁用道路救援评分规则表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return questionRescueService.prohibitById(id);
    }
}
