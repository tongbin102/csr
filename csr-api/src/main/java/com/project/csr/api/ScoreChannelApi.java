package com.project.csr.api;

import com.project.csr.common.response.BaseResponse;
import com.project.csr.model.vo.ScoreChannelVo;
import com.project.csr.model.vo.ScoreVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.csr.service.ScoreChannelService;
import com.project.csr.utils.ConvertUtils;
import com.project.csr.model.po.ScoreChannelPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 分渠道成绩统计表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-05
 */
@Slf4j
@Api(tags = {"ScoreChannelApi"}, value = "分渠道成绩统计表")
@RestController
@RequestMapping("/scoreChannelApi")
public class ScoreChannelApi {

    @Autowired
    private ScoreChannelService scoreChannelService;

    @ApiOperation(value = "查询分页分渠道成绩统计表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<ScoreChannelPo> findListByPage(@RequestBody ScoreChannelVo scoreChannelVo) {
        return scoreChannelService.findListByPage(scoreChannelVo);
    }

    @ApiOperation(value = "根据id查询分渠道成绩统计表数据")
    @GetMapping(value = "/findById/{id}")
    public ScoreChannelVo findById(@PathVariable("id") String id) {
        ScoreChannelPo po = scoreChannelService.getById(id);
        return ConvertUtils.convert(po, ScoreChannelVo.class);
    }

    @ApiOperation(value = "新增分渠道成绩统计表数据")
    @PostMapping(value = "/add")
    public ScoreChannelVo add(@RequestBody ScoreChannelVo scoreChannelVo) {
        ScoreChannelPo po = ConvertUtils.convert(scoreChannelVo, ScoreChannelPo.class);
        scoreChannelService.save(po);
        return ConvertUtils.convert(po, ScoreChannelVo.class);
    }

    @ApiOperation(value = "删除分渠道成绩统计表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id) {
        return scoreChannelService.removeById(id);
    }

    @ApiOperation(value = "更新分渠道成绩统计表数据")
    @PutMapping(value = "/update")
    public ScoreChannelVo update(@RequestBody ScoreChannelVo scoreChannelVo) {
        ScoreChannelPo po = ConvertUtils.convert(scoreChannelVo, ScoreChannelPo.class);
        scoreChannelService.updateById(po);
        return ConvertUtils.convert(po, ScoreChannelVo.class);
    }

    @ApiOperation("根据ID禁用分渠道成绩统计表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return scoreChannelService.prohibitById(id);
    }

    @ApiOperation("获取分渠道得分情况")
    @GetMapping(value = "/findInfo")
    public List<Map<String, Object>> findInfo(@RequestParam("store_id") Integer storeId,
                                                    @RequestParam("current_period") String currentPeriod,
                                                    @RequestParam("last_period") String lastPeriod) {
        return scoreChannelService.findVoList(storeId, currentPeriod, lastPeriod);
    }
}
