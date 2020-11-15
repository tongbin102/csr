package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.constants.DictionaryType;
import com.project.csr.dao.QuestionDataMapper;
import com.project.csr.model.po.QuestionDataPo;
import com.project.csr.model.po.RegulationPo;
import com.project.csr.model.po.ScoreQuestionPo;
import com.project.csr.model.vo.QuestionDataVo;
import com.project.csr.service.QuestionDataService;
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
 * 道路救援评分规则表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-15
 */
@Service
public class QuestionDataServiceImpl extends ServiceImpl<QuestionDataMapper, QuestionDataPo> implements QuestionDataService {

    @Autowired
    private QuestionDataMapper questionDataMapper;

    @Autowired
    private RegulationService regulationService;

    @Autowired
    private ScoreQuestionService scoreQuestionService;

    @Override
    public IPage<QuestionDataPo> findListByPage(QuestionDataVo questionDataVo) {
        IPage<QuestionDataPo> page = new Page<>(questionDataVo.getPageNo(), questionDataVo.getPageSize());
        LambdaQueryWrapper<QuestionDataPo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<QuestionDataPo> selectPage = questionDataMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        QuestionDataPo po = new QuestionDataPo();
        po.setValidInd(false);
        LambdaQueryWrapper<QuestionDataPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(QuestionDataPo::getId, id);
        return questionDataMapper.update(po, wrapper) >= 1;
    }

    @Override
    public List<QuestionDataVo> findListByRegulationId(String period, Long storeId, Long regulationId) {
        LambdaQueryWrapper<QuestionDataPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(QuestionDataPo::getRegulationId, regulationId);
        List<QuestionDataVo> questionDataVoList = ConvertUtils.convert(questionDataMapper.selectList(wrapper), QuestionDataVo.class);
        String questionIds = ToolsUtils.getIdsFromList(questionDataVoList, ",");
        List<RegulationPo> regulationPoList = regulationService.findListFromIds(questionIds, ",");
        List<ScoreQuestionPo> scoreQuestionPoList = scoreQuestionService.findByStoreAndQuestionIds(period, storeId, DictionaryType.CHANNEL_ID_DATA, questionIds);

        questionDataVoList.stream().forEach(questionDataVo -> {
            // 获取类别
            List<RegulationPo> list1 = regulationPoList.stream().filter(r -> r.getId().equals(questionDataVo.getRegulationId().toString())).collect(Collectors.toList());
            if (list1.size() > 0) {
                questionDataVo.setScoreType(list1.get(0).getScoreType());
            }
            List<ScoreQuestionPo> list2 = scoreQuestionPoList.stream().filter(q -> q.getQuestionId().toString().equals(questionDataVo.getId())).collect(Collectors.toList());
            if (list2.size() > 0) {
                questionDataVo.setScore(list2.get(0).getScore());
                questionDataVo.setGrade(list2.get(0).getGrade());
            }
        });

        return questionDataVoList;
    }
}

