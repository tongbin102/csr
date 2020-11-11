package com.project.csr.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.RegulationScorePo;
import com.project.csr.model.vo.RegulationScoreVo;
import com.project.csr.service.RegulationScoreService;
import com.project.csr.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 因子要素细则评分规则表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-11
 */
@Slf4j
@Api(tags = {"RegulationScoreApi"}, value = "因子要素细则评分规则表")
@RestController
@RequestMapping("/regulationScoreApi")
public class RegulationScoreApi {

    @Autowired
    private RegulationScoreService regulationScoreService;

    @ApiOperation(value = "查询分页因子要素细则评分规则表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<RegulationScorePo> findListByPage(@RequestBody RegulationScoreVo regulationScoreVo) {
        return regulationScoreService.findListByPage(regulationScoreVo);
    }

    @ApiOperation(value = "根据id查询因子要素细则评分规则表数据")
    @GetMapping(value = "/findById/{id}")
    public RegulationScoreVo findById(@PathVariable("id") String id) {
        RegulationScorePo po = regulationScoreService.getById(id);
        return ConvertUtils.convert(po, RegulationScoreVo.class);
    }

    @ApiOperation(value = "新增因子要素细则评分规则表数据")
    @PostMapping(value = "/add")
    public RegulationScoreVo add(@RequestBody RegulationScoreVo regulationScoreVo) {
        RegulationScorePo po = ConvertUtils.convert(regulationScoreVo, RegulationScorePo.class);
        regulationScoreService.save(po);
        return ConvertUtils.convert(po, RegulationScoreVo.class);
    }

    @ApiOperation(value = "删除因子要素细则评分规则表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id) {
        return regulationScoreService.removeById(id);
    }

    @ApiOperation(value = "更新因子要素细则评分规则表数据")
    @PutMapping(value = "/update")
    public RegulationScoreVo update(@RequestBody RegulationScoreVo regulationScoreVo) {
        RegulationScorePo po = ConvertUtils.convert(regulationScoreVo, RegulationScorePo.class);
        regulationScoreService.updateById(po);
        return ConvertUtils.convert(po, RegulationScoreVo.class);
    }

    @ApiOperation("根据ID禁用因子要素细则评分规则表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return regulationScoreService.prohibitById(id);
    }

    @ApiOperation("获取因子要素细则评分规则情况")
    @GetMapping(value = "/findInfo")
    public List<RegulationScoreVo> findInfo(@RequestParam("store_id") Long storeId,
                                            @RequestParam("period") String period,
                                            @RequestParam("factor_id") Long factorId) {
        return regulationScoreService.findInfo(storeId, period, factorId);
    }
}
