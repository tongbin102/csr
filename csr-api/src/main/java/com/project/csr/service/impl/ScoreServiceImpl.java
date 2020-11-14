package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.constants.DictionaryType;
import com.project.csr.dao.ScoreMapper;
import com.project.csr.model.po.*;
import com.project.csr.model.vo.ScoreVo;
import com.project.csr.service.*;
import com.project.csr.utils.ToolsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 成绩排名表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-05
 */
@Slf4j
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, ScorePo> implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Autowired
    private RegionService regionService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private CityService cityService;

    @Autowired
    private StoreService storeService;


    @Override
    public IPage<ScorePo> findListByPage(ScoreVo scoreVo) {
        IPage<ScorePo> page = new Page<>(scoreVo.getPageNo(), scoreVo.getPageSize());
        LambdaQueryWrapper<ScorePo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<ScorePo> selectPage = scoreMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        ScorePo po = new ScorePo();
        po.setValidInd(false);
        LambdaQueryWrapper<ScorePo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ScorePo::getId, id);
        return scoreMapper.update(po, wrapper) >= 1;
    }

    @Override
    public List<ScoreVo> findScoreInfo(Long scopeId, Long parentId, String currentPeriod, String lastPeriod) {
        List<ScoreVo> resultList = new ArrayList();
        String childIds = null;
        Long childScopeId = null;

        if (scopeId.equals(DictionaryType.SCOPE_ID_NATIONAL)) {
            LambdaQueryWrapper<RegionPo> wrapper = Wrappers.lambdaQuery();
            wrapper.eq(RegionPo::getNationalId, parentId);
            List<RegionPo> childList = regionService.list(wrapper);
            childIds = ToolsUtils.getIdsFromList(childList, ",");
            childScopeId = DictionaryType.SCOPE_ID_REGION;
        } else if (scopeId.equals(DictionaryType.SCOPE_ID_REGION)) {
            LambdaQueryWrapper<ProvincePo> wrapper = Wrappers.lambdaQuery();
            wrapper.eq(ProvincePo::getRegionId, parentId);
            List<ProvincePo> childList = provinceService.list(wrapper);
            childIds = ToolsUtils.getIdsFromList(childList, ",");
            childScopeId = DictionaryType.SCOPE_ID_PROVINCE;
        } else if (scopeId.equals(DictionaryType.SCOPE_ID_PROVINCE)) {
            LambdaQueryWrapper<CityPo> wrapper = Wrappers.lambdaQuery();
            wrapper.eq(CityPo::getProvinceId, parentId);
            List<CityPo> childList = cityService.list(wrapper);
            childIds = ToolsUtils.getIdsFromList(childList, ",");
            childScopeId = DictionaryType.SCOPE_ID_CITY;
        } else if (scopeId.equals(DictionaryType.SCOPE_ID_CITY)) {
            LambdaQueryWrapper<StorePo> wrapper = Wrappers.lambdaQuery();
            wrapper.eq(StorePo::getCityId, parentId);
            List<StorePo> childList = storeService.list(wrapper);
            childIds = ToolsUtils.getIdsFromList(childList, ",");
            childScopeId = DictionaryType.SCOPE_ID_SUPERIOR;
        } else if (scopeId.equals(DictionaryType.SCOPE_ID_SUPERIOR)) {
            LambdaQueryWrapper<StorePo> wrapper = Wrappers.lambdaQuery();
            wrapper.eq(StorePo::getParentId, parentId);
            List<StorePo> childList = storeService.list(wrapper);
            childIds = ToolsUtils.getIdsFromList(childList, ",");
            childScopeId = DictionaryType.SCOPE_ID_STORE;
        } else if (scopeId.equals(DictionaryType.SCOPE_ID_STORE)) {
        }
        if (StringUtils.isNotBlank(childIds) && null != childScopeId) {
            resultList = this.findVoList(childScopeId, childIds, currentPeriod, lastPeriod);
        }
        return resultList;
    }

    @Override
    public List<ScoreVo> findVoList(Long scopeId, String storeIds, String currentPeriod, String lastPeriod) {
        Map<String, Object> params = new HashMap<>();
        params.put("current_period", currentPeriod);
        params.put("last_period", lastPeriod);
        params.put("store_ids", storeIds.split(","));
        if (scopeId.equals(DictionaryType.SCOPE_ID_NATIONAL)) {
            return scoreMapper.findNationalVoList(params);
        } else if (scopeId.equals(DictionaryType.SCOPE_ID_REGION)) {
            return scoreMapper.findRegionVoList(params);
        } else if (scopeId.equals(DictionaryType.SCOPE_ID_PROVINCE)) {
            return scoreMapper.findProvinceVoList(params);
        } else if (scopeId.equals(DictionaryType.SCOPE_ID_CITY)) {
            return scoreMapper.findCityVoList(params);
        } else if (scopeId.equals(DictionaryType.SCOPE_ID_SUPERIOR)) {
            return scoreMapper.findStoreVoList(params);
        } else if (scopeId.equals(DictionaryType.SCOPE_ID_STORE)) {
            return scoreMapper.findStoreVoList(params);
        }
        return null;
    }


}

