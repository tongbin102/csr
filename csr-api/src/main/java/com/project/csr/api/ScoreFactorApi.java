package com.project.csr.api;

import com.project.csr.model.vo.ScoreFactorVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.csr.service.ScoreFactorService;
import com.project.csr.utils.ConvertUtils;
import com.project.csr.model.po.ScoreFactorPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 分因子成绩统计表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-05
 * @version v1.0
 */
@Slf4j
@Api(tags = {"ScoreFactorApi"},value = "分因子成绩统计表")
@RestController
@RequestMapping("/scoreFactorApi")
public class ScoreFactorApi {

    @Autowired
    private ScoreFactorService scoreFactorService;

    @ApiOperation(value = "查询分页分因子成绩统计表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<ScoreFactorPo> findListByPage(@RequestBody ScoreFactorVo scoreFactorVo){
        return scoreFactorService.findListByPage(scoreFactorVo);
    }

    @ApiOperation(value = "根据id查询分因子成绩统计表数据")
    @GetMapping(value = "/findById/{id}")
    public ScoreFactorVo findById(@PathVariable("id") String id){
        ScoreFactorPo po = scoreFactorService.getById(id);
        return ConvertUtils.convert(po, ScoreFactorVo.class);
    }

    @ApiOperation(value = "新增分因子成绩统计表数据")
    @PostMapping(value = "/add")
    public ScoreFactorVo add(@RequestBody ScoreFactorVo scoreFactorVo){
        ScoreFactorPo po = ConvertUtils.convert(scoreFactorVo, ScoreFactorPo.class);
        scoreFactorService.save(po);
        return ConvertUtils.convert(po, ScoreFactorVo.class);
    }

    @ApiOperation(value = "删除分因子成绩统计表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id){
        return scoreFactorService.removeById(id);
    }

    @ApiOperation(value = "更新分因子成绩统计表数据")
    @PutMapping(value = "/update")
    public ScoreFactorVo update(@RequestBody ScoreFactorVo scoreFactorVo){
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
    public List<Map<String, Object>> findInfo(@RequestParam("store_id") Integer storeId,
                                                          @RequestParam("current_period") String currentPeriod,
                                                          @RequestParam("last_period") String lastPeriod) {
        return scoreFactorService.findVoList(storeId, currentPeriod, lastPeriod);
    }
}
