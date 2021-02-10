/*
SQLyog Ultimate v12.14 (64 bit)
MySQL - 10.4.13-MariaDB : Database - lequiz
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`lequiz` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `lequiz`;

/*Table structure for table `academicyears` */

DROP TABLE IF EXISTS `academicyears`;

CREATE TABLE `academicyears` (
  `academic_year_id` int(11) NOT NULL AUTO_INCREMENT,
  `ay_code` varchar(6) DEFAULT '',
  `ay` varchar(20) DEFAULT '',
  `semester_id` int(11) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `active` tinyint(4) DEFAULT 0,
  PRIMARY KEY (`academic_year_id`),
  KEY `semesterID` (`semester_id`),
  CONSTRAINT `academicyears_ibfk_1` FOREIGN KEY (`semester_id`) REFERENCES `semesters` (`semester_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `academicyears` */

insert  into `academicyears`(`academic_year_id`,`ay_code`,`ay`,`semester_id`,`created_at`,`updated_at`,`active`) values 
(1,'191','2019-2020',1,'2019-10-11 05:01:26','2019-10-11 02:55:18',0),
(2,'192','2019-2020',2,'2019-10-11 05:03:01','2019-10-11 02:55:28',0),
(4,'181','2018-2019',1,'2019-10-11 03:03:46','2019-10-11 03:03:46',0),
(5,'182','2018-2019',2,'2019-10-11 03:04:04','2019-10-11 03:04:04',0),
(6,'173','2017-2018',3,'2019-10-11 03:04:04','2020-05-10 14:33:59',0),
(10,'201','2020-2021',1,'2021-01-23 21:54:46','2021-01-23 21:54:46',1);

/*Table structure for table `categories` */

DROP TABLE IF EXISTS `categories`;

CREATE TABLE `categories` (
  `category_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL,
  `academic_year_id` int(11) DEFAULT NULL,
  `category` varchar(100) DEFAULT NULL,
  `category_desc` varchar(255) DEFAULT NULL,
  `unit` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `updated_at` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  PRIMARY KEY (`category_id`),
  KEY `user_id` (`user_id`),
  KEY `academic_year_id` (`academic_year_id`),
  CONSTRAINT `categories_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `categories_ibfk_2` FOREIGN KEY (`academic_year_id`) REFERENCES `academicyears` (`academic_year_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=latin1;

/*Data for the table `categories` */

insert  into `categories`(`category_id`,`user_id`,`academic_year_id`,`category`,`category_desc`,`unit`,`created_at`,`updated_at`) values 
(52,22,10,'WEB APP','WEB APP',5,'2021-02-10 19:13:33','2021-02-10 11:13:33'),
(55,22,10,'SSXXX','SSASDAWDA',3,'2021-02-10 19:13:24','2021-02-10 11:13:24'),
(57,22,10,'TEST','TEST',3,'2021-02-10 11:12:53','2021-02-10 11:12:53');

/*Table structure for table `courses` */

DROP TABLE IF EXISTS `courses`;

CREATE TABLE `courses` (
  `course_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_code` varchar(30) DEFAULT '',
  `course_desc` varchar(100) DEFAULT '',
  `unit` tinyint(4) DEFAULT NULL,
  `is_lec` tinyint(1) DEFAULT NULL,
  `is_lab` tinyint(1) DEFAULT NULL,
  `careated_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `courses` */

/*Table structure for table `failed_jobs` */

DROP TABLE IF EXISTS `failed_jobs`;

CREATE TABLE `failed_jobs` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `connection` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `queue` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `payload` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `exception` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `failed_at` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `failed_jobs` */

/*Table structure for table `migrations` */

DROP TABLE IF EXISTS `migrations`;

CREATE TABLE `migrations` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `migration` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `migrations` */

insert  into `migrations`(`id`,`migration`,`batch`) values 
(1,'2014_10_12_000000_create_users_table',1),
(2,'2014_10_12_100000_create_password_resets_table',1),
(3,'2019_08_19_000000_create_failed_jobs_table',1);

/*Table structure for table `password_resets` */

DROP TABLE IF EXISTS `password_resets`;

CREATE TABLE `password_resets` (
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  KEY `password_resets_email_index` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `password_resets` */

/*Table structure for table `questions` */

DROP TABLE IF EXISTS `questions`;

CREATE TABLE `questions` (
  `question_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `question` varchar(255) DEFAULT '',
  `opt_a` varchar(255) DEFAULT '',
  `opt_b` varchar(255) DEFAULT '',
  `opt_c` varchar(255) DEFAULT '',
  `opt_d` varchar(255) DEFAULT '',
  `ans` varchar(255) DEFAULT '',
  `quiz_id` bigint(20) DEFAULT NULL,
  `set_time` int(11) DEFAULT 10,
  `equiv_score` int(11) DEFAULT 1,
  PRIMARY KEY (`question_id`),
  KEY `quiz_id` (`quiz_id`),
  CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`quiz_id`) REFERENCES `quizzes` (`quiz_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=latin1;

/*Data for the table `questions` */

insert  into `questions`(`question_id`,`question`,`opt_a`,`opt_b`,`opt_c`,`opt_d`,`ans`,`quiz_id`,`set_time`,`equiv_score`) values 
(91,'What is HTML','Hyper Markup Language','Programming Language','Assembly Language','Spoken Language','A',57,5,1),
(92,'What is PHP','Hypertext preprocessor','A javascript','Assembly Language','None of the above','A',57,5,1),
(93,'CSS stands for?','Cast style sheets','Copper Style Sheets','Cascading Style Sheets','None of the above','c',57,5,1),
(94,'what is bla','a','b','c','d','A',58,5,1),
(95,'what is b','a','b','xc','d','B',58,5,1);

/*Table structure for table `quizzes` */

DROP TABLE IF EXISTS `quizzes`;

CREATE TABLE `quizzes` (
  `quiz_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `schedule_code` varbinary(10) DEFAULT '',
  `user_id` bigint(20) unsigned NOT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `access_code` varchar(20) NOT NULL DEFAULT '',
  `quiz_title` varchar(50) DEFAULT '',
  `quiz_desc` varchar(100) DEFAULT '',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `updated_at` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  `ay_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`quiz_id`),
  KEY `category_id` (`category_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `quizzes_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `quizzes_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=latin1;

/*Data for the table `quizzes` */

insert  into `quizzes`(`quiz_id`,`schedule_code`,`user_id`,`category_id`,`access_code`,`quiz_title`,`quiz_desc`,`created_at`,`updated_at`,`ay_id`) values 
(57,'120111',22,52,'2a5986','1ST QUIZ','1ST QUIZ','2021-01-23 22:09:39','2021-01-23 22:09:39',NULL),
(58,'120112',22,52,'ae2ae5','2ND QUIZ','2ND QUIZ','2021-01-23 23:15:05','2021-01-23 23:15:05',NULL),
(59,'120111',22,55,'6215eb','TEST','TEST','2021-02-10 11:03:12','2021-02-10 11:03:12',NULL);

/*Table structure for table `room_students` */

DROP TABLE IF EXISTS `room_students`;

CREATE TABLE `room_students` (
  `room_student_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `room_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  PRIMARY KEY (`room_student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `room_students` */

/*Table structure for table `rooms` */

DROP TABLE IF EXISTS `rooms`;

CREATE TABLE `rooms` (
  `room_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `room` varchar(50) DEFAULT NULL,
  `room_desc` varchar(255) DEFAULT NULL,
  `access_code` varchar(10) DEFAULT '',
  `quiz_id` bigint(11) NOT NULL,
  `isStart` tinyint(4) DEFAULT 0,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  PRIMARY KEY (`room_id`),
  UNIQUE KEY `room` (`room`),
  KEY `quiz_id` (`quiz_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `rooms` */

/*Table structure for table `semesters` */

DROP TABLE IF EXISTS `semesters`;

CREATE TABLE `semesters` (
  `semester_id` int(11) NOT NULL AUTO_INCREMENT,
  `semester` varchar(20) DEFAULT '',
  PRIMARY KEY (`semester_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `semesters` */

insert  into `semesters`(`semester_id`,`semester`) values 
(1,'1ST SEMESTER'),
(2,'2ND SEMESTER'),
(3,'SUMMER');

/*Table structure for table `student_ans` */

DROP TABLE IF EXISTS `student_ans`;

CREATE TABLE `student_ans` (
  `student_ans_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_quiz_id` bigint(20) DEFAULT NULL,
  `question_id` bigint(20) DEFAULT NULL,
  `student_ans` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`student_ans_id`),
  KEY `student_quiz_id` (`student_quiz_id`),
  CONSTRAINT `student_ans_ibfk_1` FOREIGN KEY (`student_quiz_id`) REFERENCES `student_quizzes` (`student_quiz_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `student_ans` */

/*Table structure for table `student_quizzes` */

DROP TABLE IF EXISTS `student_quizzes`;

CREATE TABLE `student_quizzes` (
  `student_quiz_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL,
  `quiz_id` bigint(20) NOT NULL,
  `ay_code` varchar(20) DEFAULT '',
  `academic_year_id` int(20) DEFAULT NULL,
  `course` varchar(100) DEFAULT '',
  `total_score` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`student_quiz_id`),
  KEY `user_id` (`user_id`),
  KEY `room_id` (`quiz_id`),
  CONSTRAINT `student_quizzes_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_quizzes_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_quizzes_ibfk_4` FOREIGN KEY (`quiz_id`) REFERENCES `quizzes` (`quiz_id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1;

/*Data for the table `student_quizzes` */

insert  into `student_quizzes`(`student_quiz_id`,`user_id`,`quiz_id`,`ay_code`,`academic_year_id`,`course`,`total_score`,`created_at`) values 
(50,24,57,'201',NULL,'WEB APP',3,'2021-01-24 07:12:07'),
(51,26,57,'201',NULL,'WEB APP',1,'2021-01-24 07:14:04'),
(52,26,58,'201',NULL,'WEB APP',2,'2021-01-24 07:16:14');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `idno` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `username` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lname` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `fname` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `mname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `classification` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `email_verified_at` timestamp NULL DEFAULT NULL,
  `password` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `remember_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp(),
  `apwd` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `users_email_unique` (`email`),
  UNIQUE KEY `idno` (`idno`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `users` */

insert  into `users`(`user_id`,`idno`,`username`,`lname`,`fname`,`mname`,`email`,`classification`,`email_verified_at`,`password`,`remember_token`,`created_at`,`updated_at`,`apwd`) values 
(15,'812345','admin','Administrator','Administrator','Administrator','admin@yahoo.com','ADMINISTRATOR',NULL,'$2y$10$YjaJIEK15ouZFJCAOONZa.sRBl0jg6w5S9AyX68TInfNsuebCR/Tm',NULL,'2019-10-26 16:41:35','2021-02-10 18:06:14','admin'),
(17,'2','wayne','Amparado','Etienne',NULL,'et88@yahoo.com','FACULTY',NULL,'$2y$10$wWJB2EsF3o/463.JpOyKtuL0ReSob/Y8.M4iauDNGawgwJtnJyp8O',NULL,'2020-05-03 06:18:11','2020-12-20 04:47:07','a'),
(19,'3','f1','f1','f1','f1','f1@gmail.com','FACULTY',NULL,'$2y$10$mhSA9WsuhuETFmxwCuCbpOrv4m9Z7tI6u7mc5I2e0.e4CryChPLha',NULL,'2020-06-24 13:07:12','2020-12-20 04:47:09','a'),
(21,'21','jfloriza','Floriza','Jade Ann','E','jadeann@gmail.com','FACULTY',NULL,'$2y$10$XfB1O4WXX6htCF24n7.ifO0Cpi7dUAkQgZtpGYn1iM7gz7eBW0Spy',NULL,'2020-10-25 05:59:48','2020-12-20 04:47:13','1234'),
(22,'22','jrey','Santarita','Jun Rey','M','junrey12345@gmail.com','FACULTY',NULL,'$2y$10$Jm9JbUlhK6mh2oIRw6J4nO9GLWCn1XCUVBooey/GnAQmX6T/KS.w2',NULL,'2020-10-25 06:03:02','2020-12-20 04:47:14','1234'),
(23,'23','jalcala','Alcala','Jacqueline','','jalcala@gadtc.edu.ph','STUDENT',NULL,'$2y$10$z1.Gn2DaagGep.t1zcqghunh0/Fj7mQ3/jtFm4LkcrdH9nL1Gtbhy',NULL,'2020-10-25 06:26:03','2020-12-20 04:47:15','1234'),
(24,'2456','halgadipe','Algadipe','Hezzle','','halgadipe@gadtc.edu.ph','STUDENT',NULL,'$2y$10$ErDELbZlnlCC411mQp60w.xGfwkTIp0aTEaPEgwkCZCKAVgcEDNau',NULL,'2020-10-25 06:26:36','2021-02-10 18:19:49','1234'),
(25,'25','raljas','Aljas','Roselyn','','raljas@gadtc.edu.ph','STUDENT',NULL,'$2y$10$TQqhnVw5hD4qM.xBqUx5q.enqIM5hnAoqII/i/coylRlG8r5bvRsS',NULL,'2020-10-25 06:27:13','2020-12-20 04:47:18','1234'),
(26,'26','zauxtero','Auxtero','Zyra Jean','','zauxtero@gadtc.edu.ph','STUDENT',NULL,'$2y$10$S2DynBLaXd6ro6hgd4EYv.ZaJPrc/lEBtBS0davVsni.d2T/Xq9Ge',NULL,'2020-10-25 06:28:37','2020-12-20 04:47:19','1234'),
(27,'27','jbalayong','Balayong','Justine Red','','jbalayong@gadtc.edu.ph','STUDENT',NULL,'$2y$10$Efg4cNUyBxC0LwjQy0epI.nVQedDNihvLDCuaTpPKJWnPYwrD0AwW',NULL,'2020-10-25 06:29:29','2020-12-20 04:47:20','1234'),
(28,'28','scapundag','Capundag','Sweethy Mae','','scapundag@gadtc.edu.ph','STUDENT',NULL,'$2y$10$rZMEAAviL5J.8Z.q5F3GKOCJ.bANHmCD3v0hetwZDUPFy8Egl.QzK',NULL,'2020-10-25 06:30:35','2020-12-20 04:47:21','1234'),
(29,'29','mechavez','Echavez','Milacel','','mechavez@gadtc.edu.ph','STUDENT',NULL,'$2y$10$HMvSlIlGyexVrkzIOoU3f.DCqEHAL9ozEK.E4Y3pL6xUxOV3YOgzm',NULL,'2020-10-25 06:31:01','2020-12-20 04:47:23','1234'),
(30,'30','lenero','Enero','Lyka','','lenero@gadtc.edu.ph','STUDENT',NULL,'$2y$10$JiXda8Aqi/ysMmOIgaNdLOA/0DdsajoJjXzlrrHQ73XSgpG8OgP46',NULL,'2020-10-25 06:31:26','2020-12-20 04:47:27','1234'),
(31,'31','eesin','Esin','Enjil','','eesin@gadtc.edu.ph','STUDENT',NULL,'$2y$10$lS2Qfizv/rZwnFSDQmmYG.04M8Pn/cj82cQfM.3WKRAovnOhDd5ty',NULL,'2020-10-25 06:31:47','2020-12-20 04:47:25','1234'),
(32,'32','jofloriza','Floriza','Joshua','','jofloriza@gadtc.edu.ph','STUDENT',NULL,'$2y$10$EzPeRZ22hEo3er/KapI3UuM6rj/gpOYZuzDHc42M9xbPM8VEph306',NULL,'2020-10-25 06:32:26','2020-12-20 04:47:28','1234'),
(33,'33','janfloriza','Floriza','Jansen','','janfloriza@gadtc.edu.ph','STUDENT',NULL,'$2y$10$Q6cZK.IRO86v3hXSLd9lz.0.RcRm6IH/d/JiL1AOqV60S5zeho7v6',NULL,'2020-10-25 06:33:13','2020-12-20 04:47:30','1234'),
(34,'34','jhamis','Hamis','Joseph Riel','','jhamis@gadtc.edu.ph','STUDENT',NULL,'$2y$10$80DwnPvmoCJwyvcQDpCncONNWstsU7Ecc3xajPCC9pQeNZ/P7bNQe',NULL,'2020-10-25 06:33:42','2020-12-20 04:47:32','1234'),
(35,'35','alopez','Lopez','Angel','','alopez@gadtc.edu.ph','STUDENT',NULL,'$2y$10$5wBuMk31Hica5qCrT88CmulmutZ35h9pNnJ4k5DE0Nzvq0WaZM4yi',NULL,'2020-10-25 06:34:02','2020-12-20 04:47:36','1234'),
(36,'36','jmacahidhid','Macahidhid','Jessa','','jmacahidhid@gadtc.edu.ph','STUDENT',NULL,'$2y$10$0nB0K/JqPqJHD5wv8muGaumnVcbrqav8YZCOTTG1dL1GuTBAGyby6',NULL,'2020-10-25 06:40:16','2020-12-20 04:47:38','1234'),
(37,'37','rmaglangit','Maglangit','Reche','','rmaglangit@gadtc.edu.ph','STUDENT',NULL,'$2y$10$ZAqCcGHGSfxzIzP1TYtAteuY8EZHwCh17oVSSVBRKSl3VQVslASkW',NULL,'2020-10-25 06:40:37','2020-12-20 04:47:40','1234'),
(38,'38','jmellejor','Mellejor','Jerecho','','jmellejor@gadtc.edu.ph','STUDENT',NULL,'$2y$10$IhqCLPyA4Gq0s1sTwvwMQ.PM0mKfNIozrC2OMH1c7u62DtKsbhU8O',NULL,'2020-10-25 06:41:01','2020-12-20 04:47:42','1234'),
(39,'39','amentopa','Mentopa','Albert','','amentopa@gadtc.edu.ph','STUDENT',NULL,'$2y$10$C0PwW6yuXvb3A6mBrXTOfe2nrsQAzVQD/vxiDC93UfTAGqPOsFh8e',NULL,'2020-10-25 06:41:21','2020-12-20 04:47:44','1234'),
(40,'40','nobrial','Obrial','Nova Mae','','nobrial@gadtc.edu.ph','STUDENT',NULL,'$2y$10$hT7i4QJRlSDOqYBZdEX/lehxD64Cxo3Q/pqLEfBWWxQUpDdhkp4ia',NULL,'2020-10-25 06:41:48','2020-12-20 04:47:46','1234'),
(41,'41','jpagaran','Pagaran','Jeepril','','jpagaran@gadtc.edu.ph','STUDENT',NULL,'$2y$10$stMrh8bK6IeUC4fLjO5CzOC4L8WUFlbDsfNlqSP59qb3HRo5pLn0i',NULL,'2020-10-25 06:42:38','2020-12-20 04:47:48','1234'),
(42,'42','dsagun','Sagun','Dexter','','dsagun@gadtc.edu.ph','STUDENT',NULL,'$2y$10$2vwiyFITuvsSQomB8qXzXuEQSDzBtrDIHhMU/nm8hAWeavGEYY14q',NULL,'2020-10-25 06:43:41','2020-12-20 04:47:49','1234'),
(43,'43','bsollano','Sollano','Beverly','','bsollano@gadtc.edu.ph','STUDENT',NULL,'$2y$10$2rMQOdHQNhPgQqEWdtK.iOvWpim1QVwyoSyuH4/r6aJQtKqIxpHRK',NULL,'2020-10-25 06:44:05','2020-12-20 04:47:51','1234'),
(44,'44','vveloso','Veloso','Virgel','','vveloso@gadtc.edu.ph','STUDENT',NULL,'$2y$10$.rB1Spc.gh8ptyjpQtRnDeTNkgk6HA09EvII357gc0q4DQAuxW3I.',NULL,'2020-10-25 06:44:27','2020-12-20 04:47:52','1234'),
(45,'45','jvilla','Villa','John Paul','','jvilla@gadtc.edu.ph','STUDENT',NULL,'$2y$10$65/B2PTibU62vsz8jiQrpeKKbiUyBaP7K6wvvxtOEIAIsKgY16ndy',NULL,'2020-10-25 06:45:10','2020-12-20 04:48:39','1234'),
(47,'','ricky','Valdezz','Ricky','Dersd','ricky@gmail.com','STUDENT',NULL,'$2y$10$Uff1hS9AOrcf7TxYpuP3Su8kOuJAxWSNp3u6yMZa59UmtRJQuSvru',NULL,'2021-01-23 11:18:10','2021-01-23 11:18:10','1234');

/* Procedure structure for procedure `proc_room_by_ay` */

/*!50003 DROP PROCEDURE IF EXISTS  `proc_room_by_ay` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_room_by_ay`(vaycode varchar(10), vuser_id int)
BEGIN
    
    SELECT
	a.room_id, a.room, a.room_desc, a.access_code, a.quiz_id,
	b.user_id, e.lname, e.fname, e.mname, e.email,
	b.category_id, b.quiz_title, b.quiz_desc,
	c.category, c.academic_year_id, d.ay_code, d.ay, d.active
	FROM
	rooms a
	JOIN quizzes b ON a.quiz_id = b.quiz_id
	JOIN categories c ON b.category_id = c.category_id
	JOIN academicyears d ON c.academic_year_id = d.academic_year_id
	JOIN users e ON b.user_id = e.user_id
	where d.ay_code = vaycode and b.user_id = vuser_id;
    END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
