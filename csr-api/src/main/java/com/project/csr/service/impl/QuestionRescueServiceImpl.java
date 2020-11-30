package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.constants.DictionaryType;
import com.project.csr.dao.QuestionRescueMapper;
import com.project.csr.model.po.QuestionRescuePo;
import com.project.csr.model.po.RegulationPo;
import com.project.csr.model.po.ScoreQuestionPo;
import com.project.csr.model.vo.QuestionRescueVo;
import com.project.csr.service.QuestionRescueService;
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
public class QuestionRescueServiceImpl extends ServiceImpl<QuestionRescueMapper, QuestionRescuePo> implements QuestionRescueService {

    @Autowired
    private QuestionRescueMapper questionRescueMapper;

    @Autowired
    private RegulationService regulationService;

    @Autowired
    private ScoreQuestionService scoreQuestionService;

    @Override
    public IPage<QuestionRescuePo> findListByPage(QuestionRescueVo questionRescueVo) {
        IPage<QuestionRescuePo> page = new Page<>(questionRescueVo.getPageNo(), questionRescueVo.getPageSize());
        LambdaQueryWrapper<QuestionRescuePo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<QuestionRescuePo> selectPage = questionRescueMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        QuestionRescuePo po = new QuestionRescuePo();
        po.setValidInd(false);
        LambdaQueryWrapper<QuestionRescuePo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(QuestionRescuePo::getId, id);
        return questionRescueMapper.update(po, wrapper) >= 1;
    }

    @Override
    public List<QuestionRescueVo> findListByRegulationId(String period, String storeCode, Long regulationId) {
        RegulationPo regulationPo = regulationService.getById(regulationId);
        LambdaQueryWrapper<QuestionRescuePo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(QuestionRescuePo::getRegulationDescription, regulationPo.getElementCode() + ";" + regulationPo.getDescription());
        List<QuestionRescueVo> questionRescueVoList = ConvertUtils.convert(questionRescueMapper.selectList(wrapper), QuestionRescueVo.class);
        String questionIds = ToolsUtils.getIdsFromList(questionRescueVoList, ",");
        List<RegulationPo> regulationPoList = regulationService.findListFromIds(questionIds, ",");
        List<ScoreQuestionPo> scoreQuestionPoList = scoreQuestionService.findByStoreAndQuestionIds(period, storeCode, DictionaryType.CHANNEL_CODE_RESCUE, questionIds);

        questionRescueVoList.stream().forEach(questionRescueVo -> {
            // 获取类别
            List<RegulationPo> list1 = regulationPoList.stream().filter(r -> r.getId().equals(questionRescueVo.getRegulationDescription().toString())).collect(Collectors.toList());
            if (list1.size() > 0) {
                questionRescueVo.setScoreType(list1.get(0).getScoreType());
            }
            List<ScoreQuestionPo> list2 = scoreQuestionPoList.stream().filter(q -> q.getQuestionSeriesNo().toString().equals(questionRescueVo.getSeriesNo())).collect(Collectors.toList());
            if (list2.size() > 0) {
                questionRescueVo.setScore(list2.get(0).getScore());
                questionRescueVo.setGrade(list2.get(0).getGrade());
            }
        });

        return questionRescueVoList;
    }

    @Override
    public boolean deleteAll() {
        LambdaQueryWrapper<QuestionRescuePo> wrapper = Wrappers.lambdaQuery();
        return questionRescueMapper.delete(wrapper) >= 1;
    }
}

