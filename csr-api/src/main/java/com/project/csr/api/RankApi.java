package com.project.csr.api;

import com.project.csr.model.vo.RankVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.csr.service.RankService;
import com.project.csr.utils.ConvertUtils;
import com.project.csr.model.po.RankPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 成绩排名表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @since 2020-10-30
 * @version v1.0
 */
@Slf4j
@Api(tags = {"RankApi"},value = "成绩排名表")
@RestController
@RequestMapping("/rankApi")
public class RankApi {

    @Autowired
    private RankService rankService;

    @ApiOperation(value = "查询分页成绩排名表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<RankPo> findListByPage(@RequestBody RankVo rankVo){
        return rankService.findListByPage(rankVo);
    }

    @ApiOperation(value = "根据id查询成绩排名表数据")
    @GetMapping(value = "/findById/{id}")
    public RankVo findById(@PathVariable("id") String id){
        RankPo po = rankService.getById(id);
        return ConvertUtils.convert(po, RankVo.class);
    }

    @ApiOperation(value = "新增成绩排名表数据")
    @PostMapping(value = "/add")
    public RankVo add(@RequestBody RankVo rankVo){
        RankPo po = ConvertUtils.convert(rankVo, RankPo.class);
        rankService.save(po);
        return ConvertUtils.convert(po, RankVo.class);
    }

    @ApiOperation(value = "删除成绩排名表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id){
        return rankService.removeById(id);
    }

    @ApiOperation(value = "更新成绩排名表数据")
    @PutMapping(value = "/update")
    public RankVo update(@RequestBody RankVo rankVo){
        RankPo po = ConvertUtils.convert(rankVo, RankPo.class);
        rankService.updateById(po);
        return ConvertUtils.convert(po, RankVo.class);
    }

    @ApiOperation("根据ID禁用成绩排名表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return rankService.prohibitById(id);
    }
}
