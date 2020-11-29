package com.project.csr.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.RegulationPo;
import com.project.csr.model.vo.RegulationVo;
import com.project.csr.service.RegulationService;
import com.project.csr.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 因子要素细则表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-11
 * @version v1.0
 */
@Slf4j
@Api(tags = {"RegulationApi"},value = "因子要素细则表")
@RestController
@RequestMapping("/regulationApi")
public class RegulationApi {

    @Autowired
    private RegulationService regulationService;

    @ApiOperation(value = "查询分页因子要素细则表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<RegulationPo> findListByPage(@RequestBody RegulationVo regulationVo){
        return regulationService.findListByPage(regulationVo);
    }

    @ApiOperation(value = "根据id查询因子要素细则表数据")
    @GetMapping(value = "/findById/{id}")
    public RegulationVo findById(@PathVariable("id") String id){
        RegulationPo po = regulationService.getById(id);
        return ConvertUtils.convert(po, RegulationVo.class);
    }

    @ApiOperation(value = "新增因子要素细则表数据")
    @PostMapping(value = "/add")
    public RegulationVo add(@RequestBody RegulationVo regulationVo){
        RegulationPo po = ConvertUtils.convert(regulationVo, RegulationPo.class);
        regulationService.save(po);
        return ConvertUtils.convert(po, RegulationVo.class);
    }

    @ApiOperation(value = "删除因子要素细则表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id){
        return regulationService.removeById(id);
    }

    @ApiOperation(value = "更新因子要素细则表数据")
    @PutMapping(value = "/update")
    public RegulationVo update(@RequestBody RegulationVo regulationVo){
        RegulationPo po = ConvertUtils.convert(regulationVo, RegulationPo.class);
        regulationService.updateById(po);
        return ConvertUtils.convert(po, RegulationVo.class);
    }

    @ApiOperation("根据ID禁用因子要素细则表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return regulationService.prohibitById(id);
    }

    @ApiOperation("获取因子要素细则评分规则情况")
    @GetMapping(value = "/findInfo")
    public List<RegulationVo> findInfo(@RequestParam("store_code") String storeCode,
                                            @RequestParam("period") String period,
                                            @RequestParam("factor_code") String factorCode) {
        return regulationService.findInfo(storeCode, period, factorCode);
    }
}
