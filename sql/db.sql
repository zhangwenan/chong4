DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `article_id` int(11) NOT NULL UNIQUE COMMENT '目标站点的文章id',
  `title` varchar (80) NOT NULL DEFAULT '' COMMENT '文章标题',
  ``
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储自己掌握的QQ账号';





## 关键词对应商品个数
## 插入数据的时间

ALTER TABLE keyword_itemid MODIFY COLUMN id int;
ALTER TABLE keyword_itemid ALTER id DROP DEFAULT;
ALTER TABLE keyword_itemid CHANGE id id INT(11) NOT NULL AUTO_INCREMENT;


### ALTER TABLE keyword_itemid ALTER id SET DEFAULT 1;

ALTER TABLE alimama_item MODIFY COLUMN id int;
ALTER TABLE alimama_item ALTER id DROP DEFAULT;
ALTER TABLE alimama_item CHANGE id id INT(11) NOT NULL AUTO_INCREMENT;


### alter table people change peopleid peopleid smallint auto_increment unique;


ALTER TABLE keyword ADD refer_item_count INT NOT NULL DEFAULT 0 COMMENT'关键词相关的商品数量';
ALTER TABLE keyword ADD index keyword_refer_item_count (refer_item_count);
ALTER TABLE keyword ADD refer_item INT NOT NULL DEFAULT 0 COMMENT'关键词相关的商品数量';



DROP TABLE IF EXISTS `keyword_itemid`;
CREATE TABLE `keyword_itemid` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `keyword` varchar(255) NOT NULL COMMENT '关键词',
  `itemid` bigint NOT NULL COMMENT '商品id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关键词与商品Id的对应关系';

CREATE INDEX keyword_itemid_keyword ON keyword_itemid (keyword);

ALTER TABLE keyword ADD has_items enum('yes', 'no') NOT NULL DEFAULT 'no' COMMENT'是否已经在阿里妈妈采集了相关商品';


DROP TABLE IF EXISTS `alimama_item`;
CREATE TABLE `alimama_item` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `item_id` bigint NOT NULL UNIQUE COMMENT '淘宝商品id',
  `item_title` varchar(255) NOT NULL COMMENT '淘宝商品标题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='淘宝商品';

CREATE INDEX alimama_item_itemid ON alimama_item (item_id);





## QQ账号的表结构
DROP TABLE IF EXISTS `qq`;
CREATE TABLE `qq` (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `qq` varchar(15) NOT NULL UNIQUE COMMENT 'QQ账号',
  `password` varchar(20) NOT NULL COMMENT 'QQ密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储自己掌握的QQ账号';





// 百度账号
DROP TABLE IF EXISTS `baidu_account`;
CREATE TABLE `baidu_account` (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `user_name` varchar(40) NOT NULL UNIQUE COMMENT '账号',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `status` enum('undisplay','display') DEFAULT 'display' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='百度账号';








ALTER TABLE site_config ADD site_desc varchar(300) NOT NULL DEFAULT '' COMMENT'???????';

ALTER TABLE site_config ADD last_updated_time datetime DEFAULT NULL COMMENT'???????';
UPDATE site_config SET last_updated_time=now();

ALTER TABLE site_config ADD last_updated_id int(4) NOT NULL DEFAULT 1 COMMENT'??θ????id';


INSERT INTO site_config (id,domain,site_name,list_title_template,list_desc_template) VALUE (1,'utao.me','??????','{{ id }}','{{ id }}');
