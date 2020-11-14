package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.constants.DictionaryType;
import com.project.csr.dao.RegulationMapper;
import com.project.csr.model.po.ElementPo;
import com.project.csr.model.po.RegulationPo;
import com.project.csr.model.vo.RegulationScoreChannelVo;
import com.project.csr.model.vo.RegulationScoreVo;
import com.project.csr.model.vo.RegulationVo;
import com.project.csr.service.ElementService;
import com.project.csr.service.RegulationScoreChannelService;
import com.project.csr.service.RegulationScoreService;
import com.project.csr.service.RegulationService;
import com.project.csr.utils.ConvertUtils;
import com.project.csr.utils.ToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 细则表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-11
 */
@Service
public class RegulationServiceImpl extends ServiceImpl<RegulationMapper, RegulationPo> implements RegulationService {

    @Autowired
    private RegulationMapper regulationMapper;

    @Autowired
    private ElementService elementService;

    @Autowired
    private RegulationScoreService regulationScoreService;

    @Autowired
    private RegulationScoreChannelService regulationScoreChannelService;

    @Override
    public IPage<RegulationPo> findListByPage(RegulationVo regulationVo) {
        IPage<RegulationPo> page = new Page<>(regulationVo.getPageNo(), regulationVo.getPageSize());
        LambdaQueryWrapper<RegulationPo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<RegulationPo> selectPage = regulationMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        RegulationPo po = new RegulationPo();
        po.setValidInd(false);
        LambdaQueryWrapper<RegulationPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(RegulationPo::getId, id);
        return regulationMapper.update(po, wrapper) >= 1;
    }

    @Override
    public List<RegulationPo> findListFromIds(String ids, String delimiter) {
        LambdaQueryWrapper<RegulationPo> wrapper = Wrappers.lambdaQuery();
        wrapper.in(RegulationPo::getId, ids.split(delimiter));
        return regulationMapper.selectList(wrapper);
    }

    @Override
    public RegulationVo findVoById(Long id) {
        RegulationVo regulationVo = ConvertUtils.convert(this.getById(id), RegulationVo.class);
        ElementPo elementPo = elementService.getById(regulationVo.getElementId());
        regulationVo.setElementName(elementPo.getName());
        return regulationVo;
    }

    @Override
    public List<RegulationVo> findVoListByFactorId(Long factorId) {
        Map<String, Object> params = new HashMap<>();
        params.put("factor_id", factorId);
        return regulationMapper.findVoList(params);
    }

    @Override
    public List<RegulationVo> findInfo(Long storeId, String period, Long factorId) {
        List<RegulationVo> regulationVoList = this.findVoListByFactorId(factorId);
        String regulationIds = ToolsUtils.getIdsFromList(regulationVoList, ",");

        List<RegulationScoreVo> regulationScoreVoList = regulationScoreService.findVoList(storeId, period, regulationIds);
        List<RegulationScoreChannelVo> regulationScoreChannelVoList = regulationScoreChannelService.findVoList(storeId, period, regulationIds);

        regulationVoList.stream().forEach(item -> {
            Map<String, Object> regulationScoreVoMap = new HashMap<>();
            regulationScoreVoList.stream()
                    .filter(r -> r.getRegulationId().equals(Long.parseLong(item.getId())))
                    .forEach(r -> {
                        if (r.getScoreType().equals(DictionaryType.SCORE_TYPE_ID_EVALUATE)) {
                            regulationScoreVoMap.put("evaluateScore", r.getScore());
                        }
                        if (r.getScoreType().equals(DictionaryType.SCORE_TYPE_ID_BONUS)) {
                            regulationScoreVoMap.put("bonusScore", r.getScore());
                        }
                    });
            item.setRegulationScoreMap(regulationScoreVoMap);
            Map<String, Object> regulationScoreChannelVoMap = new HashMap<>();
            regulationScoreChannelVoList.stream()
                    .filter(r -> r.getRegulationId().equals(Long.parseLong(item.getId())))
                    .forEach(r -> {
                        if (r.getScoreType().equals(DictionaryType.SCORE_TYPE_ID_EVALUATE)) {
                            regulationScoreChannelVoMap.put("evaluateChannelScore" + r.getChannelId(), r.getGrade());
                        }
                        if (r.getScoreType().equals(DictionaryType.SCORE_TYPE_ID_BONUS)) {
                            regulationScoreChannelVoMap.put("bonusChannelScore" + r.getChannelId(), r.getGrade());
                        }
                    });

            item.setRegulationScoreChannelMap(regulationScoreChannelVoMap);
        });

        return regulationVoList;
    }

    private Map<String, Object> getRegulationScoreMap(Long regulationId, List<Map<String, Object>> mapList) {
        for (int i = 0; i < mapList.size(); i++) {
            Map<String, Object> map = mapList.get(i);
            if (map.get("regulation_id").equals(regulationId)) {
                return map;
            }
        }
        return null;
    }
}

