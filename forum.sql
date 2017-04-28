/*
Navicat MySQL Data Transfer

Source Server         : forum
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : forum_system

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-04-28 08:10:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_data_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `sys_data_dictionary`;
CREATE TABLE `sys_data_dictionary` (
  `l_data_code` bigint(255) NOT NULL AUTO_INCREMENT COMMENT '数据字典code',
  `l_data_type` bigint(20) DEFAULT NULL COMMENT '数据字典类型',
  `vc_data_name` varchar(255) DEFAULT NULL COMMENT '数据字典名称',
  `vc_data_order` varchar(20) DEFAULT NULL COMMENT '排序',
  `vc_data_status` varchar(10) DEFAULT NULL COMMENT '数据字典状态',
  PRIMARY KEY (`l_data_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_data_dictionary
-- ----------------------------

-- ----------------------------
-- Table structure for sys_data_dictionary_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_data_dictionary_type`;
CREATE TABLE `sys_data_dictionary_type` (
  `l_data_type_id` bigint(255) NOT NULL AUTO_INCREMENT COMMENT '数据字典类型id',
  `vc_data_type_name` varchar(255) DEFAULT NULL COMMENT '数据字典类型名称',
  PRIMARY KEY (`l_data_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_data_dictionary_type
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `l_user_id` bigint(255) NOT NULL AUTO_INCREMENT COMMENT '用户名',
  `vc_user_name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `vc_login_password` varchar(255) DEFAULT NULL COMMENT '密码',
  `vc_login_name` varchar(255) DEFAULT NULL COMMENT '登录名',
  `l_mobile_phone` varchar(30) DEFAULT NULL COMMENT '移动电话',
  `vc_email_adress` varchar(255) DEFAULT NULL COMMENT '邮箱地址',
  `vc_card_no` varchar(50) DEFAULT NULL COMMENT '身份证号',
  `l_current_status` int(10) DEFAULT '0' COMMENT '用户当前状态',
  `l_lock_status` int(10) DEFAULT '0' COMMENT '用户锁定状态',
  `l_error_count` int(10) NOT NULL COMMENT '记录登录错误次数',
  `vc_curent_login_adress` varchar(255) NOT NULL COMMENT '当前登录地址',
  `vc_curent_login_ip` varchar(255) DEFAULT NULL COMMENT '当前登录IP',
  `vc_cureent_login_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '当前登录时间',
  `d_del_date_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '删除时间',
  PRIMARY KEY (`l_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------

-- ----------------------------
-- Table structure for sys_visit_permission_init
-- ----------------------------
DROP TABLE IF EXISTS `sys_visit_permission_init`;
CREATE TABLE `sys_visit_permission_init` (
  `l_id` bigint(255) NOT NULL AUTO_INCREMENT COMMENT '系统访问权限-唯一标识',
  `vc_visit_path` varchar(255) NOT NULL COMMENT '访问路径',
  `vc_permission_init` varchar(255) NOT NULL COMMENT '权限配置',
  `l_sort` int(255) NOT NULL COMMENT '排序',
  `vc_status` varchar(10) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`l_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_visit_permission_init
-- ----------------------------
INSERT INTO `sys_visit_permission_init` VALUES ('1', '/**', 'authc', '2', '0');
INSERT INTO `sys_visit_permission_init` VALUES ('2', '/rest/login/logout', 'logout', '997', '0');
INSERT INTO `sys_visit_permission_init` VALUES ('3', '/rest/login/initLoginPage', 'anon', '998', '0');
INSERT INTO `sys_visit_permission_init` VALUES ('5', '/static/**', 'anon', '999', '0');
INSERT INTO `sys_visit_permission_init` VALUES ('8', '/rest/**', 'anon', '996', '1');
SET FOREIGN_KEY_CHECKS=1;
