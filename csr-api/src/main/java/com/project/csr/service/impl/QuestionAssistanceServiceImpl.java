package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.constants.DictionaryType;
import com.project.csr.dao.QuestionAssistanceMapper;
import com.project.csr.model.po.QuestionAssistancePo;
import com.project.csr.model.po.RegulationPo;
import com.project.csr.model.po.ScoreQuestionPo;
import com.project.csr.model.vo.QuestionAssistanceVo;
import com.project.csr.service.QuestionAssistanceService;
import com.project.csr.service.RegulationService;
import com.project.csr.service.ScoreQuestionService;
import com.project.csr.utils.ConvertUtils;
import com.project.csr.utils.ToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务助手评分规则表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-10
 */
@Service
public class QuestionAssistanceServiceImpl extends ServiceImpl<QuestionAssistanceMapper, QuestionAssistancePo> implements QuestionAssistanceService {

    @Autowired
    private QuestionAssistanceMapper questionAssistanceMapper;

    @Autowired
    private RegulationService regulationService;

    @Autowired
    private ScoreQuestionService scoreQuestionService;

    @Override
    public IPage<QuestionAssistancePo> findListByPage(QuestionAssistanceVo questionAssistanceVo) {
        IPage<QuestionAssistancePo> page = new Page<>(questionAssistanceVo.getPageNo(), questionAssistanceVo.getPageSize());
        LambdaQueryWrapper<QuestionAssistancePo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<QuestionAssistancePo> selectPage = questionAssistanceMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        QuestionAssistancePo po = new QuestionAssistancePo();
        po.setValidInd(false);
        LambdaQueryWrapper<QuestionAssistancePo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(QuestionAssistancePo::getId, id);
        return questionAssistanceMapper.update(po, wrapper) >= 1;
    }

    @Override
    public List<QuestionAssistanceVo> findListByRegulationId(String period, String storeCode, Long regulationId) {
        RegulationPo regulationPo = regulationService.getById(regulationId);
        LambdaQueryWrapper<QuestionAssistancePo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(QuestionAssistancePo::getRegulationDescription, regulationPo.getElementCode() + ";" + regulationPo.getDescription());
        List<QuestionAssistanceVo> questionAssistanceVoList = ConvertUtils.convert(questionAssistanceMapper.selectList(wrapper), QuestionAssistanceVo.class);
        String questionIds = ToolsUtils.getIdsFromList(questionAssistanceVoList, ",");
        List<RegulationPo> regulationPoList = regulationService.findListFromIds(questionIds, ",");
        List<ScoreQuestionPo> scoreQuestionPoList = scoreQuestionService.findByStoreAndQuestionIds(period, storeCode, DictionaryType.CHANNEL_CODE_ASSISTANCE, questionIds);

        questionAssistanceVoList.stream().forEach(questionAssistanceVo -> {
            // 获取类别
            List<RegulationPo> list1 = regulationPoList.stream().filter(r -> r.getId().equals(questionAssistanceVo.getRegulationDescription().toString())).collect(Collectors.toList());
            if (list1.size() > 0) {
                questionAssistanceVo.setScoreType(list1.get(0).getScoreType());
            }
            List<ScoreQuestionPo> list2 = scoreQuestionPoList.stream().filter(q -> q.getQuestionSeriesNo().toString().equals(questionAssistanceVo.getSeriesNo())).collect(Collectors.toList());
            if (list2.size() > 0) {
                questionAssistanceVo.setGrade(list2.get(0).getGrade());
            }
        });

        return questionAssistanceVoList;
    }

    @Override
    public boolean deleteAll() {
        LambdaQueryWrapper<QuestionAssistancePo> wrapper = Wrappers.lambdaQuery();
        return questionAssistanceMapper.delete(wrapper) >= 1;
    }
}

