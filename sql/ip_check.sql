/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50130
Source Host           : localhost:3306
Source Database       : spam_tools

Target Server Type    : MYSQL
Target Server Version : 50130
File Encoding         : 65001

Date: 2013-11-13 16:26:27
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `ip_check`
-- ----------------------------
DROP TABLE IF EXISTS `ip_check`;
CREATE TABLE `ip_check` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) NOT NULL COMMENT 'ip地址',
  `last_used` date DEFAULT NULL COMMENT '最后一次使用时间',
  `gmt_created` date NOT NULL COMMENT 'ip入库时间',
  `speed` bigint(20) DEFAULT NULL COMMENT 'ip回应速度',
  `isCheck` int(2) DEFAULT NULL COMMENT '是否已检测',
  PRIMARY KEY (`id`,`ip`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ip_check
-- ----------------------------
INSERT INTO `ip_check` VALUES ('1', '192.168.10.224', '2013-11-13', '2013-11-13', '2', null);
INSERT INTO `ip_check` VALUES ('2', '192.168.10.226', '2013-11-14', '2013-11-13', '1', null);
INSERT INTO `ip_check` VALUES ('3', '192.168.10.222', '2013-11-15', '2013-11-13', '0', null);
