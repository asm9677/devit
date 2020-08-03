CREATE DATABASE IF NOT EXISTS devit;

USE devit;



# user 테이블 생성
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL,
  `active` varchar(1) DEFAULT 'Y',
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `deleted` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `nickname_UNIQUE` (`nickname`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

# temp data 삽입
INSERT INTO `user` (`nickname`, `email`, `password`) VALUES ('admin', 'admin@admin.com', '{bcrypt}$2a$10$pcUo/H7ABf42BYF9QTTf0uq.cIV0d8Hu4Bnhh0JxAksWlVdz42m4e');
INSERT INTO `user` (`nickname`, `email`, `password`) VALUES ('test', 'test@test.com', '{bcrypt}$2a$10$8mVudpzSB9T8lP/Up3zxDekuNI0zEjAt4FnQdV0wXvtHj5qwOR.lK');

# user_roles 테이블 생성
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `user_role` varchar(45) DEFAULT NULL,
  KEY `uid_idx` (`user_id`),
  CONSTRAINT `uid` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# temp data 삽입
INSERT INTO `user_role` (`user_id`, `user_role`) VALUES((SELECT `user_id` FROM `user` WHERE nickname='admin'), 'ROLE_ADMIN');
INSERT INTO `user_role` (`user_id`, `user_role`) VALUES((SELECT `user_id` FROM `user` WHERE nickname='test'), 'ROLE_USER');



# board 테이블 삽입
CREATE TABLE `board` (
  `board_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `title` varchar(300) NOT NULL,
  `content` text NOT NULL,
  `type` int(11) NOT NULL,
  `count` int(11) NOT NULL DEFAULT '0',
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `modified` datetime DEFAULT NULL,
  `flag` varchar(45) DEFAULT 'Y',
  PRIMARY KEY (`board_id`),
  KEY `uidd_idx` (`user_id`),
  CONSTRAINT `board_uid_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `board_like` (
  `board_like_id` int(11) NOT NULL,
  `board_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `like_created` datetime DEFAULT CURRENT_TIMESTAMP,
  `like_flag` varchar(1) DEFAULT 'Y',
  PRIMARY KEY (`board_like_id`),
  KEY `board_like_bid_fk_idx` (`board_id`),
  KEY `user_uid_fk_idx` (`user_id`),
  CONSTRAINT `board_like_bid_fk` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_uid_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `board_reply` (
  `board_reply_id` int(11) NOT NULL AUTO_INCREMENT,
  `board_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `reply_created` datetime DEFAULT CURRENT_TIMESTAMP,
  `reply_content` text,
  `parent_reply_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`board_reply_id`),
  KEY `board_reply_bid_fk_idx` (`board_id`),
  KEY `board_reply_uid_fk_idx` (`user_id`),
  CONSTRAINT `board_reply_bid_fk` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `board_reply_uid_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# lecture
CREATE TABLE `lecture_common` (
  `common_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`common_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

CREATE TABLE `lecture` (
  `lecture_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `common_id` int(11) NOT NULL,
  `title` varchar(300) DEFAULT NULL,
  `content` text,
  `thumbnail_url` varchar(45) DEFAULT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `type` int(11) DEFAULT NULL,
  `deleted_flag` varchar(1) DEFAULT 'N',
  `view_count` int(11) DEFAULT '0',
  PRIMARY KEY (`lecture_id`),
  UNIQUE KEY `cid_UNIQUE` (`common_id`),
  KEY `lecture_cid_fk_idx` (`common_id`),
  KEY `lecture_uid_fk_idx` (`user_id`),
  CONSTRAINT `lecture_cid_fk` FOREIGN KEY (`common_id`) REFERENCES `lecture_common` (`common_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `lecture_uid_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

CREATE TABLE `lecture_auth` (
  `auth_id` int(11) NOT NULL,
  `lecture_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `role_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`auth_id`),
  KEY `lecture_auth_uid_fk_idx` (`user_id`),
  KEY `lecture_auth_lid_fk` (`lecture_id`),
  CONSTRAINT `lecture_auth_lid_fk` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`lecture_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `lecture_auth_uid_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `lecture_main_index` (
  `main_id` int(11) NOT NULL AUTO_INCREMENT,
  `lecture_id` int(11) NOT NULL,
  `common_id` int(11) NOT NULL,
  `title` varchar(300) DEFAULT NULL,
  `order` int(11) DEFAULT '1',
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`main_id`),
  UNIQUE KEY `cid_UNIQUE` (`common_id`),
  KEY `lecture_main_index_lid_fk_idx` (`lecture_id`),
  KEY `lecture_main_index_cid_fk_idx` (`common_id`),
  CONSTRAINT `lecture_main_index_cid_fk` FOREIGN KEY (`common_id`) REFERENCES `lecture_common` (`common_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `lecture_main_index_lid_fk` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`lecture_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `lecture_sub_index` (
  `sub_id` int(11) NOT NULL AUTO_INCREMENT,
  `main_id` int(11) NOT NULL,
  `common_id` int(11) NOT NULL,
  `lecture_id` int(11) NOT NULL,
  `title` varchar(300) DEFAULT NULL,
  `order` int(11) DEFAULT '1',
  `thumbnail_url` varchar(300) DEFAULT NULL,
  `player_url` varchar(300) DEFAULT NULL,
  `content` text,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `view_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`sub_id`),
  UNIQUE KEY `cid_UNIQUE` (`common_id`),
  KEY `lecture_sub_index_cid_fk_idx` (`common_id`),
  KEY `lecture_sub_index_mid_fk_idx` (`main_id`),
  KEY `lecture_sub_index_lid_fk_idx` (`lecture_id`),
  CONSTRAINT `lecture_sub_index_cid_fk` FOREIGN KEY (`common_id`) REFERENCES `lecture_common` (`common_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `lecture_sub_index_lecture_id_fk` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`lecture_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `lecture_sub_index_main_id_fk` FOREIGN KEY (`main_id`) REFERENCES `lecture_main_index` (`main_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

CREATE TABLE `lecture_tag` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `common_id` int(11) NOT NULL,
  `tag_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`tag_id`),
  KEY `lecture_tag_cid_fk_idx` (`common_id`),
  CONSTRAINT `lecture_tag_cid_fk` FOREIGN KEY (`common_id`) REFERENCES `lecture_common` (`common_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

CREATE TABLE `lecture_like` (
  `like_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `lecture_id` int(11) NOT NULL,
  `main_id` int(11) DEFAULT NULL,
  `sub_id` int(11) DEFAULT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `like_flag` varchar(1) DEFAULT 'Y',
  `like_type` int(11) NOT NULL COMMENT '1 : lecture\n2 : main (not used)\n3 : sub',
  PRIMARY KEY (`like_id`),
  KEY `lecture_like_uid_fk_idx` (`user_id`),
  KEY `lecture_like_lecture_id_fk_idx` (`lecture_id`),
  CONSTRAINT `lecture_like_lecture_id_fk` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`lecture_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `lecture_like_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;