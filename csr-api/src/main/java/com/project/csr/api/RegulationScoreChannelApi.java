package com.project.csr.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.RegulationScoreChannelPo;
import com.project.csr.model.vo.RegulationScoreChannelVo;
import com.project.csr.service.RegulationScoreChannelService;
import com.project.csr.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 因子要素细则分渠道评分规则表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-11
 */
@Slf4j
@Api(tags = {"RegulationScoreChannelApi"}, value = "因子要素细则分渠道评分规则表")
@RestController
@RequestMapping("/regulationScoreChannelApi")
public class RegulationScoreChannelApi {

    @Autowired
    private RegulationScoreChannelService regulationScoreChannelService;

    @ApiOperation(value = "查询分页因子要素细则分渠道评分规则表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<RegulationScoreChannelPo> findListByPage(@RequestBody RegulationScoreChannelVo regulationScoreChannelVo) {
        return regulationScoreChannelService.findListByPage(regulationScoreChannelVo);
    }

    @ApiOperation(value = "根据id查询因子要素细则分渠道评分规则表数据")
    @GetMapping(value = "/findById/{id}")
    public RegulationScoreChannelVo findById(@PathVariable("id") String id) {
        RegulationScoreChannelPo po = regulationScoreChannelService.getById(id);
        return ConvertUtils.convert(po, RegulationScoreChannelVo.class);
    }

    @ApiOperation(value = "新增因子要素细则分渠道评分规则表数据")
    @PostMapping(value = "/add")
    public RegulationScoreChannelVo add(@RequestBody RegulationScoreChannelVo regulationScoreChannelVo) {
        RegulationScoreChannelPo po = ConvertUtils.convert(regulationScoreChannelVo, RegulationScoreChannelPo.class);
        regulationScoreChannelService.save(po);
        return ConvertUtils.convert(po, RegulationScoreChannelVo.class);
    }

    @ApiOperation(value = "删除因子要素细则分渠道评分规则表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id) {
        return regulationScoreChannelService.removeById(id);
    }

    @ApiOperation(value = "更新因子要素细则分渠道评分规则表数据")
    @PutMapping(value = "/update")
    public RegulationScoreChannelVo update(@RequestBody RegulationScoreChannelVo regulationScoreChannelVo) {
        RegulationScoreChannelPo po = ConvertUtils.convert(regulationScoreChannelVo, RegulationScoreChannelPo.class);
        regulationScoreChannelService.updateById(po);
        return ConvertUtils.convert(po, RegulationScoreChannelVo.class);
    }

    @ApiOperation("根据ID禁用因子要素细则分渠道评分规则表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return regulationScoreChannelService.prohibitById(id);
    }

    @ApiOperation("获取因子要素细则分渠道评分规则信息")
    @GetMapping(value = "/findInfo")
    public List<Map<String, Object>> findInfo(@RequestParam("store_id") Long storeId,
                                              @RequestParam("period") String period,
                                              @RequestParam("factor_id") Long factorId) {
        return regulationScoreChannelService.findMapList(storeId, period, factorId);
    }
}
