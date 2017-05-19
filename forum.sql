/*
Navicat MySQL Data Transfer

Source Server         : forum
Source Server Version : 50624
Source Host           : 127.0.0.1:3306
Source Database       : forum_system

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-05-19 19:02:45
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_data_dictionary_type
-- ----------------------------

-- ----------------------------
-- Table structure for sys_login_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_record`;
CREATE TABLE `sys_login_record` (
  `l_login_id` int(11) NOT NULL AUTO_INCREMENT,
  `l_user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `vc_curent_login_adress` varchar(255) DEFAULT NULL COMMENT '当前登录地址',
  `vc_curent_login_ip` varchar(255) DEFAULT NULL COMMENT '当前登录IP',
  `d_cureent_login_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '当前登录时间',
  PRIMARY KEY (`l_login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_login_record
-- ----------------------------
INSERT INTO `sys_login_record` VALUES ('1', '1000', '', '192.168.1.43', '2017-05-05 09:17:48');
INSERT INTO `sys_login_record` VALUES ('2', '1000', '', '127.0.0.1', '2017-05-19 11:12:44');
INSERT INTO `sys_login_record` VALUES ('3', '1000', '', '127.0.0.1', '2017-05-19 13:20:48');

-- ----------------------------
-- Table structure for sys_menu_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_item`;
CREATE TABLE `sys_menu_item` (
  `l_menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `l_parent_menu_id` bigint(20) DEFAULT NULL COMMENT '父级菜单id',
  `vc_menu_show_type` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '设置一级菜单下面的子菜单显示类型(group-menu、tree-menu、single-menu)',
  `vc_menu_key` varchar(255) DEFAULT NULL COMMENT '菜单键',
  `vc_icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `vc_url` varchar(255) DEFAULT NULL,
  `l_status` int(10) DEFAULT '0' COMMENT '状态',
  `vc_menu_describe` varchar(255) DEFAULT NULL COMMENT '菜单描述',
  PRIMARY KEY (`l_menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1022 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu_item
-- ----------------------------
INSERT INTO `sys_menu_item` VALUES ('1000', '0', 'single-menu', 'menu.root.home', null, null, '0', '首页');
INSERT INTO `sys_menu_item` VALUES ('1001', '0', 'group-menu', 'menu.root.uifeatures', null, null, '0', 'UI Features');
INSERT INTO `sys_menu_item` VALUES ('1002', '0', 'single-menu', 'menu.root.layout', null, null, '0', 'Layout');
INSERT INTO `sys_menu_item` VALUES ('1003', '0', 'group-menu', 'menu.root.components', null, null, '0', 'Components');
INSERT INTO `sys_menu_item` VALUES ('1004', '0', 'tree-menu', 'menu.root.more', null, null, '0', 'More');
INSERT INTO `sys_menu_item` VALUES ('1005', '0', 'tree-menu', 'menu.root.pages', null, null, '0', 'Pages');
INSERT INTO `sys_menu_item` VALUES ('1006', '1001', 'group-menu', 'menu.uifeatures.buttons', 'icon-bulb', null, '0', 'Item 1');
INSERT INTO `sys_menu_item` VALUES ('1007', '1001', 'group-menu', 'menu.uifeatures.colors', 'icon-settings', null, '0', 'Item 2');
INSERT INTO `sys_menu_item` VALUES ('1008', '1001', 'group-menu', 'menu.uifeatures.generalcomponents', 'icon-settings', null, '0', 'Item 3');
INSERT INTO `sys_menu_item` VALUES ('1009', '1001', 'group-menu', 'menu.uifeatures.spinnerbuttons', 'icon-settings', null, '0', 'Item 4');
INSERT INTO `sys_menu_item` VALUES ('1010', '1001', 'group-menu', 'menu.uifeatures.fonticons ', 'icon-settings', null, '0', 'Item 5');
INSERT INTO `sys_menu_item` VALUES ('1011', '1001', 'group-menu', 'menu.uifeatures.socialicons', 'icon-settings', null, '0', 'Item 6');
INSERT INTO `sys_menu_item` VALUES ('1012', '1001', 'group-menu', 'menu.uifeatures.typography', 'icon-settings', null, '0', 'Item 7');
INSERT INTO `sys_menu_item` VALUES ('1013', '1002', 'single-menu', 'menu.layout.lightmegamenu', 'icon-settings', null, '0', 'Item 1');
INSERT INTO `sys_menu_item` VALUES ('1014', '1002', 'single-menu', 'menu.layout.yaout1', 'icon-settings', null, '0', 'Item 2');
INSERT INTO `sys_menu_item` VALUES ('1015', '1002', 'single-menu', 'menu.layout.yaout2', 'icon-settings', null, '0', 'Item 3');
INSERT INTO `sys_menu_item` VALUES ('1016', '1002', 'single-menu', 'menu.layout.yaout3', 'icon-settings', null, '0', 'Item 4');
INSERT INTO `sys_menu_item` VALUES ('1017', '1002', 'single-menu', 'menu.layout.yaout4', 'icon-settings', null, '0', 'Item 5');
INSERT INTO `sys_menu_item` VALUES ('1018', '1002', 'single-menu', 'menu.layout.yaout5', 'icon-settings', null, '0', 'Item 6');
INSERT INTO `sys_menu_item` VALUES ('1019', '1004', 'single-menu', 'menu.more.more1', 'icon-settings', null, '0', 'Item 1');
INSERT INTO `sys_menu_item` VALUES ('1020', '1004', 'tree-menu', 'menu.more.more2', 'icon-settings', null, '0', 'Item 2');
INSERT INTO `sys_menu_item` VALUES ('1021', '1020', 'single-menu', 'menu.more2.more1', 'icon-settings', null, '0', 'Item 2-1');

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
  `l_user_type` int(10) DEFAULT NULL COMMENT '用户类型（0：前端: 1：后端）',
  `l_lock_status` int(10) DEFAULT '0' COMMENT '用户锁定状态',
  `l_error_count` int(10) NOT NULL COMMENT '记录登录错误次数',
  `d_del_date_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '删除时间',
  `d_lock_date_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '用户锁定时间',
  PRIMARY KEY (`l_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1000', '管理员', '21218cca77804d2ba1922c33e0151105', 'admin', '18717905137', 'carl_email_96@163.com', null, '0', '1', '0', '0', '2017-05-19 13:20:48', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_visit_permission_init
-- ----------------------------
INSERT INTO `sys_visit_permission_init` VALUES ('1', '/**', 'authc', '1', '0');
INSERT INTO `sys_visit_permission_init` VALUES ('2', '/system/logout', 'logout', '999997', '0');
INSERT INTO `sys_visit_permission_init` VALUES ('3', '/system/login', 'anon', '999998', '0');
INSERT INTO `sys_visit_permission_init` VALUES ('5', '/static/**', 'anon', '999999', '0');
INSERT INTO `sys_visit_permission_init` VALUES ('8', '/rest/**', 'authc', '999950', '0');
INSERT INTO `sys_visit_permission_init` VALUES ('9', '/system/401', 'anon', '999987', '0');
INSERT INTO `sys_visit_permission_init` VALUES ('10', '/system/404', 'anon', '999988', '0');
INSERT INTO `sys_visit_permission_init` VALUES ('11', '/system/500', 'anon', '999989', '0');
INSERT INTO `sys_visit_permission_init` VALUES ('12', '/system/400', 'anon', '999986', '0');
INSERT INTO `sys_visit_permission_init` VALUES ('13', '/system/signIn', 'anon', '999996', '0');
INSERT INTO `sys_visit_permission_init` VALUES ('15', '/system/405', 'anon', '999985', '0');
INSERT INTO `sys_visit_permission_init` VALUES ('16', '/localeLanguage/langType', 'anon', '999995', '0');
INSERT INTO `sys_visit_permission_init` VALUES ('17', '/system/login?lang=zh_CN', 'anon', '999998', '0');
INSERT INTO `sys_visit_permission_init` VALUES ('18', '/system/login?lang=en_US', 'anon', '999998', '0');
