package com.project.csr.api;

import com.project.csr.model.vo.ScoreQuestionVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.csr.service.ScoreQuestionService;
import com.project.csr.utils.ConvertUtils;
import com.project.csr.model.po.ScoreQuestionPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 门店题目-得分表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-11
 * @version v1.0
 */
@Slf4j
@Api(tags = {"ScoreQuestionApi"},value = "门店题目-得分表")
@RestController
@RequestMapping("/scoreQuestionApi")
public class ScoreQuestionApi {

    @Autowired
    private ScoreQuestionService scoreQuestionService;

    @ApiOperation(value = "查询分页门店题目-得分表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<ScoreQuestionPo> findListByPage(@RequestBody ScoreQuestionVo scoreQuestionVo){
        return scoreQuestionService.findListByPage(scoreQuestionVo);
    }

    @ApiOperation(value = "根据id查询门店题目-得分表数据")
    @GetMapping(value = "/findById/{id}")
    public ScoreQuestionVo findById(@PathVariable("id") String id){
        ScoreQuestionPo po = scoreQuestionService.getById(id);
        return ConvertUtils.convert(po, ScoreQuestionVo.class);
    }

    @ApiOperation(value = "新增门店题目-得分表数据")
    @PostMapping(value = "/add")
    public ScoreQuestionVo add(@RequestBody ScoreQuestionVo scoreQuestionVo){
        ScoreQuestionPo po = ConvertUtils.convert(scoreQuestionVo, ScoreQuestionPo.class);
        scoreQuestionService.save(po);
        return ConvertUtils.convert(po, ScoreQuestionVo.class);
    }

    @ApiOperation(value = "删除门店题目-得分表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id){
        return scoreQuestionService.removeById(id);
    }

    @ApiOperation(value = "更新门店题目-得分表数据")
    @PutMapping(value = "/update")
    public ScoreQuestionVo update(@RequestBody ScoreQuestionVo scoreQuestionVo){
        ScoreQuestionPo po = ConvertUtils.convert(scoreQuestionVo, ScoreQuestionPo.class);
        scoreQuestionService.updateById(po);
        return ConvertUtils.convert(po, ScoreQuestionVo.class);
    }

    @ApiOperation("根据ID禁用门店题目-得分表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return scoreQuestionService.prohibitById(id);
    }
}
