/*
Navicat MySQL Data Transfer

Source Server         : 10.55.3.4_3306
Source Server Version : 50731
Source Host           : 10.55.3.4:3306
Source Database       : csr

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2020-11-05 14:57:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for channel
-- ----------------------------
DROP TABLE IF EXISTS `channel`;
CREATE TABLE `channel` (
  `id` bigint(11) NOT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '渠道code',
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '渠道名称',
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='渠道表';

-- ----------------------------
-- Records of channel
-- ----------------------------
INSERT INTO `channel` VALUES ('1', 'survey', '客户调研', '', '1', '2020-10-30 12:31:03', '1', '2020-10-30 12:31:03');
INSERT INTO `channel` VALUES ('2', 'monitor', '过程监控', '', '1', '2020-10-30 12:31:03', '1', '2020-10-30 12:31:03');
INSERT INTO `channel` VALUES ('3', 'assistant', '服务助手', '', '1', '2020-10-30 12:31:03', '1', '2020-10-30 12:31:03');

-- ----------------------------
-- Table structure for element
-- ----------------------------
DROP TABLE IF EXISTS `element`;
CREATE TABLE `element` (
  `id` bigint(11) NOT NULL COMMENT '主键id',
  `code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '要素code',
  `factor_id` bigint(255) DEFAULT NULL COMMENT '因子',
  `factor_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '因子名称',
  `element` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '考核单元',
  `specific` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '细项要求',
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='因子要素表';

-- ----------------------------
-- Records of element
-- ----------------------------
INSERT INTO `element` VALUES ('1', null, '1', null, '预约沟通准备', '沟通准备', '', '1', '2020-10-30 11:02:17', '1', '2020-10-30 11:02:17');
INSERT INTO `element` VALUES ('2', null, '1', null, '预约时间管理', '削峰填谷', '', '1', '2020-10-30 11:02:17', '1', '2020-10-30 11:02:17');
INSERT INTO `element` VALUES ('3', null, '1', null, '预约沟通内容', '预约记录', '', '1', '2020-10-30 11:02:17', '1', '2020-10-30 11:02:17');
INSERT INTO `element` VALUES ('4', null, '1', null, '预约沟通内容', '预约确认', '', '1', '2020-10-30 11:02:17', '1', '2020-10-30 11:02:17');
INSERT INTO `element` VALUES ('5', null, '1', null, '预约沟通内容', '主管监控', '', '1', '2020-10-30 11:02:17', '1', '2020-10-30 11:02:17');
INSERT INTO `element` VALUES ('6', null, '1', null, '预约沟通/话术', '微笑沟通', '', '1', '2020-10-30 11:02:17', '1', '2020-10-30 11:02:17');
INSERT INTO `element` VALUES ('7', null, '1', null, '预约沟通/话术', '话术培训', '', '1', '2020-10-30 11:02:17', '1', '2020-10-30 11:02:17');
INSERT INTO `element` VALUES ('8', null, '1', null, '预约沟通/话术', '录音抽查', '', '1', '2020-10-30 11:02:17', '1', '2020-10-30 11:02:17');
INSERT INTO `element` VALUES ('9', null, '1', null, '预约提醒', '预约提醒', '', '1', '2020-10-30 11:02:17', '1', '2020-10-30 11:02:17');
INSERT INTO `element` VALUES ('10', null, '1', null, '预约客户欢迎', '欢迎看板', '', '1', '2020-10-30 11:02:17', '1', '2020-10-30 11:02:17');
INSERT INTO `element` VALUES ('11', null, '1', null, '预约客户欢迎', '数字化欢迎', '', '1', '2020-10-30 11:02:17', '1', '2020-10-30 11:02:17');
INSERT INTO `element` VALUES ('12', null, '1', null, '进度实时更新', '菱菱邦进度查询', '', '1', '2020-10-30 11:02:17', '1', '2020-10-30 11:02:17');
INSERT INTO `element` VALUES ('13', null, '1', null, '进度实时更新', '人工进度更新', '', '1', '2020-10-30 11:02:17', '1', '2020-10-30 11:02:17');
INSERT INTO `element` VALUES ('14', null, '1', null, '投诉发生', '投诉发生', '', '1', '2020-10-30 11:02:17', '1', '2020-10-30 11:02:17');
INSERT INTO `element` VALUES ('15', null, '2', null, '前台热情接待', '微笑服务', '', '1', '2020-10-30 11:02:17', '1', '2020-10-30 11:02:17');
INSERT INTO `element` VALUES ('16', null, '2', null, '前台热情接待', '外置接待台', '', '1', '2020-10-30 11:02:17', '1', '2020-10-30 11:02:17');
INSERT INTO `element` VALUES ('17', null, '2', null, '前台个性化接待', '个性化服务', '', '1', '2020-10-30 11:02:17', '1', '2020-10-30 11:02:17');
INSERT INTO `element` VALUES ('18', null, '2', null, '现场及时接待', '及时接待', '', '1', '2020-10-30 11:02:17', '1', '2020-10-30 11:02:17');
INSERT INTO `element` VALUES ('19', null, '2', null, '预检接待礼仪', '预检邀约', '', '1', '2020-10-30 11:02:17', '1', '2020-10-30 11:02:17');
INSERT INTO `element` VALUES ('20', null, '2', null, '预检接待礼仪', '免费项目提醒', '', '1', '2020-10-30 11:02:17', '1', '2020-10-30 11:02:17');
INSERT INTO `element` VALUES ('21', null, '2', null, '预检专业接待', '环车检查', '', '1', '2020-10-30 11:02:17', '1', '2020-10-30 11:02:17');
INSERT INTO `element` VALUES ('22', null, '2', null, '预检专业接待', '问题诊断', '', '1', '2020-10-30 11:02:17', '1', '2020-10-30 11:02:17');
INSERT INTO `element` VALUES ('23', null, '2', null, '预检专业接待', '旧件沟通', '', '1', '2020-10-30 11:02:17', '1', '2020-10-30 11:02:17');
INSERT INTO `element` VALUES ('24', null, '2', null, '预检专业接待', '话术培训', '', '1', '2020-10-30 11:02:17', '1', '2020-10-30 11:02:17');
INSERT INTO `element` VALUES ('25', null, '2', null, '项目洽谈专业接待', '交付时间预估', '', '1', '2020-10-30 11:02:17', '1', '2020-10-30 11:02:17');
INSERT INTO `element` VALUES ('26', null, '2', null, '项目洽谈专业接待', '清晰解释', '', '1', '2020-10-30 11:02:17', '1', '2020-10-30 11:02:17');
INSERT INTO `element` VALUES ('27', null, '2', null, '项目洽谈专业接待', '钥匙管理', '', '1', '2020-10-30 11:02:17', '1', '2020-10-30 11:02:17');
INSERT INTO `element` VALUES ('28', null, '2', null, '项目洽谈透明化沟通', '逐条解释', '', '1', '2020-10-30 11:02:17', '1', '2020-10-30 11:02:17');
INSERT INTO `element` VALUES ('29', null, '2', null, '项目洽谈透明化沟通', '标准项目', '', '1', '2020-10-30 11:02:17', '1', '2020-10-30 11:02:17');
INSERT INTO `element` VALUES ('30', null, '2', null, '项目洽谈透明化沟通', '竞品价格解释', '', '1', '2020-10-30 11:02:17', '1', '2020-10-30 11:02:17');

-- ----------------------------
-- Table structure for element_score
-- ----------------------------
DROP TABLE IF EXISTS `element_score`;
CREATE TABLE `element_score` (
  `id` bigint(11) NOT NULL COMMENT '主键id',
  `period` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '期数',
  `scope_id` int(11) DEFAULT NULL COMMENT '范围id',
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '名称',
  `element_id` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '因子要素id',
  `channel_id` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '渠道id',
  `score` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '分因子分数',
  `score_type` bit(1) DEFAULT NULL COMMENT '成绩类型：1.考核项目 2. 加分项',
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='分项成绩统计表';

-- ----------------------------
-- Records of element_score
-- ----------------------------

-- ----------------------------
-- Table structure for element_score_channel
-- ----------------------------
DROP TABLE IF EXISTS `element_score_channel`;
CREATE TABLE `element_score_channel` (
  `id` bigint(11) NOT NULL COMMENT '主键id',
  `period` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '期数',
  `scope_id` int(11) DEFAULT NULL COMMENT '范围id',
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '名称',
  `element_id` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '因子要素id',
  `score` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '分因子分数',
  `score_type` bit(1) DEFAULT NULL COMMENT '成绩类型：1.考核项目 2. 加分项',
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='分渠道因子要素分布表';

-- ----------------------------
-- Records of element_score_channel
-- ----------------------------

-- ----------------------------
-- Table structure for factor
-- ----------------------------
DROP TABLE IF EXISTS `factor`;
CREATE TABLE `factor` (
  `id` bigint(11) NOT NULL COMMENT '主键id',
  `code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '因子code',
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '因子名称',
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='因子表';

-- ----------------------------
-- Records of factor
-- ----------------------------
INSERT INTO `factor` VALUES ('1', null, '预约', '', '1', '2020-10-30 14:02:37', '1', '2020-10-30 14:02:37');
INSERT INTO `factor` VALUES ('2', null, '服务流程', '', '1', '2020-10-30 14:02:37', '1', '2020-10-30 14:02:37');
INSERT INTO `factor` VALUES ('3', null, '交付效率', '', '1', '2020-10-30 14:02:37', '1', '2020-10-30 14:02:37');
INSERT INTO `factor` VALUES ('4', null, '硬件设施', '', '1', '2020-10-30 14:02:37', '1', '2020-10-30 14:02:37');
INSERT INTO `factor` VALUES ('5', null, '服务质量', '', '1', '2020-10-30 14:02:37', '1', '2020-10-30 14:02:37');
INSERT INTO `factor` VALUES ('6', null, '服务价值', '', '1', '2020-10-30 14:02:37', '1', '2020-10-30 14:02:37');

-- ----------------------------
-- Table structure for factor_channel_question
-- ----------------------------
DROP TABLE IF EXISTS `factor_channel_question`;
CREATE TABLE `factor_channel_question` (
  `id` bigint(11) NOT NULL,
  `factor_id` bigint(11) DEFAULT NULL,
  `channel_id` int(11) DEFAULT NULL,
  `questions` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '对应题目',
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='TSS-1 因子要素映射配置表';

-- ----------------------------
-- Records of factor_channel_question
-- ----------------------------
INSERT INTO `factor_channel_question` VALUES ('1', '15', '1', 'S15-1', '', '1', '2020-10-30 13:03:25', '1', '2020-10-30 13:03:25');
INSERT INTO `factor_channel_question` VALUES ('2', '16', '2', 'P15-1', '', '1', '2020-10-30 13:03:25', '1', '2020-10-30 13:03:25');
INSERT INTO `factor_channel_question` VALUES ('3', '17', '1', 'S15-1', '', '1', '2020-10-30 13:03:25', '1', '2020-10-30 13:03:25');
INSERT INTO `factor_channel_question` VALUES ('4', '18', '1', 'S17-1', '', '1', '2020-10-30 13:03:25', '1', '2020-10-30 13:03:25');
INSERT INTO `factor_channel_question` VALUES ('5', '19', '1', 'S18-1', '', '1', '2020-10-30 13:03:25', '1', '2020-10-30 13:03:25');
INSERT INTO `factor_channel_question` VALUES ('6', '20', '1', 'S18-1', '', '1', '2020-10-30 13:03:25', '1', '2020-10-30 13:03:25');
INSERT INTO `factor_channel_question` VALUES ('7', '21', '1', 'S19-1', '', '1', '2020-10-30 13:03:25', '1', '2020-10-30 13:03:25');
INSERT INTO `factor_channel_question` VALUES ('8', '22', '1', 'S19-1', '', '1', '2020-10-30 13:03:25', '1', '2020-10-30 13:03:25');
INSERT INTO `factor_channel_question` VALUES ('9', '23', '1', 'S19-1', '', '1', '2020-10-30 13:03:25', '1', '2020-10-30 13:03:25');
INSERT INTO `factor_channel_question` VALUES ('10', '24', '2', 'P19-1，P19-2\r\nP19-1,P19-2', '', '1', '2020-10-30 13:03:25', '1', '2020-10-30 13:03:25');
INSERT INTO `factor_channel_question` VALUES ('11', '24', '1', 'S21-1', '', '1', '2020-10-30 13:03:25', '1', '2020-10-30 13:03:25');
INSERT INTO `factor_channel_question` VALUES ('12', '24', '3', 'F20-1', '', '1', '2020-10-30 13:03:25', '1', '2020-10-30 13:03:25');

-- ----------------------------
-- Table structure for question_assistance
-- ----------------------------
DROP TABLE IF EXISTS `question_assistance`;
CREATE TABLE `question_assistance` (
  `id` bigint(11) NOT NULL,
  `channel_id` bigint(11) DEFAULT NULL COMMENT '所属渠道',
  `series_no` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '问题序号',
  `analysis_point` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '分析要点',
  `kpi` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT 'KPI指标',
  `desc` varchar(1024) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT 'KPI说明',
  `excellent` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '优秀',
  `good` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '优良',
  `standard` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '达标',
  `weak` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '薄弱',
  `score_type` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '类别',
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='TSS-4 服务助手及道路救援';

-- ----------------------------
-- Records of question_assistance
-- ----------------------------
INSERT INTO `question_assistance` VALUES ('1', '3', 'F2-1', '入店波峰分析', '入店车辆波动标准差', '店内8:00~18:00各个时段入场车辆数量波动幅度', '<1.0', '<1.5', '<2.5', '>=2.5', '考核', '', '1', '2020-10-30 13:04:33', '1', '2020-10-30 13:04:33');
INSERT INTO `question_assistance` VALUES ('2', '3', 'F7-1', '预约类车辆下单时间与开工时间差（分钟）', '预约车辆及时开工点', '预约类车辆（开工时间-下单时间）', '<3分钟', '<5分钟', '<15分钟', '>=15分钟', '考核', '', '1', '2020-10-30 13:04:33', '1', '2020-10-30 13:04:33');
INSERT INTO `question_assistance` VALUES ('3', '3', 'F7-2', '车辆在店的各个环节之间的高效流转', '高效流转率', '有效工时占比=有效工时/在店时长\r\n1） 平均有效工时=（完工时间-开工时间）/客户\r\n2)平均在店时长（结算时间-接车时间）/客户', '>80%', '>70%', '>60%', '<=60%', '加分', '', '1', '2020-10-30 13:04:33', '1', '2020-10-30 13:04:33');
INSERT INTO `question_assistance` VALUES ('4', '3', 'F20-1', '服务顾问给客户的预估交付时间是否准确', '预估偏差率', '预估偏差率=abs(预计完工时长/实际在店时长-1）\r\n1）预计完工时长为服务助手中预计的完工时长/客户\r\n2）实际在店时长=（结算时间-接车时间）/客户', '<5%', '<10%', '<20%', '>=20%', '考核', '', '1', '2020-10-30 13:04:33', '1', '2020-10-30 13:04:33');
INSERT INTO `question_assistance` VALUES ('5', '3', 'F30-1', '车间是否进行质检', '质检比率', '店内完成质检车辆数/店内全部的结算交车数量', '>80%', '>70%', '>60%', '<=60%', '加分', '', '1', '2020-10-30 13:04:33', '1', '2020-10-30 13:04:33');
INSERT INTO `question_assistance` VALUES ('6', '3', 'R30-1', '道路救援', '救援反应时间', '从收到救援申请到反应的时间', '<1分钟', '<2分钟', '<3分钟', '>=3分钟', '考核', '', '1', '2020-10-30 13:04:33', '1', '2020-10-30 13:04:33');

-- ----------------------------
-- Table structure for question_monitor
-- ----------------------------
DROP TABLE IF EXISTS `question_monitor`;
CREATE TABLE `question_monitor` (
  `id` bigint(11) NOT NULL COMMENT '主键id',
  `channel_id` bigint(11) DEFAULT NULL COMMENT '所属渠道',
  `series_no` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '问题序号',
  `desc` varchar(1024) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '调研问题',
  `illustration` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '相关说明',
  `excellent` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '优秀',
  `good` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '优良',
  `standard` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '达标',
  `weak` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '薄弱',
  `score_type` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '类别',
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='TSS-3 过程监控要求';

-- ----------------------------
-- Records of question_monitor
-- ----------------------------
INSERT INTO `question_monitor` VALUES ('1', '2', 'P3-1', '1) 经销店每月提供一次预约成功的客户预约内容记录（参照模版：预约客户需求记录.xls）\r\n2) 经销店每月提供一次服务顾问与客户预约成功后发给客户的确认记录截屏（短信、微信、APP等方式）（参照模版：预约客户需求确认截屏提报表.xls）', '1. 为邀约成功客户的需求内容记录，非邀约客户通话记录\r\n2. 预约内容记录为过去一个月的店内全部预约客户内容\r\n3. 服务顾问与客户的确认记录要求每个服务顾问提供10个不同客户的截屏，发生时间段要均分在过去一个月内，不能聚焦在某2~3天', '1. 依据邀约客户需求记录分析，平均每天不少于2名预约客户，每月不少于60名，预约客户基本能够均布在当月的不同日期\r\n2. 需求内容记录详细，问题描述和需求能够做到细节展开，清晰详细 \r\n3. 服务顾问与客户确认信息与需求内容记录保持一致，截屏能够数目每个服务顾问能够达到10个，分布在10个不同日期，并且70%有客户回复确认\r\n4. 依据邀约客户需求记录分析，售后经理/技术主管对50%以上的预约记录有点评，对记录简单、内容不全的有提示要求', '1. 依据邀约客户需求记录分析，平均每天不少于1名预约客户，每月不少于30名，预约客户没有聚集在某3~4天的时间段\r\n2. 需求内容记录清晰，齐全\r\n3. 服务顾问与客户确认信息与需求内容记录保持一致，截屏能够数目每个服务顾问能够达到6个，分布在6个不同日期，并且40%有客户回复确认\r\n4. 依据邀约客户需求记录分析，售后经理/技术主管对30%以上的预约记录有点评，对记录简单、内容不全的有提示要求', '1. 依据邀约客户需求记录分析，平均每天不少于0.5名预约客户，每月不少于15名，预约客户没有聚集在某3~4天的时间段\r\n2. 需求内容记录清晰，齐全\r\n3. 服务顾问与客户确认信息与需求内容记录保持一致，截屏能够数目每个服务顾问能够达到3个，分布在3个不同日期 ', '其他情况， 或未提报', '考核项', '', '1', '2020-10-30 13:24:08', '1', '2020-10-30 13:24:08');
INSERT INTO `question_monitor` VALUES ('2', '2', 'P4-1', '经销店每月提供一次本店预约话术提升会议纪要', '1. 提报材料参照《预约话术提升会会议纪要模版》, 各项内容填写齐全', null, null, null, '其他情况， 或未提报', '考核项', '', '1', '2020-10-30 13:24:08', '1', '2020-10-30 13:24:08');
INSERT INTO `question_monitor` VALUES ('3', '2', 'P4-2', '1）经销店提供店内过去一个月的所有抽查录音反馈表及抽查录音文件夹列表截图\r\n2）提供过去一个月中5个抽查录音反馈表及其录音文件 ', '1. 参考《预约录音抽查统计模板》和《预约录音抽查反馈表》， 各项内容填写齐全', null, null, null, '其他情况， 或未提报', '考核项', '', '1', '2020-10-30 13:24:08', '1', '2020-10-30 13:24:08');
INSERT INTO `question_monitor` VALUES ('4', '2', 'P5-1', '经销店提供过去一个月服务顾问与客户在微信、短信、菱菱邦APP上的预约到店提醒截图', null, null, null, null, '其他情况， 或未提报', '考核项', '', '1', '2020-10-30 13:24:08', '1', '2020-10-30 13:24:08');
INSERT INTO `question_monitor` VALUES ('5', '2', 'P9-1', '提供过去一个月晨会的10个小视频', null, null, null, null, '其他情况， 或未提报', '加分项', '', '1', '2020-10-30 13:24:08', '1', '2020-10-30 13:24:08');
INSERT INTO `question_monitor` VALUES ('6', '2', 'P9-2', '提报过去一个月10天中的《预约客户提前准备信息表》', null, null, null, null, '其他情况， 或未提报', '考核项', '', '1', '2020-10-30 13:24:08', '1', '2020-10-30 13:24:08');
INSERT INTO `question_monitor` VALUES ('7', '2', 'P19-1', '提供过去一个月《常见问题和故障应对话术手册》', null, null, null, null, '其他情况， 或未提报', '考核项', '', '1', '2020-10-30 13:24:08', '1', '2020-10-30 13:24:08');
INSERT INTO `question_monitor` VALUES ('8', '2', 'P19-2', '提供过去一个月围绕《常见问题和故障应对话术手册》的员工演练视频6段', null, null, null, null, '其他情况， 或未提报', '考核项', '', '1', '2020-10-30 13:24:08', '1', '2020-10-30 13:24:08');
INSERT INTO `question_monitor` VALUES ('9', '2', 'P39-1', '1. 提报三级质检录音5个\r\n2. 提供三级质检录音文件夹截屏', null, null, null, null, '其他情况， 或未提报', '考核项', '', '1', '2020-10-30 13:24:08', '1', '2020-10-30 13:24:08');
INSERT INTO `question_monitor` VALUES ('10', '2', 'P39-2', '提报三级质检研讨会议纪要', null, null, null, null, '其他情况， 或未提报', '考核项', '', '1', '2020-10-30 13:24:08', '1', '2020-10-30 13:24:08');

-- ----------------------------
-- Table structure for question_survey
-- ----------------------------
DROP TABLE IF EXISTS `question_survey`;
CREATE TABLE `question_survey` (
  `id` bigint(11) NOT NULL COMMENT '主键id',
  `channel_id` bigint(11) DEFAULT NULL COMMENT '所属渠道',
  `series_no` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '问题序号',
  `desc` varchar(1024) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '调研问题',
  `answer1` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '答案1',
  `answer2` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '答案2',
  `answer3` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '答案3',
  `answer4` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '答案4',
  `answer5` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '答案5',
  `score_item` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '计分答案项',
  `formula` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '公式号',
  `excellent` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '优秀',
  `good` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '优良',
  `standard` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '达标',
  `weak` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '薄弱',
  `score_type` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '类别',
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='TSS-2 调研问卷评分规则配置表';

-- ----------------------------
-- Records of question_survey
-- ----------------------------
INSERT INTO `question_survey` VALUES ('1', '1', 'S10-1', '作为预约客户，经销店有没有提供给你特别的好处：\nA）有，时间更高效，优先维修，预约明显感觉到服务和维修都很快\nB）有，在工时或配件上有折扣\nC）有，其他形式的优惠\nD）没有印象，不知道有这样的优惠\"\"作为预约客户，经销店有没有提供给你特别的好处：\nA）有，时间更高效，优先维修，预约明显感觉到服务和维修都很快\nB）有，在工时或配件上有折扣\nC）有，其他形式的优惠\nD）没有印象，不知道有这样的优惠\r\n作为预约客户，经销店有没有提供给你特别的好处：\r\nA）有，时间更高效，优先维修，预约明显感觉到服务和维修都很快\r\nB）有，在工时或配件上有折扣\r\nC）有，其他形式的优惠\r\nD）没有印象，不知道有这样的优惠', '10', '7', '5', '0', '', '综合分', null, '>', '8.5', '7', '6', '', '', '1', '2020-10-30 13:04:33', '1', '2020-10-30 13:04:33');
INSERT INTO `question_survey` VALUES ('2', '1', 'S11-1', '在您在店等待期间，你是如何了解到你的车辆的维修进度的？\r\nA）我自己去车间查看\r\nB）服务顾问会每隔半个小时或一个小时给我通报更新一下\r\nC）我通过菱菱邦APP、经销店的液晶屏的进度看板去看进度的，比较准确\r\nD）没有什么很有效的方法让我了解我车辆的维修进度', '1', '0', '0', '1', '', '综合分', null, '<', '10%', '20%', '30%', '', '', '1', '2020-10-30 13:04:33', '1', '2020-10-30 13:04:33');
INSERT INTO `question_survey` VALUES ('3', '1', 'S15-1', '在您到店维修时，接待您的服务顾问能否做到：\r\nA）热情微笑，主动迎前或站立问候接待您，而不是您先找他们才开始服务\r\nB）叫出您的姓名，根据您具体情况而给您更好的建议\r\nC）正常接待，没有太多好坏\r\nD）整体接待态度不好，体验不好', '0', '0', '1', '1', '', '综合分', null, '<', '10%', '20%', '30%', '', '', '1', '2020-10-30 13:04:33', '1', '2020-10-30 13:04:33');
INSERT INTO `question_survey` VALUES ('4', '1', 'S17-1', '您进到售后前台，是否有人第一时间（30秒内）主动来接待您？而不是您与他们打招呼后才接待您\r\nA）是\r\nB）否', '', '', '', '', '', '1', null, '>', '80%', '60%', '40%', '', '', '1', '2020-10-30 13:04:33', '1', '2020-10-30 13:04:33');
INSERT INTO `question_survey` VALUES ('5', '1', 'S18-1', '在服务顾问与您确定维修项目开始维修前， 有没有邀请您一起对您的车辆进行快捷全面的车辆检查？\r\nA）未有此环节\r\nB）有，只是简单看了一下\r\nC）有，还提醒我贵重物品要带走，标记了里程数和油箱量，挺贴心的\r\nD）有，检查前还用座椅套、方向盘套、脚垫，对车内、车外、后尾箱进行了检查，把引擎盖打开检查', '1', '1', '0', '0', '', '综合分', null, '<', '10%', '20%', '30%', '', '', '1', '2020-10-30 13:04:33', '1', '2020-10-30 13:04:33');
INSERT INTO `question_survey` VALUES ('6', '1', 'S19-1', '你是如何评价服务顾问在帮你诊断车辆问题的专业能力的？\r\nA）服务顾问没让我感觉有多少专业能力，车辆问题主要是车间里的人更懂\r\nB）服务顾问在车辆检查中能够根据车辆问题进行专业分析，\r\nC）服务顾问讲的我也听不太懂，感觉似乎有道理但心里也没法判断，只能选择相信了', '', '', '', '', '', '2', null, '>', '80%', '60%', '40%', '', '', '1', '2020-10-30 13:04:33', '1', '2020-10-30 13:04:33');
INSERT INTO `question_survey` VALUES ('7', '1', 'S21-1', '您觉得经销店维修的收费情况如何？\r\nA）收费很合理，服务顾问能够详细逐条解释每项收费标准，让我感觉物超所值\r\nB ) 收费还是可以接受的，服务顾问也做了详细解释\r\nC）收费是否合理没法做太准确判断，服务顾问解释也一概而过，我对价格感觉不是很踏实\r\nD）收费是挺贵的，服务顾问也没什么详细的解释', '10', '8', '7', '5', '', '综合分', null, '>', '8.5', '7', '6', '', '', '1', '2020-10-30 13:04:33', '1', '2020-10-30 13:04:33');
INSERT INTO `question_survey` VALUES ('8', '1', 'S22-1', '您是如何评价经销店的客休区服务的？\r\nA）有专职的客休区服务员，并能主动上前询问需要的饮料，服务热情\r\nB）客休区服务员没有主动接待客户，只是在吧台内接待客户\r\nC）没有看到专职的客休区服务员', '10', '5', '0', '', '', '综合分', null, '>', '9', '7.5', '5', '', '', '1', '2020-10-30 13:04:33', '1', '2020-10-30 13:04:33');
INSERT INTO `question_survey` VALUES ('9', '1', 'S24-1', '您如何评价维修后4S店给您的交车？\r\nA）没有特别的环节，服务顾问通知我车修好了， 结算后我就自行把车开走了\r\nB）服务顾问陪同我对车辆进行了简单的验收，把保养和维修的内容简单说了一遍。 \r\nC）服务顾问陪同我对车辆进行了详细验收，对维修保养成果进行了展示，包括换下来的新旧件和安装前后的比较。\r\nD）交车后还提供了额外的服务，有仪式感或小礼物（包括矿泉水等），服务顾问服务贴心周到。', '0', '5', '7', '10', '', '综合分', null, '>', '8.5', '7', '6', '', '', '1', '2020-10-30 13:04:33', '1', '2020-10-30 13:04:33');
INSERT INTO `question_survey` VALUES ('10', '1', 'S24-2', '您如何评价您车辆维修保养后经销店交给您时的状态：\r\nA ）车没洗或者洗的不干净，车内也有杂物或者摆放不整齐的\r\nB）车辆能够看得出来是经过整理清洁的，车洗得干净\r\nC）车内的空调、座椅、音响恢复原位', '0', '5', '10', '', '', '综合分', null, '>', '9', '7.5', '5', '', '', '1', '2020-10-30 13:04:33', '1', '2020-10-30 13:04:33');
INSERT INTO `question_survey` VALUES ('11', '1', 'S26-1', '您如何评价4S店的结算和送行环节？\r\nA）比较简单，没什么特别的印象\r\nB）结束时服务顾问能够目送我们离开\r\nC）能够感受到比较优质的服务，结算时收银员能够起立微笑问候', '0', '5', '10', '', '', '综合分', null, '>', '9', '7.5', '5', '', '', '1', '2020-10-30 13:04:33', '1', '2020-10-30 13:04:33');
INSERT INTO `question_survey` VALUES ('12', '1', 'S28-1', '在您维修后，接待过您的服务顾问有没有电话联系您关心维修后车辆的质量和效果情况？\r\nA）接待我的服务顾问联系过我，了解过我车辆维修后的质量和效果\r\nB）店里其他人联系过，了解对在店维修是否满意\r\nC）没有人联系过我', '10', '5', '0', '', '', '综合分', null, '>', '9', '7.5', '5', '', '', '1', '2020-10-30 13:04:33', '1', '2020-10-30 13:04:33');
INSERT INTO `question_survey` VALUES ('13', '1', 'S29-1', '您对4S店的车辆现场管理印象如何？\r\nA） 车辆摆放感觉比较杂多随意，跟非专业品牌修理厂差不多\r\nB） 车辆停放管理专业，能够清晰便捷看到客户停车区、预检区和交车区等停车区域划分\r\nC）从接待大厅到车间以及车间内部通道整洁宽敞，无车辆杂乱停放\r\nD）车辆摆放整齐，所有车辆停放统一车头向外', '', '', '', '', '', '1', null, '<', '10%', '20%', '30%', '', '', '1', '2020-10-30 13:04:33', '1', '2020-10-30 13:04:33');
INSERT INTO `question_survey` VALUES ('14', '1', 'S29-2', '您的车辆维修结束后是否在专门的交车区交付？\r\nA）没有感觉是专门的交车区，就在普通的过道或者车位上取的车\r\nB）有专门的交车区，地面或者旁边有“交车区”的标志\r\nC )  有专门的交车区，交车区有特殊的标识，比如在室内专门位置，或者有交车背景墙', '10', '5', '0', '', '', '综合分', null, '>', '9', '7.5', '5', '', '', '1', '2020-10-30 13:04:33', '1', '2020-10-30 13:04:33');
INSERT INTO `question_survey` VALUES ('15', '1', 'S29-3', '您的4S店的现场整洁性印象如何？\r\nA）现场比较老旧，地面有破损裂纹，停车线掉色、磨损等现场比较多，尘土杂物摆放时有可见\r\nB）现场卫生和秩序保持较好，地面整洁无裂痕，停车线保持鲜亮，清晰，无损坏残缺迹象', '', '', '', '', '', '2', null, '>', '80%', '60%', '40%', '', '', '1', '2020-10-30 13:04:33', '1', '2020-10-30 13:04:33');
INSERT INTO `question_survey` VALUES ('16', '1', 'S33-1', '您在4S店里客休区等待期间整体感觉如何？\r\nA）客休区环境设施较差\r\nB）客休区宽敞、明亮、整洁，有多种功能区的设置\r\nC）客休区有人性化的设计，例如多接口手机充电，共享充电台，香薰、背景音乐、电脑工作台、免洗洗手液等', '', '', '', '', '', '1', null, '<', '10%', '20%', '30%', '', '', '1', '2020-10-30 13:04:33', '1', '2020-10-30 13:04:33');
INSERT INTO `question_survey` VALUES ('17', '1', 'S33-2', '您在客休区对车辆维修项目相关的布置感觉如何？\r\nA）没什么感觉， 客休区没有多少跟维修相关的布置\r\nB）有透明的玻璃窗可以看到车间的施工现场，或者有摄像头可以在电视屏幕上看到车间的现场施工\r\nC）能够看到店里有一些车辆使用和维修常识的介绍和宣传，例如常见保养项目介绍、原厂/非原厂件对比墙/柜、车主俱乐部活动、认证技师/服务顾问介绍等', '', '', '', '', '', '1', null, '<', '10%', '20%', '30%', '', '', '1', '2020-10-30 13:04:33', '1', '2020-10-30 13:04:33');
INSERT INTO `question_survey` VALUES ('18', '1', 'S33-3 ', '您感觉店内对客休室的娱乐性方面投入如何？\r\nA）没有特别的发现\r\nB）有专门的娱乐区域，例如按摩区、台球区、茶艺区、儿童娱乐区、上网游戏区等', '', '', '', '', '', '2', null, '>', '80%', '60%', '40%', '', '', '1', '2020-10-30 13:04:33', '1', '2020-10-30 13:04:33');
INSERT INTO `question_survey` VALUES ('19', '1', 'S39-1', '您对在经销店车辆维修的质量感觉如何？\r\nA）维修和保养做得很专业彻底，问题都能一次性解决\r\nB）维修和保养做得基本满意，还是有些问题没有彻底解决，或者很快就又出问题了\r\nC）维修和保养质量不是很可靠，经常会有修了问题还在，反复修理的问题\r\nD) 维修和保养质量非常不可靠，不值得信任', '', '', '', '', '', '1', null, '>', '85%', '70%', '40%', '', '', '1', '2020-10-30 13:04:33', '1', '2020-10-30 13:04:33');
INSERT INTO `question_survey` VALUES ('20', '1', 'S40-1', '在维修中， 4S店在更换您的零部件方面是怎么跟您沟通的？\r\nA）没有特别的提及，也没有展示，只是说更换了某某零部件\r\nB）在维修前的预检中，明确询问我是否要带走旧的零部件\r\nC）在维修结束后，专门给我展示了换下来的旧零部件（我要求带走的已经给我包好放在车里了）', '', '', '', '', '', '1', null, '>', '5%', '10%', '20%', '', '', '1', '2020-10-30 13:04:33', '1', '2020-10-30 13:04:33');
INSERT INTO `question_survey` VALUES ('21', '1', 'S42-1', '过去一年中您参加过4S店里组织的车主忠诚度活动么？例如自驾游，亲子活动、茶艺鉴赏、VIP客户茶话会等 \r\nA）是的，过去一年参加过2次以上\r\nB）是的，过去一年参加过1次\r\nC）没有，但收到过4S店的邀请\r\nD）没有，也没有收到过邀请', '10', '7', '5', '0', '', '综合分', null, '>', '9', '7.5', '5', '', '', '1', '2020-10-30 13:04:33', '1', '2020-10-30 13:04:33');
INSERT INTO `question_survey` VALUES ('22', '1', 'S43-1', '对您和您的服务顾问的关系下面哪种描述是正确的？\r\nA）我有固定的服务顾问，每次去都是她/他接待我，我对她/他有非常好的信任感和熟悉度\r\nB）服务顾问在日常中能够主动给我一些车辆驾驶和保养的建议，对我提出的问题也能及时回复\r\nC）A和B都有\r\nD）没有固定的服务顾问', '7', '5', '10', '0', '', '综合分', null, '>', '9', '7.5', '5', '', '', '1', '2020-10-30 13:04:33', '1', '2020-10-30 13:04:33');
INSERT INTO `question_survey` VALUES ('23', '1', 'S47-1', '您如何评价经销店常规保养的效率如何？\r\nA）非常高效，基本能在1个小时内完成。 \r\nB）效率一般，稍微复杂一些情况就可能到1~2个小时，等待时间很长\r\nC）效率很低，很多简单的项目也要2个小时以上，稍微复杂时间更长', '10', '5', '0', '', '', '综合分', null, '>', '9', '7.5', '5', '', '', '1', '2020-10-30 13:04:33', '1', '2020-10-30 13:04:33');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '角色code',
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '角色描述',
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin', 'admin', 'admin', '', '1', '2020-10-30 09:21:29', '1', '2020-10-30 09:21:29');
INSERT INTO `role` VALUES ('2', 'vendor', 'vendor', 'vendor', '', '1', '2020-10-30 09:21:29', '1', '2020-07-20 13:45:39');
INSERT INTO `role` VALUES ('3', 'region', 'region', 'region', '', '1', '2020-10-30 09:21:29', '1', '2020-07-20 13:45:39');
INSERT INTO `role` VALUES ('4', 'area', 'area', 'area', '', '1', '2020-10-30 09:21:29', '1', '2020-07-20 13:45:39');
INSERT INTO `role` VALUES ('5', 'city', 'city', 'city', '', '1', '2020-10-30 09:21:29', '1', '2020-07-20 13:45:39');
INSERT INTO `role` VALUES ('6', 'superior', 'superior', 'superior', '', '1', '2020-10-30 09:21:29', '1', '2020-07-20 13:45:39');
INSERT INTO `role` VALUES ('7', 'store', 'store', 'store', '', '1', '2020-10-30 09:21:29', '1', '2020-07-20 13:45:39');

-- ----------------------------
-- Table structure for scope
-- ----------------------------
DROP TABLE IF EXISTS `scope`;
CREATE TABLE `scope` (
  `id` bigint(11) NOT NULL COMMENT '主键',
  `code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '范围code',
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '范围名称',
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='范围表';

-- ----------------------------
-- Records of scope
-- ----------------------------
INSERT INTO `scope` VALUES ('1', 'country', '全国', '', '1', '2020-10-30 14:14:32', '1', '2020-10-30 14:14:32');
INSERT INTO `scope` VALUES ('2', 'region', '大区', '', '1', '2020-10-30 14:14:32', '1', '2020-10-30 14:14:32');
INSERT INTO `scope` VALUES ('3', 'area', '小区', '', '1', '2020-10-30 14:14:32', '1', '2020-10-30 14:14:32');
INSERT INTO `scope` VALUES ('4', 'city', '城市', '', '1', '2020-10-30 14:14:32', '1', '2020-10-30 14:14:32');
INSERT INTO `scope` VALUES ('5', 'superior', '一级店', '', '1', '2020-10-30 14:14:32', '1', '2020-10-30 14:14:32');
INSERT INTO `scope` VALUES ('6', 'store', '经销店', '', '1', '2020-10-30 14:14:32', '1', '2020-10-30 14:14:32');

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `id` bigint(11) NOT NULL COMMENT '主键id',
  `period` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '期数',
  `scope_id` int(11) DEFAULT NULL COMMENT '范围id',
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '名称',
  `score` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '总分数',
  `rank_country` int(11) DEFAULT NULL COMMENT '排名',
  `rank_region` int(11) DEFAULT NULL COMMENT '区域排名',
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='成绩排名表';

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES ('1', '202011', '1', '全国', '1000', null, null, '', '1', '2020-11-05 10:59:29', '1', '2020-11-05 10:59:29');
INSERT INTO `score` VALUES ('2', '202011', '2', '华东大区', '900', '2', null, '', '1', '2020-11-05 10:59:29', '1', '2020-11-05 10:59:29');
INSERT INTO `score` VALUES ('3', '202011', '2', '华南大区', '840', '3', null, '', '1', '2020-11-05 10:59:29', '1', '2020-11-05 10:59:29');
INSERT INTO `score` VALUES ('4', '202011', '2', '华北大区', '720', '4', null, '', '1', '2020-11-05 10:59:29', '1', '2020-11-05 10:59:29');
INSERT INTO `score` VALUES ('5', '202011', '2', '西北大区', '900', '6', null, '', '1', '2020-11-05 10:59:29', '1', '2020-11-05 10:59:29');
INSERT INTO `score` VALUES ('6', '202010', '1', '全国', '995', null, null, '', '1', '2020-11-05 10:59:29', '1', '2020-11-05 10:59:29');
INSERT INTO `score` VALUES ('7', '202010', '2', '华东大区', '898', '7', null, '', '1', '2020-11-05 10:59:29', '1', '2020-11-05 10:59:29');
INSERT INTO `score` VALUES ('8', '202010', '2', '华南大区', '835', '5', null, '', '1', '2020-11-05 10:59:29', '1', '2020-11-05 10:59:29');
INSERT INTO `score` VALUES ('9', '202010', '2', '华北大区', '722', '2', null, '', '1', '2020-11-05 10:59:29', '1', '2020-11-05 10:59:29');
INSERT INTO `score` VALUES ('10', '202010', '2', '西北大区', '909', '4', null, '', '1', '2020-11-05 10:59:29', '1', '2020-11-05 10:59:29');

-- ----------------------------
-- Table structure for score_channel
-- ----------------------------
DROP TABLE IF EXISTS `score_channel`;
CREATE TABLE `score_channel` (
  `id` bigint(11) NOT NULL COMMENT '主键id',
  `period` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '期数',
  `scope_id` int(11) DEFAULT NULL COMMENT '范围id',
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '名称',
  `channel_id` int(11) DEFAULT NULL COMMENT '渠道id',
  `score` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '分渠道分数',
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='分渠道成绩统计表';

-- ----------------------------
-- Records of score_channel
-- ----------------------------
INSERT INTO `score_channel` VALUES ('1', '202011', '1', '全国', '1', '800', '', '1', '2020-11-05 13:22:09', '1', '2020-11-05 13:22:09');
INSERT INTO `score_channel` VALUES ('2', '202011', '1', '全国', '2', '1000', '', '1', '2020-11-05 13:22:09', '1', '2020-11-05 13:22:09');
INSERT INTO `score_channel` VALUES ('3', '202011', '1', '全国', '3', '600', '', '1', '2020-11-05 13:22:09', '1', '2020-11-05 13:22:09');
INSERT INTO `score_channel` VALUES ('4', '202011', '1', '全国', '4', '-20', '', '1', '2020-11-05 13:22:09', '1', '2020-11-05 13:22:09');
INSERT INTO `score_channel` VALUES ('5', '202010', '1', '全国', '1', '700', '', '1', '2020-11-05 13:22:09', '1', '2020-11-05 13:22:09');
INSERT INTO `score_channel` VALUES ('6', '202010', '1', '全国', '2', '900', '', '1', '2020-11-05 13:22:09', '1', '2020-11-05 13:22:09');
INSERT INTO `score_channel` VALUES ('7', '202010', '1', '全国', '3', '800', '', '1', '2020-11-05 13:22:09', '1', '2020-11-05 13:22:09');
INSERT INTO `score_channel` VALUES ('8', '202010', '1', '全国', '4', '-200', '', '1', '2020-11-05 13:22:09', '1', '2020-11-05 13:22:09');

-- ----------------------------
-- Table structure for score_factor
-- ----------------------------
DROP TABLE IF EXISTS `score_factor`;
CREATE TABLE `score_factor` (
  `id` bigint(11) NOT NULL COMMENT '主键id',
  `period` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '期数',
  `scope_id` int(11) DEFAULT NULL COMMENT '范围id',
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '名称',
  `factor_id` int(11) DEFAULT NULL COMMENT '因子id',
  `score` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '分因子分数',
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='分因子成绩统计表';

-- ----------------------------
-- Records of score_factor
-- ----------------------------
INSERT INTO `score_factor` VALUES ('1', '202011', '1', '全国', '1', '800', '', '1', '2020-11-05 13:22:09', '1', '2020-11-05 13:22:09');
INSERT INTO `score_factor` VALUES ('2', '202011', '1', '全国', '2', '1000', '', '1', '2020-11-05 13:22:09', '1', '2020-11-05 13:22:09');
INSERT INTO `score_factor` VALUES ('3', '202011', '1', '全国', '3', '600', '', '1', '2020-11-05 13:22:09', '1', '2020-11-05 13:22:09');
INSERT INTO `score_factor` VALUES ('4', '202011', '1', '全国', '4', '600', '', '1', '2020-11-05 13:22:09', '1', '2020-11-05 13:22:09');
INSERT INTO `score_factor` VALUES ('5', '202011', '1', '全国', '5', '600', '', '1', '2020-11-05 13:22:09', '1', '2020-11-05 13:22:09');
INSERT INTO `score_factor` VALUES ('6', '202011', '1', '全国', '6', '500', '', '1', '2020-11-05 13:22:09', '1', '2020-11-05 13:22:09');
INSERT INTO `score_factor` VALUES ('7', '202010', '1', '全国', '1', '700', '', '1', '2020-11-05 13:22:09', '1', '2020-11-05 13:22:09');
INSERT INTO `score_factor` VALUES ('8', '202010', '1', '全国', '2', '900', '', '1', '2020-11-05 13:22:09', '1', '2020-11-05 13:22:09');
INSERT INTO `score_factor` VALUES ('9', '202010', '1', '全国', '3', '800', '', '1', '2020-11-05 13:22:09', '1', '2020-11-05 13:22:09');
INSERT INTO `score_factor` VALUES ('10', '202010', '1', '全国', '4', '200', '', '1', '2020-11-05 13:22:09', '1', '2020-11-05 13:22:09');
INSERT INTO `score_factor` VALUES ('11', '202010', '1', '全国', '5', '300', '', '1', '2020-11-05 13:22:09', '1', '2020-11-05 13:22:09');
INSERT INTO `score_factor` VALUES ('12', '202010', '1', '全国', '6', '900', '', '1', '2020-11-05 13:22:09', '1', '2020-11-05 13:22:09');

-- ----------------------------
-- Table structure for score_question
-- ----------------------------
DROP TABLE IF EXISTS `score_question`;
CREATE TABLE `score_question` (
  `id` bigint(11) NOT NULL COMMENT '主键id',
  `period` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '期数',
  `scope_id` int(11) DEFAULT NULL COMMENT '范围id',
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '名称',
  `question` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '问题code',
  `score` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '分因子分数',
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='分项成绩统计表';

-- ----------------------------
-- Records of score_question
-- ----------------------------

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
  `id` int(11) NOT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '单位代码',
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '经销店名',
  `superior` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '所属一级',
  `scale` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '规模',
  `region` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '全国大区',
  `province` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '省份',
  `city` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '城市',
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` VALUES ('1', 'A001', '无锡浏阳店', 'A001', '1类', '华东大区', '江苏', '无锡', null, null, null, null, null);
INSERT INTO `store` VALUES ('2', 'A00101', '上海五菱店', 'A001', '2类', '华南大区', '上海', '上海', null, null, null, null, null);
INSERT INTO `store` VALUES ('3', 'A00102', '杭州志明店', 'A001', '3类', '华南大区', '浙江', '杭州', null, null, null, null, null);
INSERT INTO `store` VALUES ('4', 'A00103', '苏州明田店', 'A002', '2类', '华东大区', '江苏', '苏州', null, null, null, null, null);
INSERT INTO `store` VALUES ('5', 'A00104', '山东青岛A店', 'A001', '2类', '华北大区', '山东', '青岛', null, null, null, null, null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '用户名',
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '用户姓名',
  `password` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '密码',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `ref` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '负责范围',
  `sex` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '性别',
  `address` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '地址',
  `birth_date` datetime DEFAULT NULL COMMENT '出生日期',
  `phone` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '手机号',
  `icon_url` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '用户头像',
  `description` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '用户描述',
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', '$2a$10$mseea54Txd8r7f9Qi/8CV.cPEnUe55gC/2MJRcpCFCFKu6I58t4P.', '1', null, '3', null, null, null, null, null, '', '1', '2020-10-30 09:52:38', '1', '2020-10-30 09:52:38');
INSERT INTO `user` VALUES ('2', 'wl000', '何小鹏\r\n', '$2a$10$mseea54Txd8r7f9Qi/8CV.cPEnUe55gC/2MJRcpCFCFKu6I58t4P.', '1', '', '1', null, null, null, null, null, '', '1', '2020-10-30 09:52:38', '1', '2020-10-30 09:52:38');
INSERT INTO `user` VALUES ('3', 'WL001', '周林', '$2a$10$mseea54Txd8r7f9Qi/8CV.cPEnUe55gC/2MJRcpCFCFKu6I58t4P.', '2', '', null, null, null, null, null, null, '', '1', '2020-10-30 09:52:38', '1', '2020-10-30 09:52:38');
INSERT INTO `user` VALUES ('4', 'WL0021', '王磊', '$2a$10$mseea54Txd8r7f9Qi/8CV.cPEnUe55gC/2MJRcpCFCFKu6I58t4P.', '3', '华东区', null, null, null, null, null, null, '', '1', '2020-10-30 09:52:38', '1', '2020-10-30 09:52:38');
INSERT INTO `user` VALUES ('5', 'WL0022', '孙月', '$2a$10$mseea54Txd8r7f9Qi/8CV.cPEnUe55gC/2MJRcpCFCFKu6I58t4P.', '3', '华南区', null, null, null, null, null, null, '', '1', '2020-10-30 09:52:38', '1', '2020-10-30 09:52:38');
INSERT INTO `user` VALUES ('6', 'WL0023', '陈明', '$2a$10$mseea54Txd8r7f9Qi/8CV.cPEnUe55gC/2MJRcpCFCFKu6I58t4P.', '4', '江苏', null, null, null, null, null, null, '', '1', '2020-10-30 09:52:38', '1', '2020-10-30 09:52:38');
INSERT INTO `user` VALUES ('7', 'WL0024', 'XX', '$2a$10$mseea54Txd8r7f9Qi/8CV.cPEnUe55gC/2MJRcpCFCFKu6I58t4P.', '4', '浙江', null, null, null, null, null, null, '', '1', '2020-10-30 09:52:38', '1', '2020-10-30 09:52:38');
INSERT INTO `user` VALUES ('9', 'WL00211', '薛明其', '$2a$10$mseea54Txd8r7f9Qi/8CV.cPEnUe55gC/2MJRcpCFCFKu6I58t4P.', '5', '南京', null, null, null, null, null, null, '', '1', '2020-10-30 09:52:38', '1', '2020-10-30 09:52:38');
INSERT INTO `user` VALUES ('10', 'WL00212', '赵同任', '$2a$10$mseea54Txd8r7f9Qi/8CV.cPEnUe55gC/2MJRcpCFCFKu6I58t4P.', '5', '杭州', null, null, null, null, null, null, '', '1', '2020-10-30 09:52:38', '1', '2020-10-30 09:52:38');
INSERT INTO `user` VALUES ('11', 'WL00213', '林森', '$2a$10$mseea54Txd8r7f9Qi/8CV.cPEnUe55gC/2MJRcpCFCFKu6I58t4P.', '6', 'A001', null, null, null, null, null, null, '', '1', '2020-10-30 09:52:38', '1', '2020-10-30 09:52:38');
INSERT INTO `user` VALUES ('12', 'WLDGP001', '刘铁民', '$2a$10$mseea54Txd8r7f9Qi/8CV.cPEnUe55gC/2MJRcpCFCFKu6I58t4P.', '6', 'A002', null, null, null, null, null, null, '', '1', '2020-10-30 09:52:38', '1', '2020-10-30 09:52:38');
INSERT INTO `user` VALUES ('13', 'WLSH_001', '孙一凯', '$2a$10$mseea54Txd8r7f9Qi/8CV.cPEnUe55gC/2MJRcpCFCFKu6I58t4P.', '7', 'A00102', null, null, null, null, null, null, '', '1', '2020-10-30 09:52:38', '1', '2020-10-30 09:52:38');
INSERT INTO `user` VALUES ('14', 'WLDT_001\r\n', '林智慧', '$2a$10$mseea54Txd8r7f9Qi/8CV.cPEnUe55gC/2MJRcpCFCFKu6I58t4P.', '7', 'A00103', null, null, null, null, null, null, '', '1', '2020-10-30 09:52:38', '1', '2020-10-30 09:52:38');
