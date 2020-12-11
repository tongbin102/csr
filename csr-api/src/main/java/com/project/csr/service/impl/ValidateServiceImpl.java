package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.ValidateMapper;
import com.project.csr.model.po.UserPo;
import com.project.csr.model.po.ValidatePo;
import com.project.csr.model.vo.ValidateVo;
import com.project.csr.service.ValidateService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 验证表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-12-11
 */
@Service
public class ValidateServiceImpl extends ServiceImpl<ValidateMapper, ValidatePo> implements ValidateService {

    @Autowired
    private ValidateMapper validateMapper;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public IPage<ValidatePo> findListByPage(ValidateVo validateVo) {
        IPage<ValidatePo> page = new Page<>(validateVo.getPageNo(), validateVo.getPageSize());
        LambdaQueryWrapper<ValidatePo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<ValidatePo> selectPage = validateMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        ValidatePo po = new ValidatePo();
        po.setValidInd(false);
        LambdaQueryWrapper<ValidatePo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ValidatePo::getId, id);
        return validateMapper.update(po, wrapper) >= 1;
    }

    @Async
    @Override
    public void sendPasswordResetEmail(MimeMessage email) {
        javaMailSender.send(email);
    }

    @Override
    public boolean insertNewResetRecord(ValidatePo validatePo, UserPo userPo, String token) {
        validatePo.setUsername(userPo.getUsername());
        validatePo.setEmail(userPo.getEmail());
        validatePo.setResetToken(token);
        validatePo.setType("passwordReset");
        return validateMapper.insert(validatePo) >= 1;
    }

    @Override
    public boolean sendValidateLimitation(String email, long requestPerDay, long interval) {
        LambdaQueryWrapper<ValidatePo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ValidatePo::getEmail, email);
        List<ValidatePo> validatePoList = validateMapper.selectList(wrapper);
        if (validatePoList.isEmpty()) {
            return true;
        }
        // 有记录，则判断是否频繁申请以及是否达到日均请求上限
        long countTodayValidation = validatePoList.stream().filter(validatePo -> DateUtils.isSameDay(validatePo.getUpdateTime(), new Date())).count();
        Optional<Date> validate = validatePoList.stream().map(ValidatePo::getUpdateTime).max(Date::compareTo);
        Date dateOfLastRequest = new Date();
        if (validate.isPresent()) {
            dateOfLastRequest = (Date) validate.get();
        }
        long intervalForLastRequest = new Date().getTime() - dateOfLastRequest.getTime();
        return countTodayValidation <= requestPerDay && intervalForLastRequest >= interval * 60 * 1000;

    }

    @Override
    public boolean validateLimitation(String email, long requestPerDay, long interval, String token) {
        LambdaQueryWrapper<ValidatePo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ValidatePo::getEmail, email);
        List<ValidatePo> validatePoList = validateMapper.selectList(wrapper);
        // if (validatePoList.isEmpty()) {
        //     return true;
        // }
        // 有记录才会调用该函数，只需判断是否超时
        Optional<Date> validate = validatePoList.stream().map(ValidatePo::getUpdateTime).max(Date::compareTo);
        Date dateOfLastRequest = new Date();
        if (validate.isPresent()) {
            dateOfLastRequest = (Date) validate.get();
        }
        long intervalForLastRequest = new Date().getTime() - dateOfLastRequest.getTime();

        Optional lastRequestToken = validatePoList.stream().filter(validatePo -> validatePo.getResetToken().equals(token)).map(ValidatePo::getUpdateTime).findAny();
        Date dateOfLastRequestToken = new Date();
        if (lastRequestToken.isPresent()) {
            dateOfLastRequestToken = (Date) lastRequestToken.get();
        }
        return intervalForLastRequest <= interval * 60 * 1000 && dateOfLastRequest == dateOfLastRequestToken;
    }
}

