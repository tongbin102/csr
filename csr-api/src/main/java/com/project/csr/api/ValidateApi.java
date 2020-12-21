package com.project.csr.api;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.project.csr.common.enums.ResCodeEnum;
import com.project.csr.common.exceptions.ServiceException;
import com.project.csr.constants.CsrConstant;
import com.project.csr.model.po.UserPo;
import com.project.csr.model.po.ValidatePo;
import com.project.csr.model.vo.ValidateVo;
import com.project.csr.service.UserService;
import com.project.csr.service.ValidateService;
import com.project.csr.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 验证表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-12-11
 */
@Slf4j
@Api(tags = {"ValidateApi"}, value = "验证表")
@RestController
@RequestMapping("/validateApi")
public class ValidateApi {

    @Autowired
    private ValidateService validateService;

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PasswordEncoder encoder;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${spring.mail.requestPerDay}")
    private long requestPerDay;

    @ApiOperation(value = "查询分页验证表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<ValidatePo> findListByPage(@RequestBody ValidateVo validateVo) {
        return validateService.findListByPage(validateVo);
    }

    @ApiOperation(value = "根据id查询验证表数据")
    @GetMapping(value = "/findById/{id}")
    public ValidateVo findById(@PathVariable("id") String id) {
        ValidatePo po = validateService.getById(id);
        return ConvertUtils.convert(po, ValidateVo.class);
    }

    @ApiOperation(value = "新增验证表数据")
    @PostMapping(value = "/add")
    public ValidateVo add(@RequestBody ValidateVo validateVo) {
        ValidatePo po = ConvertUtils.convert(validateVo, ValidatePo.class);
        validateService.save(po);
        return ConvertUtils.convert(po, ValidateVo.class);
    }

    @ApiOperation(value = "删除验证表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id) {
        return validateService.removeById(id);
    }

    @ApiOperation(value = "更新验证表数据")
    @PutMapping(value = "/update")
    public ValidateVo update(@RequestBody ValidateVo validateVo) {
        ValidatePo po = ConvertUtils.convert(validateVo, ValidatePo.class);
        validateService.updateById(po);
        return ConvertUtils.convert(po, ValidateVo.class);
    }

    @ApiOperation("根据ID禁用验证表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return validateService.prohibitById(id);
    }

    @ApiOperation(value = "发送忘记密码邮件", notes = "发送忘记密码邮件")
    @PostMapping(value = "/sendValidationEmail")
    public Map<String, Object> sendValidationEmail(@ApiParam("邮箱地址") @RequestParam("email") String email, HttpServletRequest request) throws MessagingException {
        Map<String, Object> resultMap = new HashMap<>();
        LambdaQueryWrapper<UserPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserPo::getEmail, email);
        UserPo userPo = userService.getOne(wrapper);
        if (null == userPo) {
            throw new ServiceException(ResCodeEnum.RESCODE_BAD_REQUEST, "邮箱所属用户不存在");
        }
        if (validateService.sendValidateLimitation(email, requestPerDay, 1)) {

            ValidatePo validatePo = new ValidatePo();
            validateService.insertNewResetRecord(validatePo, userPo, UUID.randomUUID().toString());
            // 设置邮件内容
            // String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
//            String appUrl = request.getScheme() + "://" + request.getServerName() + ":8000";
            String appUrl = "http://wlcsi.sgmwsales.com:8443";
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            // multipart模式
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setSubject("重置密码");
            StringBuilder sb = new StringBuilder();
            sb.append("<html><head></head>");
            sb.append("<body><h1>点击下面的链接重置密码</h1>" +
                    "<a href = " + appUrl + "/user/resetPassword?token=" + validatePo.getResetToken() + ">" + appUrl + "/user/resetPassword?token=" + validatePo.getResetToken() + "</a></body>");
            sb.append("</html>");
            // 启用html
            mimeMessageHelper.setText(sb.toString(), true);
            validateService.sendPasswordResetEmail(mimeMessage);
            resultMap.put("token", validatePo.getResetToken());
            resultMap.put("link", appUrl + "/validate/resetPassword?token=" + validatePo.getResetToken());
            resultMap.put("message", "邮件已经发送");
        } else {
            throw new ServiceException(ResCodeEnum.RESCODE_BAD_REQUEST, "操作过于频繁，请稍后再试");
        }
        return resultMap;
    }

    @ApiOperation(value = "重置密码,邮箱中的token有效时间为5分钟,每天每个用户最多发10次邮件", notes = "重置密码")
    @PostMapping(value = "/resetPassword")
    public String resetPassword(@ApiParam("token") @RequestParam("token") String token,
                                @ApiParam("密码") @RequestParam("password") String password,
                                @ApiParam("密码确认") @RequestParam("confirmPassword") String confirmPassword) {
        String strResult = null;
        // 通过token找到validate记录
        LambdaQueryWrapper<ValidatePo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ValidatePo::getResetToken, token);
        ValidatePo validatePo = validateService.getOne(wrapper);
        if (validatePo == null) {
            throw new ServiceException(ResCodeEnum.RESCODE_BAD_REQUEST, "该重置请求不存在");
        } else {
            if (validateService.validateLimitation(validatePo.getEmail(), Long.MAX_VALUE, 5, token)) {
                String username = validatePo.getUsername();
                if (password.equals(confirmPassword)) {
                    UserPo userPo = new UserPo();
                    userPo.setPassword(encoder.encode(CsrConstant.DEFAULT_RAW_PASSWORD));
                    // userPo.setUsername(username);
                    UserPo tmpUserPo = userService.findByUsername(username);
                    if (null == tmpUserPo) {
                        throw new ServiceException(ResCodeEnum.RESCODE_BAD_REQUEST, "用户不存在");
                    }
                    userPo.setId(tmpUserPo.getId());
                    userService.updateById(userPo);
                    strResult = "成功重置密码";
                } else {
                    throw new ServiceException(ResCodeEnum.RESCODE_BAD_REQUEST, "确认密码和密码不一致，请重新输入");
                }
            } else {
                throw new ServiceException(ResCodeEnum.RESCODE_BAD_REQUEST, "链接失效");
            }
        }
        return strResult;
    }
}
