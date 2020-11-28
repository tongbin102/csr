package com.project.csr.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.ScoreFactorPo;
import com.project.csr.model.vo.ScoreFactorVo;
import com.project.csr.model.vo.ScoreVo;
import com.project.csr.service.ScoreFactorService;
import com.project.csr.service.ScoreService;
import com.project.csr.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 分因子成绩统计表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-05
 */
@Slf4j
@Api(tags = {"ScoreFactorApi"}, value = "分因子成绩统计表")
@RestController
@RequestMapping("/scoreFactorApi")
public class ScoreFactorApi {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private ScoreFactorService scoreFactorService;

    @ApiOperation(value = "查询分页分因子成绩统计表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<ScoreFactorPo> findListByPage(@RequestBody ScoreFactorVo scoreFactorVo) {
        return scoreFactorService.findListByPage(scoreFactorVo);
    }

    @ApiOperation(value = "根据id查询分因子成绩统计表数据")
    @GetMapping(value = "/findById/{id}")
    public ScoreFactorVo findById(@PathVariable("id") String id) {
        ScoreFactorPo po = scoreFactorService.getById(id);
        return ConvertUtils.convert(po, ScoreFactorVo.class);
    }

    @ApiOperation(value = "新增分因子成绩统计表数据")
    @PostMapping(value = "/add")
    public ScoreFactorVo add(@RequestBody ScoreFactorVo scoreFactorVo) {
        ScoreFactorPo po = ConvertUtils.convert(scoreFactorVo, ScoreFactorPo.class);
        scoreFactorService.save(po);
        return ConvertUtils.convert(po, ScoreFactorVo.class);
    }

    @ApiOperation(value = "删除分因子成绩统计表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id) {
        return scoreFactorService.removeById(id);
    }

    @ApiOperation(value = "更新分因子成绩统计表数据")
    @PutMapping(value = "/update")
    public ScoreFactorVo update(@RequestBody ScoreFactorVo scoreFactorVo) {
        ScoreFactorPo po = ConvertUtils.convert(scoreFactorVo, ScoreFactorPo.class);
        scoreFactorService.updateById(po);
        return ConvertUtils.convert(po, ScoreFactorVo.class);
    }

    @ApiOperation("根据ID禁用分因子成绩统计表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return scoreFactorService.prohibitById(id);
    }

    @ApiOperation("获取分因子得分情况")
    @GetMapping(value = "/findInfo")
    public List<Map<String, Object>> findInfo(@RequestParam("scope_id") Long scopeId,
                                              @RequestParam("store_code") String storeCode,
                                              @RequestParam("current_period") String currentPeriod,
                                              @RequestParam("last_period") String lastPeriod) {
        return scoreFactorService.findMapList(scopeId, storeCode, currentPeriod, lastPeriod);
    }

    @ApiOperation("获取分因子得分情况（经销店）")
    @GetMapping(value = "/findInfoForStore")
    public Map<String, Object> findInfoForStore(@RequestParam("scope_id") Long scopeId,
                                                @RequestParam("store_code") String storeCode,
                                                @RequestParam("current_period") String currentPeriod,
                                                @RequestParam("last_period") String lastPeriod) {

        Map<String, Object> resultMap = new HashMap<>();
        List<ScoreVo> totalScoreVoList = scoreService.findVoList(scopeId, storeCode, currentPeriod, lastPeriod);
        resultMap.put("totalScoreList", totalScoreVoList);

        Map<String, Object> params = new HashMap<>();
        params.put("current_period", currentPeriod);
        params.put("last_period", lastPeriod);
        params.put("store_code", storeCode);
        List<ScoreFactorVo> scoreFactorVoList = scoreFactorService.findVoList(params);
        resultMap.put("scoreFactorList", scoreFactorVoList);
        return resultMap;
    }

    @ApiOperation("获取分因子得分情况（经销店）")
    @GetMapping(value = "/findInfoForFactor")
    public Map<String, Object> findInfoForFactor(@RequestParam("store_code") String storeCode,
                                                 @RequestParam("current_period") String currentPeriod,
                                                 @RequestParam("last_period") String lastPeriod,
                                                 @RequestParam(value = "factor_code", required = false) String factorCode) {

        Map<String, Object> resultMap = new HashMap<>();

        Map<String, Object> params = new HashMap<>();
        params.put("current_period", currentPeriod);
        params.put("last_period", lastPeriod);
        params.put("store_code", storeCode);
        params.put("factor_code", factorCode);
        List<ScoreFactorVo> scoreFactorVoList = scoreFactorService.findVoList(params);
        resultMap.put("scoreFactorList", scoreFactorVoList);
        return resultMap;
    }

    @ApiOperation("根据期间范围获取分因子得分情况")
    @GetMapping(value = "/findInfoByPeriods")
    public List<Map<String, Object>> findInfoByPeriods(@RequestParam("scope_id") Long scopeId,
                                                       @RequestParam("store_code") String storeCode,
                                                       @RequestParam("begin_period") String beginPeriod,
                                                       @RequestParam("end_period") String endPeriod) {
        return scoreFactorService.findVoMapList(scopeId, storeCode, beginPeriod, endPeriod);
    }

}
