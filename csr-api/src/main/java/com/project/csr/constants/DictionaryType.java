package com.project.csr.constants;

/**
 * @author: bin.tong
 * @date: 2020/11/12 17:20
 **/
public class DictionaryType {

    /************************** Channel 渠道 ********************************/

    /**
     * CHANNEL_ID_SURVEY
     */
    public static final Long CHANNEL_ID_SURVEY = 1L;

    /**
     * CHANNEL_ID_MONITOR
     */
    public static final Long CHANNEL_ID_MONITOR = 2L;

    /**
     * CHANNEL_ID_ASSISTANCE
     */
    public static final Long CHANNEL_ID_ASSISTANCE = 3L;

    public static final Integer CHANNEL_TYPE_EVALUATE = 1;

    public static final Integer CHANNEL_TYPE_DEDUCT = 2;



    /************************** Scope 范围 ********************************/

    /**
     * SCOPE_ID_NATIONAL
     */
    public static final Long SCOPE_ID_NATIONAL = 1L;

    /**
     * SCOPE_ID_REGION
     */
    public static final Long SCOPE_ID_REGION = 2L;

    /**
     * SCOPE_ID_PROVINCE
     */
    public static final Long SCOPE_ID_PROVINCE = 3L;

    /**
     * SCOPE_ID_CITY
     */
    public static final Long SCOPE_ID_CITY = 4L;

    /**
     * SCOPE_ID_SUPERIOR
     */
    public static final Long SCOPE_ID_SUPERIOR = 5L;

    /**
     * SCOPE_ID_STORE
     */
    public static final Long SCOPE_ID_STORE = 6L;

    /************************** ScoreType 得分类型 ********************************/

    /**
     * SCORE_TYPE_EVALUATE_ID   考核分
     */
    public static final Integer SCORE_TYPE_ID_EVALUATE = 1;

    public static final String SCORE_TYPE_EVALUATE = "evaluate";

    /**
     * SCORE_TYPE_ID_BONUS   加分
     */
    public static final Integer SCORE_TYPE_ID_BONUS = 2;
    public static final String SCORE_TYPE_BONUS = "bonus";

    /**
     * SCORE_TYPE_ID_DEDUCT   扣分
     */
    public static final Integer SCORE_TYPE_ID_DEDUCT = 3;
    public static final String SCORE_TYPE_DEDUCT = "deduct";


}
