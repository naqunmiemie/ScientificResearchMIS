CREATE DATABASE scientific_research_mis;
USE scientific_research_mis;

DROP TABLE IF EXISTS `ticket`;

CREATE TABLE `ticket` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `ticket` varchar(1024) DEFAULT NULL,
  `expired_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uid` (`user_id`),
  UNIQUE KEY `t` (`ticket`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

