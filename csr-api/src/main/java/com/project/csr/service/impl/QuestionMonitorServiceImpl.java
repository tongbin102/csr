package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.constants.DictionaryType;
import com.project.csr.dao.QuestionMonitorMapper;
import com.project.csr.model.po.QuestionMonitorPo;
import com.project.csr.model.po.RegulationPo;
import com.project.csr.model.po.ScoreQuestionPo;
import com.project.csr.model.vo.QuestionMonitorVo;
import com.project.csr.model.vo.QuestionSurveyVo;
import com.project.csr.service.QuestionMonitorService;
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
 * 过程监控评分规则表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-10
 */
@Service
public class QuestionMonitorServiceImpl extends ServiceImpl<QuestionMonitorMapper, QuestionMonitorPo> implements QuestionMonitorService {

    @Autowired
    private QuestionMonitorMapper questionMonitorMapper;

    @Autowired
    private RegulationService regulationService;

    @Autowired
    private ScoreQuestionService scoreQuestionService;

    @Override
    public IPage<QuestionMonitorPo> findListByPage(QuestionMonitorVo questionMonitorVo) {
        IPage<QuestionMonitorPo> page = new Page<>(questionMonitorVo.getPageNo(), questionMonitorVo.getPageSize());
        LambdaQueryWrapper<QuestionMonitorPo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<QuestionMonitorPo> selectPage = questionMonitorMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        QuestionMonitorPo po = new QuestionMonitorPo();
        po.setValidInd(false);
        LambdaQueryWrapper<QuestionMonitorPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(QuestionMonitorPo::getId, id);
        return questionMonitorMapper.update(po, wrapper) >= 1;
    }

    @Override
    public List<QuestionMonitorVo> findListByRegulationId(String period, String storeCode, Long regulationId) {
        RegulationPo regulationPo = regulationService.getById(regulationId);
        LambdaQueryWrapper<QuestionMonitorPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(QuestionMonitorPo::getRegulationDescription, regulationPo.getElementCode() + ";" + regulationPo.getDescription());
        List<QuestionMonitorVo> questionMonitorVoList = ConvertUtils.convert(questionMonitorMapper.selectList(wrapper), QuestionMonitorVo.class);
        // String questionIds = ToolsUtils.getIdsFromList(questionMonitorVoList, ",");
        String questionSeriesNos = questionMonitorVoList.stream().map(QuestionMonitorVo::getSeriesNo).collect(Collectors.joining(","));
        List<RegulationPo> regulationPoList = regulationService.findListFromIds(questionSeriesNos, ",");
        List<ScoreQuestionPo> scoreQuestionPoList = scoreQuestionService.findByStoreAndQuestionSeriesNos(period, storeCode, DictionaryType.CHANNEL_CODE_MONITOR, questionSeriesNos);

        questionMonitorVoList.stream().forEach(questionMonitorVo -> {
            // 获取类别
            List<ScoreQuestionPo> list = scoreQuestionPoList.stream().filter(q -> q.getQuestionSeriesNo().toString().equals(questionMonitorVo.getSeriesNo())).collect(Collectors.toList());
            if (list.size() > 0) {
                questionMonitorVo.setGrade(list.get(0).getGrade());
            }
        });

        return questionMonitorVoList;
    }

    @Override
    public boolean deleteAll() {
        LambdaQueryWrapper<QuestionMonitorPo> wrapper = Wrappers.lambdaQuery();
        return questionMonitorMapper.delete(wrapper) >= 1;
    }
}

