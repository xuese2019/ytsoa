/*
Navicat MySQL Data Transfer

Source Server         : 6
Source Server Version : 50724
Source Host           : 192.168.1.222:3306
Source Database       : ytsoa

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2018-12-26 17:30:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account_table
-- ----------------------------
DROP TABLE IF EXISTS `account_table`;
CREATE TABLE `account_table` (
  `uuid` varchar(36) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `lx` int(10) DEFAULT NULL,
  `bmid` varchar(255) DEFAULT NULL,
  `is_login` varchar(255) DEFAULT NULL,
  `systimes` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `rzrq` date DEFAULT NULL,
  `creator_acc_id` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_table
-- ----------------------------
INSERT INTO `account_table` VALUES ('1', null, '123456', 'e10adc3949ba59abbe56e057f20f883e', null, '72ef14ed081a11e986ab00269e9c3a5e', 'Y', '2018-12-26 15:34:19', null, null, null, null);
INSERT INTO `account_table` VALUES ('3d6c548408e911e9ba7500269e9c3a5e', '123', '123', 'e10adc3949ba59abbe56e057f20f883e', '1', '7881c824082111e986ab00269e9c3a5e', 'Y', '2018-12-26 16:35:44', '2018-12-26', '1', '男', '12345678912');

-- ----------------------------
-- Table structure for admin_table
-- ----------------------------
DROP TABLE IF EXISTS `admin_table`;
CREATE TABLE `admin_table` (
  `uuid` varchar(255) NOT NULL,
  `account` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `is_login` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_table
-- ----------------------------
INSERT INTO `admin_table` VALUES ('1234', 'admin', 'e10adc3949ba59abbe56e057f20f883e', null);

-- ----------------------------
-- Table structure for qxgl_table
-- ----------------------------
DROP TABLE IF EXISTS `qxgl_table`;
CREATE TABLE `qxgl_table` (
  `uuid` varchar(255) NOT NULL,
  `qxmc` varchar(255) DEFAULT NULL,
  `qxbs` varchar(255) DEFAULT NULL,
  `qxfj` varchar(255) DEFAULT NULL,
  `qxlx` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qxgl_table
-- ----------------------------
INSERT INTO `qxgl_table` VALUES ('1', '员工管理主页面', 'accountPage', '11', '1');
INSERT INTO `qxgl_table` VALUES ('11', '人事管理', 'rsgl', '0', '1');
INSERT INTO `qxgl_table` VALUES ('2', '员工管理新增', 'accountAdd', '1', '2');
INSERT INTO `qxgl_table` VALUES ('3', '员工管理修改', 'accountUpdate', '1', '2');
INSERT INTO `qxgl_table` VALUES ('4', '员工管理删除', 'accountDelete', '1', '2');
INSERT INTO `qxgl_table` VALUES ('5', '项目委派管理', 'xmwpgl', '0', '1');
INSERT INTO `qxgl_table` VALUES ('6', '项目委派', 'xmwpApply', '5', '1');
INSERT INTO `qxgl_table` VALUES ('7', '项目委派管理', 'xmwpglPage', '5', '1');

-- ----------------------------
-- Table structure for xmfl_table
-- ----------------------------
DROP TABLE IF EXISTS `xmfl_table`;
CREATE TABLE `xmfl_table` (
  `uuid` varchar(255) NOT NULL,
  `xmflmc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xmfl_table
-- ----------------------------
INSERT INTO `xmfl_table` VALUES ('19888498081011e986ab00269e9c3a5e', '333334444');
INSERT INTO `xmfl_table` VALUES ('d9afd82b080f11e986ab00269e9c3a5e', 'A级项目');

-- ----------------------------
-- Table structure for ywlx_table
-- ----------------------------
DROP TABLE IF EXISTS `ywlx_table`;
CREATE TABLE `ywlx_table` (
  `uuid` varchar(255) NOT NULL,
  `ywlxmc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ywlx_table
-- ----------------------------
INSERT INTO `ywlx_table` VALUES ('27eb2cbb080111e986ab00269e9c3a5e', '1510086');
INSERT INTO `ywlx_table` VALUES ('29d1c458080111e986ab00269e9c3a5e', '16');
INSERT INTO `ywlx_table` VALUES ('2c872efb080111e986ab00269e9c3a5e', '17');
INSERT INTO `ywlx_table` VALUES ('2eb435eb080111e986ab00269e9c3a5e', '18');
INSERT INTO `ywlx_table` VALUES ('317427bf080111e986ab00269e9c3a5e', '19');
INSERT INTO `ywlx_table` VALUES ('3367b347080111e986ab00269e9c3a5e', '20');
INSERT INTO `ywlx_table` VALUES ('6ece449e080011e986ab00269e9c3a5e', '4');
INSERT INTO `ywlx_table` VALUES ('70f9eb1f080011e986ab00269e9c3a5e', '5');
INSERT INTO `ywlx_table` VALUES ('8e23a3d907fd11e986ab00269e9c3a5e', '223');
INSERT INTO `ywlx_table` VALUES ('a6dfc68107fd11e986ab00269e9c3a5e', '323');
INSERT INTO `ywlx_table` VALUES ('ba31629d080011e986ab00269e9c3a5e', '6');
INSERT INTO `ywlx_table` VALUES ('bbfb5c64080011e986ab00269e9c3a5e', '7');
INSERT INTO `ywlx_table` VALUES ('bea16903080011e986ab00269e9c3a5e', '8');
INSERT INTO `ywlx_table` VALUES ('c100bd8d080011e986ab00269e9c3a5e', '9');

-- ----------------------------
-- Table structure for zzjg_table
-- ----------------------------
DROP TABLE IF EXISTS `zzjg_table`;
CREATE TABLE `zzjg_table` (
  `uuid` varchar(255) NOT NULL,
  `zzjgfj` varchar(255) DEFAULT NULL,
  `zzjgmc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zzjg_table
-- ----------------------------
INSERT INTO `zzjg_table` VALUES ('72ef14ed081a11e986ab00269e9c3a5e', '0', '董事1111');
INSERT INTO `zzjg_table` VALUES ('7881c824082111e986ab00269e9c3a5e', '0', '222');
INSERT INTO `zzjg_table` VALUES ('7a7b5df9081a11e986ab00269e9c3a5e', '0', '董事2');
INSERT INTO `zzjg_table` VALUES ('7e3bfc99082111e986ab00269e9c3a5e', '72ef14ed081a11e986ab00269e9c3a5e', '3333');
INSERT INTO `zzjg_table` VALUES ('858ae744082111e986ab00269e9c3a5e', '7881c824082111e986ab00269e9c3a5e', '到达');
INSERT INTO `zzjg_table` VALUES ('86982622082111e986ab00269e9c3a5e', '72ef14ed081a11e986ab00269e9c3a5e', '888888');

-- ----------------------------
-- Table structure for zz_qx_table
-- ----------------------------
DROP TABLE IF EXISTS `zz_qx_table`;
CREATE TABLE `zz_qx_table` (
  `uuid` varchar(255) NOT NULL,
  `qxid` varchar(255) DEFAULT NULL,
  `zzid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zz_qx_table
-- ----------------------------
INSERT INTO `zz_qx_table` VALUES ('4856131008ed11e9ba7500269e9c3a5e', '11', '72ef14ed081a11e986ab00269e9c3a5e');
INSERT INTO `zz_qx_table` VALUES ('485a928c08ed11e9ba7500269e9c3a5e', '1', '72ef14ed081a11e986ab00269e9c3a5e');
INSERT INTO `zz_qx_table` VALUES ('485ff1b708ed11e9ba7500269e9c3a5e', '2', '72ef14ed081a11e986ab00269e9c3a5e');
INSERT INTO `zz_qx_table` VALUES ('486265ee08ed11e9ba7500269e9c3a5e', '3', '72ef14ed081a11e986ab00269e9c3a5e');
INSERT INTO `zz_qx_table` VALUES ('4864b8cb08ed11e9ba7500269e9c3a5e', '4', '72ef14ed081a11e986ab00269e9c3a5e');
INSERT INTO `zz_qx_table` VALUES ('48677bba08ed11e9ba7500269e9c3a5e', '5', '72ef14ed081a11e986ab00269e9c3a5e');
INSERT INTO `zz_qx_table` VALUES ('4869ce0108ed11e9ba7500269e9c3a5e', '6', '72ef14ed081a11e986ab00269e9c3a5e');
INSERT INTO `zz_qx_table` VALUES ('486c920d08ed11e9ba7500269e9c3a5e', '7', '72ef14ed081a11e986ab00269e9c3a5e');
