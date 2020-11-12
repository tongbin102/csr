package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.QuestionSurveyMapper;
import com.project.csr.model.po.QuestionSurveyPo;
import com.project.csr.model.po.RegulationPo;
import com.project.csr.model.po.ScoreQuestionPo;
import com.project.csr.model.vo.QuestionSurveyVo;
import com.project.csr.service.QuestionSurveyService;
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
 * 客户调研评分规则表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-10
 */
@Service
public class QuestionSurveyServiceImpl extends ServiceImpl<QuestionSurveyMapper, QuestionSurveyPo> implements QuestionSurveyService {

    @Autowired
    private QuestionSurveyMapper questionSurveyMapper;

    @Autowired
    private RegulationService regulationService;

    @Autowired
    private ScoreQuestionService scoreQuestionService;

    @Override
    public IPage<QuestionSurveyPo> findListByPage(QuestionSurveyVo questionSurveyVo) {
        IPage<QuestionSurveyPo> page = new Page<>(questionSurveyVo.getPageNo(), questionSurveyVo.getPageSize());
        LambdaQueryWrapper<QuestionSurveyPo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<QuestionSurveyPo> selectPage = questionSurveyMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        QuestionSurveyPo po = new QuestionSurveyPo();
        po.setValidInd(false);
        LambdaQueryWrapper<QuestionSurveyPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(QuestionSurveyPo::getId, id);
        return questionSurveyMapper.update(po, wrapper) >= 1;
    }

    @Override
    public List<QuestionSurveyVo> findListByRegulationId(String period, Long storeId, Long regulationId) {
        LambdaQueryWrapper<QuestionSurveyPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(QuestionSurveyPo::getRegulationId, regulationId);
        List<QuestionSurveyVo> questionSurveyVoList = ConvertUtils.convert(questionSurveyMapper.selectList(wrapper), QuestionSurveyVo.class);
        String delimiter = ",";
        String questionIds = ToolsUtils.getIdsFromList(questionSurveyVoList, delimiter);
        List<RegulationPo> regulationPoList = regulationService.findListFromIds(questionIds, delimiter);
        List<ScoreQuestionPo> scoreQuestionPoList = scoreQuestionService.findByStoreAndQuestionIds(period, storeId, questionIds);

        questionSurveyVoList.stream().forEach(questionSurveyVo -> {
            // 获取类别
            List<RegulationPo> list1 = regulationPoList.stream().filter(r -> r.getId().equals(questionSurveyVo.getRegulationId().toString())).collect(Collectors.toList());
            if (list1.size() > 0) {
                questionSurveyVo.setScoreType(list1.get(0).getScoreType());
            }
            List<ScoreQuestionPo> list2 = scoreQuestionPoList.stream().filter(q -> q.getQuestionId().toString().equals(questionSurveyVo.getId())).collect(Collectors.toList());
            if (list2.size() > 0) {
                questionSurveyVo.setGrade(list2.get(0).getGrade());
            }
        });

        return questionSurveyVoList;
    }
}

