# MySQL-Front 5.1  (Build 4.13)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: localhost    Database: spam_tools
# ------------------------------------------------------
# Server version 5.6.12

#
# Source for table mail_content
#

DROP TABLE IF EXISTS `mail_content`;
CREATE TABLE `mail_content` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `title` tinytext COMMENT '邮件主题',
  `content` tinytext COMMENT '邮件内容',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `del` tinyint(4) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table mail_content
#

LOCK TABLES `mail_content` WRITE;
/*!40000 ALTER TABLE `mail_content` DISABLE KEYS */;
/*!40000 ALTER TABLE `mail_content` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table mail_send_from
#

DROP TABLE IF EXISTS `mail_send_from`;
CREATE TABLE `mail_send_from` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL COMMENT '邮箱地址',
  `user_name` varchar(255) DEFAULT NULL COMMENT '邮箱登录用户名',
  `pass` varchar(32) DEFAULT NULL COMMENT '邮箱登录密码',
  `host` varchar(50) DEFAULT NULL COMMENT 'smtp服务器地址',
  `success_count` int(10) DEFAULT NULL COMMENT '成功次数',
  `fail_count` int(10) DEFAULT NULL COMMENT '失败次数',
  `last_time` datetime DEFAULT NULL COMMENT '上次发送时间',
  `del` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

#
# Dumping data for table mail_send_from
#

LOCK TABLES `mail_send_from` WRITE;
/*!40000 ALTER TABLE `mail_send_from` DISABLE KEYS */;
INSERT INTO `mail_send_from` VALUES (1,'123@123.com','2','3','4',0,0,NULL,0);
INSERT INTO `mail_send_from` VALUES (2,'dsf','sdf','dsf','asdf',0,0,NULL,0);
INSERT INTO `mail_send_from` VALUES (3,'3','33','333','3333',0,0,NULL,0);
INSERT INTO `mail_send_from` VALUES (4,'4','44','444','4444',0,0,NULL,0);
INSERT INTO `mail_send_from` VALUES (5,'5','55','555','5555',0,0,NULL,0);
INSERT INTO `mail_send_from` VALUES (6,'6','66','666','6666',0,0,NULL,0);
INSERT INTO `mail_send_from` VALUES (7,'7','77','777','7777',0,0,NULL,0);
INSERT INTO `mail_send_from` VALUES (8,'8','88','888','8888',0,0,NULL,0);
INSERT INTO `mail_send_from` VALUES (9,'9','99','999','9999',0,0,NULL,0);
INSERT INTO `mail_send_from` VALUES (10,'10','1010','101010','101010',0,0,NULL,0);
INSERT INTO `mail_send_from` VALUES (11,'11','1111','111111','11111111',0,0,NULL,0);
INSERT INTO `mail_send_from` VALUES (12,'12','1212','121212','12121212',0,0,NULL,1);
INSERT INTO `mail_send_from` VALUES (13,'13','1313','131313','13131313',0,0,NULL,1);
INSERT INTO `mail_send_from` VALUES (14,'14','1414','141414','14141414',0,0,NULL,1);
INSERT INTO `mail_send_from` VALUES (15,'15','1515','151515','15151515',0,0,NULL,1);
INSERT INTO `mail_send_from` VALUES (16,'16','1616','161616','16161616',0,0,NULL,1);
INSERT INTO `mail_send_from` VALUES (17,'17','1616','161616','16161616',0,0,NULL,1);
INSERT INTO `mail_send_from` VALUES (18,'18','9999999','181818','18181818',0,0,NULL,1);
/*!40000 ALTER TABLE `mail_send_from` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table mail_send_history
#

DROP TABLE IF EXISTS `mail_send_history`;
CREATE TABLE `mail_send_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `send_from` varchar(255) DEFAULT NULL COMMENT '发送邮箱',
  `send_to` varchar(255) DEFAULT NULL COMMENT '接收邮箱',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `state` tinyint(1) DEFAULT NULL COMMENT '发送状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table mail_send_history
#

LOCK TABLES `mail_send_history` WRITE;
/*!40000 ALTER TABLE `mail_send_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `mail_send_history` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table mail_send_to
#

DROP TABLE IF EXISTS `mail_send_to`;
CREATE TABLE `mail_send_to` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL COMMENT '邮箱地址',
  `success_count` int(10) DEFAULT NULL COMMENT '成功次数',
  `fail_count` int(10) DEFAULT NULL COMMENT '失败次数',
  `last_time` datetime DEFAULT NULL COMMENT '上次接收时间',
  `del` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

#
# Dumping data for table mail_send_to
#

LOCK TABLES `mail_send_to` WRITE;
/*!40000 ALTER TABLE `mail_send_to` DISABLE KEYS */;
INSERT INTO `mail_send_to` VALUES (1,'fansen@aipa.me',0,0,NULL,0);
INSERT INTO `mail_send_to` VALUES (2,'php-js@foxmail.com',0,0,NULL,0);
INSERT INTO `mail_send_to` VALUES (4,'123@123.com',0,0,NULL,0);
INSERT INTO `mail_send_to` VALUES (5,'1212@123.com',0,0,NULL,0);
INSERT INTO `mail_send_to` VALUES (6,'1111',0,0,NULL,0);
INSERT INTO `mail_send_to` VALUES (7,'11',0,0,NULL,0);
INSERT INTO `mail_send_to` VALUES (8,'113',0,0,NULL,0);
INSERT INTO `mail_send_to` VALUES (9,'1212',0,0,NULL,0);
INSERT INTO `mail_send_to` VALUES (10,'11111111',0,0,NULL,0);
INSERT INTO `mail_send_to` VALUES (11,'2',0,0,NULL,0);
INSERT INTO `mail_send_to` VALUES (12,'uil',0,0,NULL,0);
INSERT INTO `mail_send_to` VALUES (13,'111',0,0,NULL,0);
/*!40000 ALTER TABLE `mail_send_to` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table qq
#

DROP TABLE IF EXISTS `qq`;
CREATE TABLE `qq` (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `qq` varchar(15) NOT NULL COMMENT 'QQ账号',
  `password` varchar(20) NOT NULL COMMENT 'QQ密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `qq` (`qq`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='存储自己掌握的QQ账号';

#
# Dumping data for table qq
#

LOCK TABLES `qq` WRITE;
/*!40000 ALTER TABLE `qq` DISABLE KEYS */;
INSERT INTO `qq` VALUES (1,'465454','544454');
INSERT INTO `qq` VALUES (2,'5441548','4894545');
INSERT INTO `qq` VALUES (3,'6541654654','65456456');
INSERT INTO `qq` VALUES (4,'564145445','65554');
INSERT INTO `qq` VALUES (5,'514561','15413211');
INSERT INTO `qq` VALUES (6,'456415125','5154154');
INSERT INTO `qq` VALUES (10,'216510','151551');
INSERT INTO `qq` VALUES (11,'1556151','5415155');
INSERT INTO `qq` VALUES (12,'5461515461','541541');
INSERT INTO `qq` VALUES (13,'654654','64544654');
INSERT INTO `qq` VALUES (14,'654564165','65415456');
INSERT INTO `qq` VALUES (15,'6544154165','5645641564165');
INSERT INTO `qq` VALUES (16,'54615641564','9548794984');
INSERT INTO `qq` VALUES (17,'54541654165','9848944');
INSERT INTO `qq` VALUES (18,'65165132132','987489494');
INSERT INTO `qq` VALUES (19,'6512321321','65121321');
INSERT INTO `qq` VALUES (20,'65416515','15415');
INSERT INTO `qq` VALUES (21,'21621','51456156');
INSERT INTO `qq` VALUES (22,'15461541','51541');
INSERT INTO `qq` VALUES (23,'5415151','41541');
INSERT INTO `qq` VALUES (24,'154151554','654151');
INSERT INTO `qq` VALUES (25,'54151541','54151');
INSERT INTO `qq` VALUES (26,'65165165165','23132013254');
INSERT INTO `qq` VALUES (27,'654987498451','879465165');
INSERT INTO `qq` VALUES (28,'45879489165','9874651321');
INSERT INTO `qq` VALUES (29,'451321098498','6541321');
INSERT INTO `qq` VALUES (30,'498746541','21651872123');
INSERT INTO `qq` VALUES (31,'5474156','874561321');
INSERT INTO `qq` VALUES (32,'9874651321','6501321');
INSERT INTO `qq` VALUES (33,'1651','4534524');
INSERT INTO `qq` VALUES (34,'16519541','984561');
INSERT INTO `qq` VALUES (35,'65465165','5612154');
INSERT INTO `qq` VALUES (36,'65118751','651');
INSERT INTO `qq` VALUES (37,'5561546417','5410210');
/*!40000 ALTER TABLE `qq` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
