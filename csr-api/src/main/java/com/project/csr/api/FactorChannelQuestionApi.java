package com.project.csr.api;

import com.project.csr.model.vo.FactorChannelQuestionVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.csr.service.FactorChannelQuestionService;
import com.project.csr.utils.ConvertUtils;
import com.project.csr.model.po.FactorChannelQuestionPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * TSS-1 因子要素映射配置表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @since 2020-10-30
 * @version v1.0
 */
@Slf4j
@Api(tags = {"FactorChannelQuestionApi"},value = "TSS-1 因子要素映射配置表")
@RestController
@RequestMapping("/factorChannelQuestionApi")
public class FactorChannelQuestionApi {

    @Autowired
    private FactorChannelQuestionService factorChannelQuestionService;

    @ApiOperation(value = "查询分页TSS-1 因子要素映射配置表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<FactorChannelQuestionPo> findListByPage(@RequestBody FactorChannelQuestionVo factorChannelQuestionVo){
        return factorChannelQuestionService.findListByPage(factorChannelQuestionVo);
    }

    @ApiOperation(value = "根据id查询TSS-1 因子要素映射配置表数据")
    @GetMapping(value = "/findById/{id}")
    public FactorChannelQuestionVo findById(@PathVariable("id") String id){
        FactorChannelQuestionPo po = factorChannelQuestionService.getById(id);
        return ConvertUtils.convert(po, FactorChannelQuestionVo.class);
    }

    @ApiOperation(value = "新增TSS-1 因子要素映射配置表数据")
    @PostMapping(value = "/add")
    public FactorChannelQuestionVo add(@RequestBody FactorChannelQuestionVo factorChannelQuestionVo){
        FactorChannelQuestionPo po = ConvertUtils.convert(factorChannelQuestionVo, FactorChannelQuestionPo.class);
        factorChannelQuestionService.save(po);
        return ConvertUtils.convert(po, FactorChannelQuestionVo.class);
    }

    @ApiOperation(value = "删除TSS-1 因子要素映射配置表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id){
        return factorChannelQuestionService.removeById(id);
    }

    @ApiOperation(value = "更新TSS-1 因子要素映射配置表数据")
    @PutMapping(value = "/update")
    public FactorChannelQuestionVo update(@RequestBody FactorChannelQuestionVo factorChannelQuestionVo){
        FactorChannelQuestionPo po = ConvertUtils.convert(factorChannelQuestionVo, FactorChannelQuestionPo.class);
        factorChannelQuestionService.updateById(po);
        return ConvertUtils.convert(po, FactorChannelQuestionVo.class);
    }

    @ApiOperation("根据ID禁用TSS-1 因子要素映射配置表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return factorChannelQuestionService.prohibitById(id);
    }
}
