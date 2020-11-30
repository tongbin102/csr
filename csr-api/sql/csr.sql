/*
Navicat MySQL Data Transfer

Source Server         : 10.55.3.4_3306
Source Server Version : 50731
Source Host           : 10.55.3.4:3306
Source Database       : csr

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2020-11-28 16:03:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for channel
-- ----------------------------
DROP TABLE IF EXISTS `channel`;
CREATE TABLE `channel` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '渠道code',
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '渠道名称',
  `question_prefix` varchar(10) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '题目首字母',
  `ctype` int(1) DEFAULT NULL COMMENT '渠道类型（1考核；2扣分）',
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
INSERT INTO `channel` VALUES ('1', 'survey', '客户调研', 'S', '1', '', '1', '2020-10-30 12:31:03', '1', '2020-10-30 12:31:03');
INSERT INTO `channel` VALUES ('2', 'monitor', '过程监控', 'P', '1', '', '1', '2020-10-30 12:31:03', '1', '2020-10-30 12:31:03');
INSERT INTO `channel` VALUES ('3', 'assistance', '服务助手', 'F', '1', '', '1', '2020-10-30 12:31:03', '1', '2020-10-30 12:31:03');
INSERT INTO `channel` VALUES ('4', 'complain', '投诉反馈', 'C', '1', '', '1', '2020-10-30 12:31:03', '1', '2020-10-30 12:31:03');
INSERT INTO `channel` VALUES ('5', 'rescue', '道路救援', 'R', '1', '', '1', '2020-10-30 12:31:03', '1', '2020-10-30 12:31:03');
INSERT INTO `channel` VALUES ('6', 'data', '数据准确性', 'D', '2', '', '1', '2020-10-30 12:31:03', '1', '2020-10-30 12:31:03');

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '城市code',
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '城市名称',
  `province_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '所属省份code',
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='城市表';

-- ----------------------------
-- Records of city
-- ----------------------------

-- ----------------------------
-- Table structure for element
-- ----------------------------
DROP TABLE IF EXISTS `element`;
CREATE TABLE `element` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '要素code',
  `factor_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '因子code',
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '考核单元',
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

-- ----------------------------
-- Table structure for factor
-- ----------------------------
DROP TABLE IF EXISTS `factor`;
CREATE TABLE `factor` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
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
INSERT INTO `factor` VALUES ('1', '预约', '预约', '', '1', '2020-10-30 12:31:03', '1', '2020-10-30 12:31:03');
INSERT INTO `factor` VALUES ('2', '服务流程', '服务流程', '', '1', '2020-10-30 12:31:03', '1', '2020-10-30 12:31:03');
INSERT INTO `factor` VALUES ('3', '交付效率', '交付效率', '', '1', '2020-10-30 12:31:03', '1', '2020-10-30 12:31:03');
INSERT INTO `factor` VALUES ('4', '硬件设施', '硬件设施', '', '1', '2020-10-30 12:31:03', '1', '2020-10-30 12:31:03');
INSERT INTO `factor` VALUES ('5', '服务质量', '服务质量', '', '1', '2020-10-30 12:31:03', '1', '2020-10-30 12:31:03');
INSERT INTO `factor` VALUES ('6', '服务价值', '服务价值', '', '1', '2020-10-30 12:31:03', '1', '2020-10-30 12:31:03');

-- ----------------------------
-- Table structure for province
-- ----------------------------
DROP TABLE IF EXISTS `province`;
CREATE TABLE `province` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '省份code',
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '省份名称',
  `region_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '所属大区code',
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='省份表';

-- ----------------------------
-- Records of province
-- ----------------------------

-- ----------------------------
-- Table structure for question_assistance
-- ----------------------------
DROP TABLE IF EXISTS `question_assistance`;
CREATE TABLE `question_assistance` (
  `id` bigint(20) NOT NULL,
  `regulation_description` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '细则描述',
  `series_no` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '题目序号',
  `analysis_point` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '分析要点',
  `kpi` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT 'KPI指标',
  `kpi_description` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT 'KPI说明',
  `excellent` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '优秀',
  `good` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '优良',
  `standard` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '达标',
  `weak` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '薄弱',
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='服务助手评分规则表';

-- ----------------------------
-- Records of question_assistance
-- ----------------------------

-- ----------------------------
-- Table structure for question_data
-- ----------------------------
DROP TABLE IF EXISTS `question_data`;
CREATE TABLE `question_data` (
  `id` bigint(20) NOT NULL,
  `regulation_description` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '细则描述',
  `series_no` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '题目序号',
  `analysis_point` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '分析要点',
  `kpi` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT 'KPI指标',
  `kpi_description` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT 'KPI说明',
  `deduct` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '不达标后扣分',
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='道路救援评分规则表';

-- ----------------------------
-- Records of question_data
-- ----------------------------

-- ----------------------------
-- Table structure for question_monitor
-- ----------------------------
DROP TABLE IF EXISTS `question_monitor`;
CREATE TABLE `question_monitor` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `regulation_description` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '细则描述',
  `series_no` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '题目序号',
  `suggestion` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '考核方法建议',
  `description` varchar(1024) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '相关说明',
  `excellent` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '优秀',
  `good` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '优良',
  `standard` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '达标',
  `weak` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '薄弱',
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='过程监控评分规则表';

-- ----------------------------
-- Records of question_monitor
-- ----------------------------

-- ----------------------------
-- Table structure for question_rescue
-- ----------------------------
DROP TABLE IF EXISTS `question_rescue`;
CREATE TABLE `question_rescue` (
  `id` bigint(20) NOT NULL,
  `regulation_description` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '细则描述',
  `series_no` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '题目序号',
  `analysis_point` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '分析要点',
  `kpi` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT 'KPI指标',
  `kpi_description` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT 'KPI说明',
  `deduct` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '不达标后扣分',
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='道路救援评分规则表';

-- ----------------------------
-- Records of question_rescue
-- ----------------------------

-- ----------------------------
-- Table structure for question_survey
-- ----------------------------
DROP TABLE IF EXISTS `question_survey`;
CREATE TABLE `question_survey` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `regulation_description` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '细则描述',
  `series_no` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '题目序号',
  `description` varchar(1024) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '题目描述',
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
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='客户调研评分规则表';

-- ----------------------------
-- Records of question_survey
-- ----------------------------

-- ----------------------------
-- Table structure for region
-- ----------------------------
DROP TABLE IF EXISTS `region`;
CREATE TABLE `region` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '大区code',
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '大区名称',
  `national_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '全国code',
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='大区表';

-- ----------------------------
-- Records of region
-- ----------------------------

-- ----------------------------
-- Table structure for regulation
-- ----------------------------
DROP TABLE IF EXISTS `regulation`;
CREATE TABLE `regulation` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `element_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '因子要素code',
  `description` varchar(1024) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '细则描述',
  `score_type` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '类别',
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='因子要素细则表';

-- ----------------------------
-- Records of regulation
-- ----------------------------

-- ----------------------------
-- Table structure for regulation_score
-- ----------------------------
DROP TABLE IF EXISTS `regulation_score`;
CREATE TABLE `regulation_score` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `period` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '期数',
  `store_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '店code',
  `regulation_description` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '细则描述',
  `score_type` int(1) DEFAULT NULL COMMENT '成绩类型：1.考核项 2. 加分项 3. 扣分项',
  `score` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '得分',
  `grade` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '等级',
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='因子要素细则评分规则表';

-- ----------------------------
-- Records of regulation_score
-- ----------------------------

-- ----------------------------
-- Table structure for regulation_score_channel
-- ----------------------------
DROP TABLE IF EXISTS `regulation_score_channel`;
CREATE TABLE `regulation_score_channel` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `period` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '期数',
  `store_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '店code',
  `regulation_description` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '细则描述',
  `channel_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '渠道code',
  `score_type` int(1) DEFAULT NULL COMMENT '成绩类型：1.考核项 2. 加分项 3. 扣分项',
  `score` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '得分',
  `grade` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '等级',
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='因子要素细则分渠道评分规则表';

-- ----------------------------
-- Records of regulation_score_channel
-- ----------------------------

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
INSERT INTO `role` VALUES ('2', 'national', 'national', 'national', '', '1', '2020-10-30 09:21:29', '1', '2020-07-20 13:45:39');
INSERT INTO `role` VALUES ('3', 'region', 'region', 'region', '', '1', '2020-10-30 09:21:29', '1', '2020-07-20 13:45:39');
INSERT INTO `role` VALUES ('4', 'area', 'area', 'area', '', '1', '2020-10-30 09:21:29', '1', '2020-07-20 13:45:39');
INSERT INTO `role` VALUES ('5', 'superior', 'superior', 'superior', '', '1', '2020-10-30 09:21:29', '1', '2020-07-20 13:45:39');
INSERT INTO `role` VALUES ('6', 'store', 'store', 'store', '', '1', '2020-10-30 09:21:29', '1', '2020-07-20 13:45:39');

-- ----------------------------
-- Table structure for scope
-- ----------------------------
DROP TABLE IF EXISTS `scope`;
CREATE TABLE `scope` (
  `id` bigint(20) NOT NULL COMMENT '主键',
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
INSERT INTO `scope` VALUES ('3', 'province', '省份', '', '1', '2020-10-30 14:14:32', '1', '2020-10-30 14:14:32');
INSERT INTO `scope` VALUES ('4', 'city', '城市', '', '1', '2020-10-30 14:14:32', '1', '2020-10-30 14:14:32');
INSERT INTO `scope` VALUES ('5', 'superior', '一级店', '', '1', '2020-10-30 14:14:32', '1', '2020-10-30 14:14:32');
INSERT INTO `scope` VALUES ('6', 'store', '经销店', '', '1', '2020-10-30 14:14:32', '1', '2020-10-30 14:14:32');

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `period` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '期数',
  `scope_id` bigint(20) DEFAULT NULL COMMENT '范围id',
  `store_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '店code',
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '名称',
  `score` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '总分数',
  `rank_country` int(20) DEFAULT NULL COMMENT '排名',
  `rank_scope` int(20) DEFAULT NULL COMMENT '区域排名',
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

-- ----------------------------
-- Table structure for score_channel
-- ----------------------------
DROP TABLE IF EXISTS `score_channel`;
CREATE TABLE `score_channel` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `period` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '期数',
  `scope_id` bigint(20) DEFAULT NULL COMMENT '范围id',
  `store_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '店code',
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '名称',
  `channel_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '渠道code',
  `score` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '分渠道分数',
  `rank_country` int(11) DEFAULT NULL COMMENT '全国排名',
  `rank_scope` int(11) DEFAULT NULL COMMENT '区域排名',
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

-- ----------------------------
-- Table structure for score_factor
-- ----------------------------
DROP TABLE IF EXISTS `score_factor`;
CREATE TABLE `score_factor` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `period` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '期数',
  `scope_id` bigint(20) DEFAULT NULL COMMENT '范围id',
  `store_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '店code',
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '名称',
  `factor_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '因子code',
  `score` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '分因子分数',
  `rank_country` int(11) DEFAULT NULL COMMENT '全国排名',
  `rank_scope` int(11) DEFAULT NULL COMMENT '区域排名',
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

-- ----------------------------
-- Table structure for score_question
-- ----------------------------
DROP TABLE IF EXISTS `score_question`;
CREATE TABLE `score_question` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `period` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '期数',
  `store_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '店code',
  `channel_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '渠道code',
  `question_series_no` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '题目序号',
  `score` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '总分数',
  `grade` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '等级',
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='门店题目-得分表';

-- ----------------------------
-- Records of score_question
-- ----------------------------

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '门店code',
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '门店名称',
  `scale` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '规模',
  `city_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '所属城市code',
  `parent_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '所属一级门店code',
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='门店表';

-- ----------------------------
-- Records of store
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=1332308800315150339 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', '$2a$10$mseea54Txd8r7f9Qi/8CV.cPEnUe55gC/2MJRcpCFCFKu6I58t4P.', '1', null, '3', null, null, null, null, null, '', 'admin', '2020-10-30 09:52:38', 'admin', '2020-10-30 09:52:38');

-- ----------------------------
-- Table structure for user_store
-- ----------------------------
DROP TABLE IF EXISTS `user_store`;
CREATE TABLE `user_store` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `user_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '用户code',
  `scope_id` bigint(20) DEFAULT NULL COMMENT '范围id',
  `province_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '所辖省份code',
  `city_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '所辖城市code',
  `superior_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '所辖一级code',
  `store_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '所辖二级店code',
  `valid_ind` bit(1) DEFAULT NULL COMMENT '有效标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='用户-区域/店关系表';

-- ----------------------------
-- Records of user_store
-- ----------------------------
