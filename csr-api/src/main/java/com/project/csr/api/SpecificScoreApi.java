package com.project.csr.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.SpecificScorePo;
import com.project.csr.model.vo.SpecificScoreVo;
import com.project.csr.service.SpecificScoreService;
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
 * 细则-分数关系表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-09
 */
@Slf4j
@Api(tags = {"SpecificScoreApi"}, value = "细则-分数关系表")
@RestController
@RequestMapping("/specificScoreApi")
public class SpecificScoreApi {

    @Autowired
    private SpecificScoreService specificScoreService;

    @ApiOperation(value = "查询分页细则-分数关系表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<SpecificScorePo> findListByPage(@RequestBody SpecificScoreVo specificScoreVo) {
        return specificScoreService.findListByPage(specificScoreVo);
    }

    @ApiOperation(value = "根据id查询细则-分数关系表数据")
    @GetMapping(value = "/findById/{id}")
    public SpecificScoreVo findById(@PathVariable("id") String id) {
        SpecificScorePo po = specificScoreService.getById(id);
        return ConvertUtils.convert(po, SpecificScoreVo.class);
    }

    @ApiOperation(value = "新增细则-分数关系表数据")
    @PostMapping(value = "/add")
    public SpecificScoreVo add(@RequestBody SpecificScoreVo specificScoreVo) {
        SpecificScorePo po = ConvertUtils.convert(specificScoreVo, SpecificScorePo.class);
        specificScoreService.save(po);
        return ConvertUtils.convert(po, SpecificScoreVo.class);
    }

    @ApiOperation(value = "删除细则-分数关系表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id) {
        return specificScoreService.removeById(id);
    }

    @ApiOperation(value = "更新细则-分数关系表数据")
    @PutMapping(value = "/update")
    public SpecificScoreVo update(@RequestBody SpecificScoreVo specificScoreVo) {
        SpecificScorePo po = ConvertUtils.convert(specificScoreVo, SpecificScorePo.class);
        specificScoreService.updateById(po);
        return ConvertUtils.convert(po, SpecificScoreVo.class);
    }

    @ApiOperation("根据ID禁用细则-分数关系表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return specificScoreService.prohibitById(id);
    }

    @ApiOperation("获取因子要素得分情况")
    @GetMapping(value = "/findInfo")
    public List<SpecificScoreVo> findInfo(@RequestParam("store_id") Long storeId,
                                          @RequestParam("period") String period,
                                          @RequestParam("factor_id") Long factorId) {
        Map<String, Object> resultMap = new HashMap<>();
        List<SpecificScoreVo> specificScoreVoList = specificScoreService.findInfo(storeId, period, factorId);

        return specificScoreVoList;
    }
}
