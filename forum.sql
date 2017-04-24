/*
Navicat MySQL Data Transfer

Source Server         : forum
Source Server Version : 50624
Source Host           : 127.0.0.1:3306
Source Database       : forum_system

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-04-24 18:36:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_visit_permission_init
-- ----------------------------
DROP TABLE IF EXISTS `sys_visit_permission_init`;
CREATE TABLE `sys_visit_permission_init` (
  `l_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统访问权限-唯一标识',
  `vc_visit_path` varchar(255) NOT NULL COMMENT '访问路径',
  `vc_permission_init` varchar(255) NOT NULL COMMENT '权限配置',
  `l_sort` int(255) NOT NULL COMMENT '排序',
  `vc_status` varchar(10) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`l_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_visit_permission_init
-- ----------------------------
INSERT INTO `sys_visit_permission_init` VALUES ('1', '/**', 'authc', '1', '0');
INSERT INTO `sys_visit_permission_init` VALUES ('2', '/rest/login/logout', 'logout', '997', '0');
INSERT INTO `sys_visit_permission_init` VALUES ('3', '/rest/login/initLoginPage', 'anon', '998', '0');
INSERT INTO `sys_visit_permission_init` VALUES ('5', '/static/**', 'anon', '999', '0');
