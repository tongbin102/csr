
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.UserPo;
import com.project.csr.model.po.ValidatePo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.vo.ValidateVo;

import javax.mail.internet.MimeMessage;

/**
 * <p>
 * 验证表 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-12-11
 */
public interface ValidateService extends IService<ValidatePo> {

    /**
     * 分页查询
     *
     * @param validateVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.ValidatePo>
     * @author bin.tong
     * @since 2020-12-11
     */
    IPage<ValidatePo> findListByPage(ValidateVo validateVo);

    /**
     * 根据ID禁用数据
     *
     * @param id 主键id
     * @return boolean
     * @author bin.tong
     * @since 2020-12-11
     */
    boolean prohibitById(String id);

    /**
     * 发送邮件：@Async进行异步调用发送邮件接口
     *
     * @param email
     */
    void sendPasswordResetEmail(MimeMessage email);

    /**
     * 验证是否发送重置邮件：每个email的重置密码每日请求上限为requestPerDay次，与上一次的请求时间间隔为interval分钟。
     *
     * @param email
     * @param requestPerDay
     * @param interval
     * @return
     */
    boolean sendValidateLimitation(String email, long requestPerDay, long interval);

    /**
     * 验证连接是否失效：链接有两种情况失效 1.超时 2.最近请求的一次链接自动覆盖之前的链接（待看代码）
     *
     * @param email
     * @param requestPerDay
     * @param interval
     * @param token
     * @return
     */
    boolean validateLimitation(String email, long requestPerDay, long interval, String token);

    /**
     * 插入一条记录，userid，email属性来自user表，token由UUID生成
     * @param validatePo
     * @param userPo
     * @param token
     * @return
     */
    boolean insertNewResetRecord(ValidatePo validatePo, UserPo userPo, String token);
}