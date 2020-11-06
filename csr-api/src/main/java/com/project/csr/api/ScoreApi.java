package com.project.csr.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.common.response.BaseResponse;
import com.project.csr.model.po.ScorePo;
import com.project.csr.model.vo.ScoreVo;
import com.project.csr.service.ScoreChannelService;
import com.project.csr.service.ScoreFactorService;
import com.project.csr.service.ScoreService;
import com.project.csr.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 成绩排名表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-05
 */
@Slf4j
@Api(tags = {"ScoreApi"}, value = "成绩排名表")
@RestController
@RequestMapping("/scoreApi")
public class ScoreApi {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private ScoreChannelService scoreChannelService;

    @Autowired
    private ScoreFactorService scoreFactorService;

    @ApiOperation(value = "查询分页成绩排名表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<ScorePo> findListByPage(@RequestBody ScoreVo scoreVo) {
        return scoreService.findListByPage(scoreVo);
    }

    @ApiOperation(value = "根据id查询成绩排名表数据")
    @GetMapping(value = "/findById/{id}")
    public ScoreVo findById(@PathVariable("id") String id) {
        ScorePo po = scoreService.getById(id);
        return ConvertUtils.convert(po, ScoreVo.class);
    }

    @ApiOperation(value = "新增成绩排名表数据")
    @PostMapping(value = "/add")
    public ScoreVo add(@RequestBody ScoreVo scoreVo) {
        ScorePo po = ConvertUtils.convert(scoreVo, ScorePo.class);
        scoreService.save(po);
        return ConvertUtils.convert(po, ScoreVo.class);
    }

    @ApiOperation(value = "删除成绩排名表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id) {
        return scoreService.removeById(id);
    }

    @ApiOperation(value = "更新成绩排名表数据")
    @PutMapping(value = "/update")
    public ScoreVo update(@RequestBody ScoreVo scoreVo) {
        ScorePo po = ConvertUtils.convert(scoreVo, ScorePo.class);
        scoreService.updateById(po);
        return ConvertUtils.convert(po, ScoreVo.class);
    }

    @ApiOperation("根据ID禁用成绩排名表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return scoreService.prohibitById(id);
    }

    @ApiOperation("获取得分情况")
    @GetMapping(value = "/findScoreInfo")
    public Map<String, Object> findScoreInfo(@RequestParam("scope_id") Integer scopeId,
                                                     @RequestParam("period") String period) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap.put("scoreTotal", scoreService.findVoList(scopeId, period));
            resultMap.put("scoreSeparate", scoreService.findVoList(scopeId + 1, period));
            resultMap.put("scoreChannel", scoreChannelService.findVoList(scopeId, period));
            resultMap.put("scoreFactor", scoreFactorService.findVoList(scopeId, period));
        } catch (ParseException e) {
            log.info("期数格式错误");
        }
        return resultMap;
    }
}
