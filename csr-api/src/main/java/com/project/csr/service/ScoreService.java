
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.ScorePo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.vo.ScoreVo;

import java.util.List;

/**
 * <p>
 * 成绩排名表 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-05
 */
public interface ScoreService extends IService<ScorePo> {

	/**
	 * 分页查询
	 *
	 * @param scoreVo 查询条件
	 * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.ScorePo>
	 * @author bin.tong
	 * @since 2020-11-05
	 */
	IPage<ScorePo> findListByPage(ScoreVo scoreVo);

	/**
	 * 根据ID禁用数据
	 *
	 * @param id 主键id
	 * @return boolean
	 * @author bin.tong
	 * @since 2020-11-05
	 */
	boolean prohibitById(String id);

	// List<ScoreVo> findVoList(Integer scopeId, String currentPeriod, String lastPeriod, String storeIds);

	/**
	 * 获取得分信息
	 *
	 * @param scopeId
	 * @param currentPeriod
	 * @param lastPeriod
	 * @param parentId
	 * @return
	 */
	List<ScoreVo> findScoreInfo(Long scopeId, Long parentId, String currentPeriod, String lastPeriod);

	List<ScoreVo> findVoList(Long scopeId, String storeIds, String currentPeriod, String lastPeriod);

	List<ScoreVo> findVoListByPeriods(Long scopeId, Long storeId, String beginPeriod, String endPeriod);
}