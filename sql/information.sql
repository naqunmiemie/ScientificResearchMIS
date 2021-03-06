CREATE DATABASE scientific_research_mis;
USE scientific_research_mis;

DROP TABLE IF EXISTS `information`;

CREATE TABLE `information` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `university` varchar(256) NOT NULL,
  `name` varchar(256) NOT NULL,
  `phone_number` varchar(11) DEFAULT NULL,
  `title` varchar(256) DEFAULT NULL,
  `mailbox` varchar(256) DEFAULT NULL,
  `college` varchar(256) DEFAULT NULL,
  `lab` varchar(256) DEFAULT NULL,
  `resume` varchar(4096) DEFAULT NULL,
  `education_experience` varchar(4096) DEFAULT NULL,
  `work_experience` varchar(4096) DEFAULT NULL,
  `research_direction` varchar(1024) DEFAULT NULL,
  `courses` varchar(1024) DEFAULT NULL,
  `scientific_research_item` varchar(1024) DEFAULT NULL,
  `monograph` varchar(1024) DEFAULT NULL,
  `patent` varchar(1024) DEFAULT NULL,
  `awards` varchar(1024) DEFAULT NULL,
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `state` int(4) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `information`
	(`university`,`name`,`phone_number`,`title`,`mailbox`,`college`,`lab`,`resume`,
`education_experience`,`work_experience`,`research_direction`,`courses`,`scientific_research_item`,
`monograph`,`patent`,`awards`)
VALUES
('中国地质大学（武汉）','杨佳楠','13971202008','学生','944081949@qq.com','机械与电子信息学院','504','本科能不能毕业都不知道',
'不太行','还不知道能不能去','java后台开发','资历不够不能开课','没有经费研究不了',
NULL,'还真可能有一个','挺多的');

