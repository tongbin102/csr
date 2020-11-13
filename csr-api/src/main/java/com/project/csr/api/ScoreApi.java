package com.project.csr.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.ScorePo;
import com.project.csr.model.vo.ScoreVo;
import com.project.csr.service.ScoreChannelService;
import com.project.csr.service.ScoreFactorService;
import com.project.csr.service.ScoreService;
import com.project.csr.service.StoreService;
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

	@Autowired
	private StoreService storeService;

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
	@GetMapping(value = "/findInfo")
	public Map<String, Object> findInfo(@RequestParam("scope_id") Long scopeId,
										@RequestParam("parent_id") Long parentId,
	                                    @RequestParam("current_period") String currentPeriod,
	                                    @RequestParam("last_period") String lastPeriod) {
		Map<String, Object> resultMap = new HashMap<>();
		List<ScoreVo> totalScoreVoList = scoreService.findVoList(scopeId, Long.toString(parentId), currentPeriod, lastPeriod);
		resultMap.put("totalScoreList", totalScoreVoList);
		List<ScoreVo> childScoreVoList = scoreService.findScoreInfo(scopeId, parentId, currentPeriod, lastPeriod);
		resultMap.put("childScoreList", childScoreVoList);
		return resultMap;
	}
}
