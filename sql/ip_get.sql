/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50532
Source Host           : localhost:3306
Source Database       : spam_tools

Target Server Type    : MYSQL
Target Server Version : 50532
File Encoding         : 65001

Date: 2013-11-13 16:28:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `ip_get`
-- ----------------------------
DROP TABLE IF EXISTS `ip_get`;
CREATE TABLE `ip_get` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `url` varchar(255) NOT NULL COMMENT '获取ip代理的网站地址',
  `detail_regular` varchar(255) NOT NULL COMMENT '该网站列表页正则表达式',
  `gmt_created` date NOT NULL COMMENT '添加时间',
  `del` int(11) NOT NULL COMMENT '保留字段,用于对网站的删除,“1”表示上次采集失败,做逻辑删除',
  `is_done` int(11) NOT NULL COMMENT '该网站是否最近有采集过ip,采集过设置为1,未采集为0',
  `ip_regular` varchar(255) NOT NULL COMMENT 'ip的正则表达式',
  PRIMARY KEY (`id`,`url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ip_get
-- ----------------------------
