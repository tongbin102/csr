package com.project.csr.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.project.csr.model.po.QuestionComplainPo;
import com.project.csr.model.vo.QuestionComplainVo;
import com.project.csr.service.QuestionComplainService;
import com.project.csr.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 投诉单表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-12-01
 */
@Slf4j
@Api(tags = {"QuestionComplainApi"}, value = "投诉单表")
@RestController
@RequestMapping("/questionComplainApi")
public class QuestionComplainApi {

    @Autowired
    private QuestionComplainService questionComplainService;

    @ApiOperation(value = "查询分页投诉单表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<QuestionComplainPo> findListByPage(@RequestBody QuestionComplainVo questionComplainVo) {
        return questionComplainService.findListByPage(questionComplainVo);
    }

    @ApiOperation(value = "根据id查询投诉单表数据")
    @GetMapping(value = "/findById/{id}")
    public QuestionComplainVo findById(@PathVariable("id") String id) {
        QuestionComplainPo po = questionComplainService.getById(id);
        return ConvertUtils.convert(po, QuestionComplainVo.class);
    }

    @ApiOperation(value = "新增投诉单表数据")
    @PostMapping(value = "/add")
    public QuestionComplainVo add(@RequestBody QuestionComplainVo questionComplainVo) {
        QuestionComplainPo po = ConvertUtils.convert(questionComplainVo, QuestionComplainPo.class);
        questionComplainService.save(po);
        return ConvertUtils.convert(po, QuestionComplainVo.class);
    }

    @ApiOperation(value = "删除投诉单表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id) {
        return questionComplainService.removeById(id);
    }

    @ApiOperation(value = "更新投诉单表数据")
    @PutMapping(value = "/update")
    public QuestionComplainVo update(@RequestBody QuestionComplainVo questionComplainVo) {
        QuestionComplainPo po = ConvertUtils.convert(questionComplainVo, QuestionComplainPo.class);
        questionComplainService.updateById(po);
        return ConvertUtils.convert(po, QuestionComplainVo.class);
    }

    @ApiOperation("根据ID禁用投诉单表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return questionComplainService.prohibitById(id);
    }

    @ApiOperation("获取投诉单表数据")
    @RequestMapping("/findList")
    public List<QuestionComplainVo> findList(@RequestParam("period") String period,
                                             @RequestParam("store_code") String storeCode,
                                             @RequestParam("factor_code") String factorCode) {
        LambdaQueryWrapper<QuestionComplainPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(QuestionComplainPo::getPeriod, period)
                .eq(QuestionComplainPo::getStoreCode, storeCode)
                .eq(QuestionComplainPo::getFactorCode, factorCode);
        return ConvertUtils.convert(questionComplainService.list(wrapper), QuestionComplainVo.class);
    }
}
