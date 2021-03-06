package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.constants.DictionaryType;
import com.project.csr.dao.QuestionSurveyMapper;
import com.project.csr.model.po.QuestionSurveyPo;
import com.project.csr.model.po.RegionPo;
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
    public List<QuestionSurveyVo> findListByRegulationId(String period, String storeCode, Long regulationId) {
        RegulationPo regulationPo = regulationService.getById(regulationId);
        LambdaQueryWrapper<QuestionSurveyPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(QuestionSurveyPo::getRegulationDescription, regulationPo.getElementCode() + ";" + regulationPo.getDescription());
        List<QuestionSurveyVo> questionSurveyVoList = ConvertUtils.convert(questionSurveyMapper.selectList(wrapper), QuestionSurveyVo.class);
        // String questionIds = ToolsUtils.getIdsFromList(questionSurveyVoList, ",");
        String questionSeriesNos = questionSurveyVoList.stream().map(QuestionSurveyVo::getSeriesNo).collect(Collectors.joining(","));

        List<ScoreQuestionPo> scoreQuestionPoList = scoreQuestionService.findByStoreAndQuestionSeriesNos(period, storeCode, DictionaryType.CHANNEL_CODE_SURVEY, questionSeriesNos);

        questionSurveyVoList.stream().forEach(questionSurveyVo -> {
            List<ScoreQuestionPo> list = scoreQuestionPoList.stream().filter(q -> q.getQuestionSeriesNo().toString().equals(questionSurveyVo.getSeriesNo())).collect(Collectors.toList());
            if (list.size() > 0) {
                questionSurveyVo.setScore(list.get(0).getScore());
                questionSurveyVo.setGrade(list.get(0).getGrade());
            }
        });

        return questionSurveyVoList;
    }

    @Override
    public boolean deleteAll() {
        LambdaQueryWrapper<QuestionSurveyPo> wrapper = Wrappers.lambdaQuery();
        return questionSurveyMapper.delete(wrapper) >= 1;
    }
}

