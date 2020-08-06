CREATE DATABASE IF NOT EXISTS devit;

USE devit;



# user 테이블 생성
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '유저 사용자 고유 id',
  `nickname` varchar(45) DEFAULT NULL COMMENT '사용자 닉네임',
  `email` varchar(45) DEFAULT NULL COMMENT '사용자 이메일',
  `password` varchar(500) DEFAULT NULL COMMENT '사용자 비밀번호',
  `active` varchar(1) DEFAULT 'Y' COMMENT ' 탈퇴여부',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `deleted` datetime DEFAULT NULL COMMENT '삭제일',
  `email_confirm` varchar(1) NOT NULL DEFAULT 'N' COMMENT '이메일 인증 확인 여부',
  `code` varchar(45) DEFAULT NULL,
  `profile` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `nickname_UNIQUE` (`nickname`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

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
  `reply_modified` datetime DEFAULT CURRENT_TIMESTAMP,
  `delete_yn` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`board_reply_id`),
  KEY `board_reply_bid_fk_idx` (`board_id`),
  KEY `board_reply_uid_fk_idx` (`user_id`),
  CONSTRAINT `board_reply_bid_fk` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `board_reply_uid_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;



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
  `modified` datetime DEFAULT CURRENT_TIMESTAMP,
  `type` int(11) DEFAULT NULL,
  `deleted_flag` varchar(1) DEFAULT 'N',
  `view_count` int(11) DEFAULT '0',
  PRIMARY KEY (`lecture_id`),
  UNIQUE KEY `cid_UNIQUE` (`common_id`),
  KEY `lecture_cid_fk_idx` (`common_id`),
  KEY `lecture_uid_fk_idx` (`user_id`),
  CONSTRAINT `lecture_cid_fk` FOREIGN KEY (`common_id`) REFERENCES `lecture_common` (`common_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `lecture_uid_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;

CREATE TABLE `lecture_auth` (
  `auth_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '권한 id',
  `lecture_id` int(11) NOT NULL COMMENT '프로젝트 id',
  `user_id` int(11) NOT NULL COMMENT '사용자 id',
  `lecture_role` varchar(20) DEFAULT NULL COMMENT '권한 이름',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일자',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '수정 일자',
  `delete_yn` varchar(1) DEFAULT 'N' COMMENT '삭제 여부',
  PRIMARY KEY (`auth_id`),
  KEY `lecture_auth_uid_fk_idx` (`user_id`),
  KEY `lecture_auth_lid_fk` (`lecture_id`),
  CONSTRAINT `lecture_auth_lid_fk` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`lecture_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `lecture_auth_uid_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `lecture_sub_index` (
  `sub_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '소분류 테이블 자동 증가 id',
  `lecture_id` int(11) NOT NULL COMMENT '대표 프로젝트 id',
  `user_id` int(11) NOT NULL COMMENT '생성한 사람',
  `modifier` int(11) NOT NULL COMMENT '수정한 사람',
  `common_id` int(11) NOT NULL COMMENT '태그를 관리할 common id',
  `title` varchar(300) DEFAULT NULL COMMENT '소분류 제목',
  `order` int(11) DEFAULT '1' COMMENT '소분류 순서',
  `video_id` int(11) DEFAULT NULL COMMENT 'lecture_sub_history로 부터 반영되어 보여질 동영상을 포함하고 있는 lectrue_sub_history.sub_his_id',
  `wiki_id` int(11) DEFAULT NULL COMMENT 'lecture_sub_history로 부터 반영되어 보여질 wiki를 포함하고 있는 lectrue_sub_history.sub_his_id',
  `view_count` int(11) DEFAULT '0' COMMENT '조회수',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '수정일',
  PRIMARY KEY (`sub_id`),
  UNIQUE KEY `cid_UNIQUE` (`common_id`),
  KEY `lecture_sub_index_cid_fk_idx` (`common_id`),
  KEY `lecture_sub_index_lid_fk_idx` (`lecture_id`),
  KEY `lecture_sub_index_user_id_fk_idx` (`user_id`),
  KEY `lecture_sub_modifier_fk_idx` (`modifier`),
  CONSTRAINT `lecture_sub_index_cid_fk` FOREIGN KEY (`common_id`) REFERENCES `lecture_common` (`common_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `lecture_sub_index_lecture_id_fk` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`lecture_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `lecture_sub_index_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `lecture_sub_modifier_fk` FOREIGN KEY (`modifier`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `lecture_sub_history` (
  `sub_his_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '강의 참여 요청 이력 테이블 PK',
  `lecture_id` int(11) NOT NULL COMMENT '프로젝트 단위',
  `sub_id` int(11) NOT NULL COMMENT '강의(목차) 단위',
  `user_id` int(11) NOT NULL COMMENT '요청한 사람',
  `modifier` int(11) NOT NULL COMMENT '수정한 사람',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '수정일자',
  `player_url` varchar(300) DEFAULT NULL COMMENT '동영상 강의 URL',
  `wiki_content` text COMMENT '강의 WIKI 내용 TEXT만',
  `wiki_content_html` text COMMENT '강의 WIKI 내용 HTML',
  `view_count` int(11) NOT NULL DEFAULT '0' COMMENT '조회수 (요청타입이 동영상일 경우에만 count되는 컬럼)',
  `req_type` varchar(20) NOT NULL COMMENT '요청타입 (동영상인지 강의내용인지)\nex. video/wiki',
  `accept_yn` varchar(1) NOT NULL DEFAULT 'N' COMMENT '요청을 받아들일지 거절할지 여부',
  `delete_yn` varchar(1) NOT NULL DEFAULT 'N' COMMENT 'accept_yn 여부와 상관 없이 부적절한 동영상일 경우 목록에서 보이지 않도록...',
  PRIMARY KEY (`sub_his_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='강의 참여 요청 이력 테이블';

CREATE TABLE `lecture_sub_his_like` (
  `sub_his_like_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'lecture_sub_his_like의 PK',
  `lecture_id` int(11) NOT NULL,
  `sub_id` int(11) NOT NULL,
  `sub_his_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `like_flag` varchar(1) DEFAULT 'Y' COMMENT '좋아요 여부',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '수정일자',
  PRIMARY KEY (`sub_his_like_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='lecture_sub_history에 존재하는 동영상들에 대한 좋아요 관리 테이블';

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
  `like_flag` varchar(1) DEFAULT 'Y',
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `modified` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`like_id`),
  KEY `lecture_like_uid_fk_idx` (`user_id`),
  KEY `lecture_like_lecture_id_fk_idx` (`lecture_id`),
  CONSTRAINT `lecture_like_lecture_id_fk` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`lecture_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `lecture_like_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;