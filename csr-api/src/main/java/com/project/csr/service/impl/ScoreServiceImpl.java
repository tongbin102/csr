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
import com.project.csr.model.vo.UserStoreVo;
import com.project.csr.security.model.JwtUserDetails;
import com.project.csr.service.*;
import com.project.csr.utils.ConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private UserStoreService userStoreService;


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
    public List<ScoreVo> findScoreInfo(Long scopeId, String parentCode, String currentPeriod, String lastPeriod) {
        List<ScoreVo> resultList = new ArrayList();
        String childCodes = null;
        Long childScopeId = null;

        if (scopeId.equals(DictionaryType.SCOPE_ID_NATIONAL)) {
            LambdaQueryWrapper<RegionPo> wrapper = Wrappers.lambdaQuery();
            wrapper.eq(RegionPo::getNationalCode, parentCode);
            List<RegionPo> childList = regionService.list(wrapper);
            childCodes = childList.stream().map(RegionPo::getCode).collect(Collectors.joining(","));
            childScopeId = DictionaryType.SCOPE_ID_REGION;
        } else if (scopeId.equals(DictionaryType.SCOPE_ID_REGION)) {
            LambdaQueryWrapper<ProvincePo> wrapper = Wrappers.lambdaQuery();
            wrapper.eq(ProvincePo::getRegionCode, parentCode);
            List<ProvincePo> childList = provinceService.list(wrapper);
            childCodes = childList.stream().map(ProvincePo::getCode).collect(Collectors.joining(","));
            childScopeId = DictionaryType.SCOPE_ID_PROVINCE;
        } else if (scopeId.equals(DictionaryType.SCOPE_ID_PROVINCE)) {
            LambdaQueryWrapper<CityPo> wrapper = Wrappers.lambdaQuery();
            wrapper.eq(CityPo::getProvinceCode, parentCode);
            List<CityPo> childList = cityService.list(wrapper);
            childCodes = childList.stream().map(CityPo::getCode).collect(Collectors.joining(","));
            childScopeId = DictionaryType.SCOPE_ID_CITY;
        } else if (scopeId.equals(DictionaryType.SCOPE_ID_CITY)) {
            LambdaQueryWrapper<StorePo> wrapper = Wrappers.lambdaQuery();
            wrapper.eq(StorePo::getCityCode, parentCode)
                    .isNull(StorePo::getParentCode);
            List<StorePo> childList = storeService.list(wrapper);
            childCodes = childList.stream().map(StorePo::getCode).collect(Collectors.joining(","));
            childScopeId = DictionaryType.SCOPE_ID_SUPERIOR;
        } else if (scopeId.equals(DictionaryType.SCOPE_ID_SUPERIOR)) {
            LambdaQueryWrapper<StorePo> wrapper = Wrappers.lambdaQuery();
            wrapper.eq(StorePo::getParentCode, parentCode);
            List<StorePo> childList = storeService.list(wrapper);
            childCodes = childList.stream().map(StorePo::getCode).collect(Collectors.joining(","));
            childScopeId = DictionaryType.SCOPE_ID_STORE;
        } else if (scopeId.equals(DictionaryType.SCOPE_ID_STORE)) {
        }
        if (StringUtils.isNotBlank(childCodes) && null != childScopeId) {
            resultList = this.findVoList(childScopeId, childCodes, currentPeriod, lastPeriod);
        }
        return resultList;
    }

    @Override
    public List<ScoreVo> findVoList(Long scopeId, String storeCodes, String currentPeriod, String lastPeriod) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        JwtUserDetails user = (JwtUserDetails) auth.getPrincipal();
        String username = user.getUsername();
        String userRole = user.getRoleName();
        String userRef = user.getRef();

        Map<String, Object> params = new HashMap<>();
        params.put("current_period", currentPeriod);
        params.put("last_period", lastPeriod);
        params.put("store_codes", storeCodes.split(","));
        if (scopeId.equals(DictionaryType.SCOPE_ID_NATIONAL)) {
            if (isRole(userRole, DictionaryType.ROLE_NAME_ADMIN) || isRole(userRole, DictionaryType.ROLE_NAME_NATIONAL)) {
            } else {
                return null;
            }
            return scoreMapper.findNationalVoList(params);
        } else if (scopeId.equals(DictionaryType.SCOPE_ID_REGION)) {
            if (isRole(userRole, DictionaryType.ROLE_NAME_ADMIN) || isRole(userRole, DictionaryType.ROLE_NAME_NATIONAL)) {
            } else if ((isRole(userRole, DictionaryType.ROLE_NAME_REGION) && storeCodes.equals(userRef))
                    || (isRole(userRole, DictionaryType.ROLE_NAME_AREA) && storeCodes.equals(userRef))) {
            } else {
                return null;
            }
            return scoreMapper.findRegionVoList(params);
        } else if (scopeId.equals(DictionaryType.SCOPE_ID_PROVINCE)) {
            if (isRole(userRole, DictionaryType.ROLE_NAME_ADMIN) || isRole(userRole, DictionaryType.ROLE_NAME_NATIONAL)) {
            } else if (isRole(userRole, DictionaryType.ROLE_NAME_REGION) || isRole(userRole, DictionaryType.ROLE_NAME_AREA)) {
                String provinceCodes = userStoreService.findVoByUserCode(username).stream().map(UserStoreVo::getProvinceCode).distinct().collect(Collectors.joining(","));
                params.put("permission_codes", provinceCodes.split(","));
            } else {
                return null;
            }
            return scoreMapper.findProvinceVoList(params);
        } else if (scopeId.equals(DictionaryType.SCOPE_ID_CITY)) {
            if (isRole(userRole, DictionaryType.ROLE_NAME_ADMIN) || isRole(userRole, DictionaryType.ROLE_NAME_NATIONAL)) {
            } else if (isRole(userRole, DictionaryType.ROLE_NAME_REGION) || isRole(userRole, DictionaryType.ROLE_NAME_AREA)) {
                String cityCodes = userStoreService.findVoByUserCode(username).stream().map(UserStoreVo::getCityCode).distinct().collect(Collectors.joining(","));
                params.put("permission_codes", cityCodes.split(","));
            } else{
                return null;
            }
            return scoreMapper.findCityVoList(params);
        } else if (scopeId.equals(DictionaryType.SCOPE_ID_SUPERIOR)) {
            if (isRole(userRole, DictionaryType.ROLE_NAME_ADMIN) || isRole(userRole, DictionaryType.ROLE_NAME_NATIONAL)) {
            } else if (isRole(userRole, DictionaryType.ROLE_NAME_REGION)
                    || isRole(userRole, DictionaryType.ROLE_NAME_AREA)
                    || (isRole(userRole, DictionaryType.ROLE_NAME_SUPERIOR) && storeCodes.equals(userRef))) {
                String superiorCodes = userStoreService.findVoByUserCode(username).stream().map(UserStoreVo::getSuperiorCode).distinct().collect(Collectors.joining(","));
                params.put("permission_codes", superiorCodes.split(","));
            } else {
                return null;
            }
            return scoreMapper.findSuperiorVoList(params);
        } else if (scopeId.equals(DictionaryType.SCOPE_ID_STORE)) {
            if (isRole(userRole, DictionaryType.ROLE_NAME_ADMIN) || isRole(userRole, DictionaryType.ROLE_NAME_NATIONAL)) {
            } else if (isRole(userRole, DictionaryType.ROLE_NAME_REGION)
                    || isRole(userRole, DictionaryType.ROLE_NAME_AREA)
                    || isRole(userRole, DictionaryType.ROLE_NAME_SUPERIOR)
                    || (isRole(userRole, DictionaryType.ROLE_NAME_STORE) && storeCodes.equals(userRef))) {
                String codes = userStoreService.findVoByUserCode(username).stream().map(UserStoreVo::getStoreCode).distinct().collect(Collectors.joining(","));
                params.put("permission_codes", codes.split(","));
            } else {
                return null;
            }

            return scoreMapper.findStoreVoList(params);
        }
        return null;
    }

    @Override
    public List<ScoreVo> findVoListByPeriods(Long scopeId, String storeCode, String beginPeriod, String endPeriod) {
        LambdaQueryWrapper<ScorePo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ScorePo::getScopeId, scopeId)
                .eq(ScorePo::getStoreCode, storeCode)
                .between(ScorePo::getPeriod, beginPeriod, endPeriod);
        return ConvertUtils.convert(scoreMapper.selectList(wrapper), ScoreVo.class);
    }

    @Override
    public boolean deleteByPeriod(String period) {
        LambdaQueryWrapper<ScorePo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ScorePo::getPeriod, period);
        return scoreMapper.delete(wrapper) >= 1;
    }

    private boolean isRole(String userRole, String roleName) {
        return userRole.contains(roleName);
    }

}

