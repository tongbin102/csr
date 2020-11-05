package com.project.csr.model.vo;


import com.project.csr.model.po.QuestionAssistancePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * TSS-4 服务助手及道路救援 信息
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-05
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class QuestionAssistanceVo extends QuestionAssistancePo implements Serializable {

}
