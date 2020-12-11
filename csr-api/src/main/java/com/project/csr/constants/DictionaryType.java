package com.project.csr.constants;

/**
 * @author: bin.tong
 * @date: 2020/11/12 17:20
 **/
public class DictionaryType {

    /************************** ROLE 权限 ********************************/
    public static final Long ROLE_ID_ADMIN = 1L;
    public static final String ROLE_NAME_ADMIN = "admin";

    public static final Long ROLE_ID_NATIONAL = 2L;
    public static final String ROLE_NAME_NATIONAL = "national";

    public static final Long ROLE_ID_REGION = 3L;
    public static final String ROLE_NAME_REGION = "region";

    public static final Long ROLE_ID_AREA = 4L;
    public static final String ROLE_NAME_AREA = "area";

    public static final Long ROLE_ID_SUPERIOR = 5L;
    public static final String ROLE_NAME_SUPERIOR = "superior";

    public static final Long ROLE_ID_STORE = 6L;
    public static final String ROLE_NAME_STORE = "store";

    public static final Integer CHANGE_PASSWORD_NEED = 0;
    public static final Integer CHANGE_PASSWORD_DONT_NEED = 1;

    /************************** Channel 渠道 ********************************/

    /**
     * CHANNEL_ID_SURVEY
     */
    public static final Long CHANNEL_ID_SURVEY = 1L;
    public static final String CHANNEL_CODE_SURVEY = "survey";
    public static final String CHANNEL_NAME_SURVEY = "调研问卷";

    /**
     * CHANNEL_ID_MONITOR
     */
    public static final Long CHANNEL_ID_MONITOR = 2L;
    public static final String CHANNEL_CODE_MONITOR = "monitor";
    public static final String CHANNEL_NAME_MONITOR = "过程监控";

    /**
     * CHANNEL_ID_ASSISTANCE
     */
    public static final Long CHANNEL_ID_ASSISTANCE = 3L;
    public static final String CHANNEL_CODE_ASSISTANCE = "assistance";
    public static final String CHANNEL_NAME_ASSISTANCE = "服务助手";
    /**
     * CHANNEL_ID_ASSISTANCE
     */
    public static final Long CHANNEL_ID_COMPLAIN = 4L;
    public static final String CHANNEL_CODE_COMPLAIN = "complain";
    public static final String CHANNEL_NAME_COMPLAIN = "投诉反馈";
    /**
     * CHANNEL_ID_ASSISTANCE
     */
    public static final Long CHANNEL_ID_RESCUE = 5L;
    public static final String CHANNEL_CODE_RESCUE = "rescue";
    public static final String CHANNEL_NAME_RESCUE = "道路救援";
    /**
     * CHANNEL_ID_ASSISTANCE
     */
    public static final Long CHANNEL_ID_DATA = 6L;
    public static final String CHANNEL_CODE_DATA = "data";
    public static final String CHANNEL_NAME_DATA = "数据准确性";

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

    /**
     * VALID_IND_VALID      有效数据
     */
    public static final Boolean VALID_IND_VALID = true;

    /**
     * VALID_IND_INVALID    无效数据
     */
    public static final Boolean VALID_IND_INVALID = false;


}
