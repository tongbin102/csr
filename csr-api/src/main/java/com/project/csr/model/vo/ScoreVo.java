package com.project.csr.model.vo;


import com.project.csr.model.po.ScorePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 成绩排名表 信息
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-05
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ScoreVo extends ScorePo implements Serializable {

    private String storeName;

    private Integer scoreDiff;

    private Integer rankCountryDiff;

    private Integer rankScopeDiff;
}
