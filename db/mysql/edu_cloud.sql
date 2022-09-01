/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : localhost:3306
 Source Schema         : edu_cloud

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 01/09/2022 20:23:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_attachment
-- ----------------------------
DROP TABLE IF EXISTS `sys_attachment`;
CREATE TABLE `sys_attachment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '附件名称',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '附件地址',
  `size` bigint(20) NULL DEFAULT NULL COMMENT '附件大小',
  `platform` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '存储平台',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '附件管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_attachment
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dict_type_id` bigint(20) NOT NULL COMMENT '字典类型ID',
  `dict_label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典标签',
  `dict_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典值',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '停用', '0', '', 1, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_dict_data` VALUES (2, 1, '正常', '1', '', 0, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_dict_data` VALUES (3, 2, '男', '0', '', 0, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_dict_data` VALUES (4, 2, '女', '1', '', 1, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_dict_data` VALUES (5, 2, '未知', '2', '', 2, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_dict_data` VALUES (6, 3, '正常', '1', '', 0, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_dict_data` VALUES (7, 3, '停用', '0', '', 1, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_dict_data` VALUES (8, 4, '全部数据', '0', '', 0, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_dict_data` VALUES (9, 4, '本部门及子部门数据', '1', '', 0, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_dict_data` VALUES (10, 4, '本部门数据', '2', '', 0, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_dict_data` VALUES (11, 4, '本人数据', '3', '', 0, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_dict_data` VALUES (12, 4, '自定义数据', '4', '', 0, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_dict_data` VALUES (13, 5, '禁用', '0', '', 1, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_dict_data` VALUES (14, 5, '启用', '1', '', 0, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_dict_data` VALUES (15, 6, '失败', '0', '', 1, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_dict_data` VALUES (16, 6, '成功', '1', '', 0, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_dict_data` VALUES (17, 7, '登录成功', '0', '', 0, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_dict_data` VALUES (18, 7, '退出成功', '1', '', 1, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_dict_data` VALUES (19, 7, '验证码错误', '2', '', 2, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_dict_data` VALUES (20, 7, '账号密码错误', '3', '', 3, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典类型',
  `dict_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, 'post_status', '状态', '岗位管理', 0, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_dict_type` VALUES (2, 'user_gender', '性别', '用户管理', 0, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_dict_type` VALUES (3, 'user_status', '状态', '用户管理', 0, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_dict_type` VALUES (4, 'role_data_scope', '数据范围', '角色管理', 0, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_dict_type` VALUES (5, 'enable_disable', '状态', '功能状态：启用 | 禁用 ', 0, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_dict_type` VALUES (6, 'success_fail', '状态', '操作状态：成功 | 失败', 0, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_dict_type` VALUES (7, 'login_operation', '操作信息', '登录管理', 0, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');

-- ----------------------------
-- Table structure for sys_log_login
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_login`;
CREATE TABLE `sys_log_login`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录IP',
  `address` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录地点',
  `user_agent` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'User Agent',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '登录状态  0：失败   1：成功',
  `operation` tinyint(3) UNSIGNED NULL DEFAULT NULL COMMENT '操作信息   0：登录成功   1：退出成功  2：验证码错误  3：账号密码错误',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '登录日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log_login
-- ----------------------------
INSERT INTO `sys_log_login` VALUES (1, 'admin', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.102 Safari/537.36 Edg/104.0.1293.70', 0, 3, '2022-09-01 20:13:44');
INSERT INTO `sys_log_login` VALUES (2, 'admin', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.102 Safari/537.36 Edg/104.0.1293.70', 0, 3, '2022-09-01 20:13:51');
INSERT INTO `sys_log_login` VALUES (3, 'admin', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.102 Safari/537.36 Edg/104.0.1293.70', 0, 3, '2022-09-01 20:13:58');
INSERT INTO `sys_log_login` VALUES (4, 'admin', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.102 Safari/537.36 Edg/104.0.1293.70', 0, 3, '2022-09-01 20:14:06');
INSERT INTO `sys_log_login` VALUES (5, 'mjh', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.102 Safari/537.36 Edg/104.0.1293.70', 0, 3, '2022-09-01 20:14:18');
INSERT INTO `sys_log_login` VALUES (6, 'admin', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.102 Safari/537.36 Edg/104.0.1293.70', 0, 3, '2022-09-01 20:14:35');
INSERT INTO `sys_log_login` VALUES (7, 'admin', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.102 Safari/537.36 Edg/104.0.1293.70', 0, 2, '2022-09-01 20:15:48');
INSERT INTO `sys_log_login` VALUES (8, 'admin', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.102 Safari/537.36 Edg/104.0.1293.70', 0, 3, '2022-09-01 20:15:57');
INSERT INTO `sys_log_login` VALUES (9, 'admin', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.102 Safari/537.36 Edg/104.0.1293.70', 0, 2, '2022-09-01 20:16:39');
INSERT INTO `sys_log_login` VALUES (10, 'admin', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.102 Safari/537.36 Edg/104.0.1293.70', 1, 0, '2022-09-01 20:17:54');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '上级ID，一级菜单为0',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `authority` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权标识(多个用逗号分隔，如：sys:menu:list,sys:menu:save)',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '类型   0：菜单   1：按钮   2：接口',
  `open_style` tinyint(4) NULL DEFAULT NULL COMMENT '打开方式   0：内部   1：外部',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_pid`(`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '系统设置', NULL, NULL, 0, 0, 'icon-setting', 1, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (2, 1, '菜单管理', 'sys/menu/index', NULL, 0, 0, 'icon-menu', 0, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (3, 2, '查看', '', 'sys:menu:list', 1, 0, '', 0, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (4, 2, '新增', '', 'sys:menu:save', 1, 0, '', 1, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (5, 2, '修改', '', 'sys:menu:update,sys:menu:info', 1, 0, '', 2, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (6, 2, '删除', '', 'sys:menu:delete', 1, 0, '', 3, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (7, 1, '数据字典', 'sys/dict/type', '', 0, 0, 'icon-insertrowabove', 1, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (8, 7, '查询', '', 'sys:dict:page', 1, 0, '', 0, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (9, 7, '新增', '', 'sys:dict:save', 1, 0, '', 2, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (10, 7, '修改', '', 'sys:dict:update,sys:dict:info', 1, 0, '', 1, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (11, 7, '删除', '', 'sys:dict:delete', 1, 0, '', 3, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (12, 0, '权限管理', '', '', 0, 0, 'icon-safetycertificate', 0, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (13, 12, '岗位管理', 'sys/post/index', '', 0, 0, 'icon-solution', 2, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (14, 13, '查询', '', 'sys:post:page', 1, 0, '', 0, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (15, 13, '新增', '', 'sys:post:save', 1, 0, '', 1, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (16, 13, '修改', '', 'sys:post:update,sys:post:info', 1, 0, '', 2, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (17, 13, '删除', '', 'sys:post:delete', 1, 0, '', 3, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (18, 12, '机构管理', 'sys/org/index', '', 0, 0, 'icon-cluster', 1, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (19, 18, '查询', '', 'sys:org:list', 1, 0, '', 0, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (20, 18, '新增', '', 'sys:org:save', 1, 0, '', 1, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (21, 18, '修改', '', 'sys:org:update,sys:org:info', 1, 0, '', 2, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (22, 18, '删除', '', 'sys:org:delete', 1, 0, '', 3, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (23, 12, '角色管理', 'sys/role/index', '', 0, 0, 'icon-team', 3, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (24, 23, '查询', '', 'sys:role:page', 1, 0, '', 0, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (25, 23, '新增', '', 'sys:role:save,sys:role:menu,sys:org:list', 1, 0, '', 1, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (26, 23, '修改', '', 'sys:role:update,sys:role:info,sys:role:menu,sys:org:list,sys:user:page', 1, 0, '', 2, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (27, 23, '删除', '', 'sys:role:delete', 1, 0, '', 3, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (28, 12, '用户管理', 'sys/user/index', '', 0, 0, 'icon-user', 0, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (29, 28, '查询', '', 'sys:user:page', 1, 0, '', 0, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (30, 28, '新增', '', 'sys:user:save,sys:role:list', 1, 0, '', 1, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (31, 28, '修改', '', 'sys:user:update,sys:user:info,sys:role:list', 1, 0, '', 2, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (32, 28, '删除', '', 'sys:user:delete', 1, 0, '', 3, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (33, 0, '应用管理', '', '', 0, 0, 'icon-appstore', 2, 0, 1, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 20:18:25');
INSERT INTO `sys_menu` VALUES (34, 1, '附件管理', 'sys/attachment/index', NULL, 0, 0, 'icon-folder-fill', 3, 0, 1, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 20:18:38');
INSERT INTO `sys_menu` VALUES (35, 34, '查看', '', 'sys:attachment:page', 1, 0, '', 0, 0, 1, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 20:18:33');
INSERT INTO `sys_menu` VALUES (36, 34, '上传', '', 'sys:attachment:save', 1, 0, '', 1, 0, 1, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 20:18:34');
INSERT INTO `sys_menu` VALUES (37, 34, '删除', '', 'sys:attachment:delete', 1, 0, '', 1, 0, 1, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 20:18:36');
INSERT INTO `sys_menu` VALUES (38, 0, '日志管理', '', '', 0, 0, 'icon-filedone', 3, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');
INSERT INTO `sys_menu` VALUES (39, 38, '登录日志', 'sys/log/login', 'sys:log:login', 0, 0, 'icon-solution', 0, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '上级ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机构名称',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_pid`(`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '机构管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_org
-- ----------------------------

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `post_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '岗位编码',
  `post_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '岗位名称',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态  0：停用   1：正常',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '岗位管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `data_scope` tinyint(4) NULL DEFAULT NULL COMMENT '数据范围  0：全部数据  1：本部门及子部门数据  2：本部门数据  3：本人数据  4：自定义数据',
  `org_id` bigint(20) NULL DEFAULT NULL COMMENT '机构ID',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_org_id`(`org_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_data_scope
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_data_scope`;
CREATE TABLE `sys_role_data_scope`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `org_id` bigint(20) NULL DEFAULT NULL COMMENT '机构ID',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_id`(`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色数据权限' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_data_scope
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单ID',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_id`(`role_id`) USING BTREE,
  INDEX `idx_menu_id`(`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色菜单关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `gender` tinyint(4) NULL DEFAULT NULL COMMENT '性别   0：男   1：女   2：未知',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `org_id` bigint(20) NULL DEFAULT NULL COMMENT '机构ID',
  `super_admin` tinyint(4) NULL DEFAULT NULL COMMENT '超级管理员   0：否   1：是',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态  0：停用   1：正常',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10001 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (10000, 'admin', '{bcrypt}$2a$10$mW/yJPHjyueQ1g26WNBz0uxVPa0GQdJO1fFZmqdkqgMTGnyszlXxu', 'admin', 'https://cdn.maku.net/images/avatar.png', 0, 'babamu@126.com', '13612345678', NULL, 1, 1, 0, 0, 10000, '2022-09-01 19:45:42', 10000, '2022-09-01 19:45:42');

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `post_id` bigint(20) NULL DEFAULT NULL COMMENT '岗位ID',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE,
  INDEX `idx_post_id`(`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户岗位关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  `deleted` tinyint(4) NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_id`(`role_id`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
