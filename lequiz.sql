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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `academicyears` */

insert  into `academicyears`(`academic_year_id`,`ay_code`,`ay`,`semester_id`,`created_at`,`updated_at`,`active`) values 
(1,'191','2019-2020',1,'2019-10-11 05:01:26','2019-10-11 02:55:18',0),
(2,'192','2019-2020',2,'2019-10-11 05:03:01','2019-10-11 02:55:28',1),
(4,'181','2018-2019',1,'2019-10-11 03:03:46','2019-10-11 03:03:46',0),
(5,'182','2018-2019',2,'2019-10-11 03:04:04','2019-10-11 03:04:04',0),
(6,'173','2017-2018',3,'2019-10-11 03:04:04','2020-05-10 14:33:59',0);

/*Table structure for table `categories` */

DROP TABLE IF EXISTS `categories`;

CREATE TABLE `categories` (
  `category_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL,
  `academic_year_id` int(11) DEFAULT NULL,
  `category` varchar(100) DEFAULT NULL,
  `category_desc` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `updated_at` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  PRIMARY KEY (`category_id`),
  KEY `user_id` (`user_id`),
  KEY `academic_year_id` (`academic_year_id`),
  CONSTRAINT `categories_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `categories_ibfk_2` FOREIGN KEY (`academic_year_id`) REFERENCES `academicyears` (`academic_year_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=latin1;

/*Data for the table `categories` */

insert  into `categories`(`category_id`,`user_id`,`academic_year_id`,`category`,`category_desc`,`created_at`,`updated_at`) values 
(9,15,2,'oop','object oriented','2020-10-21 10:54:46','2020-10-21 10:54:46'),
(10,15,2,'Programming 1','Programming 1 - C++','2020-10-21 10:54:48','2020-10-21 10:54:48'),
(11,15,2,'test','test','2020-10-21 10:54:48','2020-10-21 10:54:48'),
(23,19,2,'F1 CATEGORY 1','F1 CATEGORY 1','2020-10-21 10:54:50','2020-10-21 10:54:50'),
(25,19,2,'F1 CATEGORY 2','F1 CATEGORY 2','2020-10-21 10:54:51','2020-10-21 10:54:51'),
(26,17,2,'WEB APP','WEB APP','2020-10-21 10:54:51','2020-10-21 10:54:51'),
(44,17,2,'SAMPLE CATEGORY','HAHAHAHA','2020-10-23 18:31:38',NULL),
(45,17,2,'OOP','OOP','2020-10-24 18:10:43',NULL),
(46,22,2,'PROGRAMMING 1','QUIZ NI SA BOGO NGA STUDENT','2020-12-07 11:39:53','2020-12-07 11:39:53'),
(47,22,2,'TEST','TEST','2020-12-07 02:25:15','2020-12-07 02:25:15');

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
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=latin1;

/*Data for the table `questions` */

insert  into `questions`(`question_id`,`question`,`opt_a`,`opt_b`,`opt_c`,`opt_d`,`ans`,`quiz_id`,`set_time`,`equiv_score`) values 
(28,'qustion 1','a','b','c','d','A',25,10,1),
(29,'question 2','a','b','c','d','B',25,10,1),
(30,'question 3','a','b','c','d','C',25,10,1),
(31,'What is CSS?','copra style shit','cascading style shit','cascading style sheet','all of the above','c',27,10,1),
(32,'How to add external CSS','<style>boday{margin:0}</style>','<p style=\"margin:0px\">I am paragraph</p>','<body><div class=\"container\"></div></body>','<link rel=\"stylesheet\" href=\"mystyle.css\">','d',27,10,1),
(33,'Document extenson of a css','.css','.js','.php','.html','A',27,10,1),
(34,'I want to add bottom padding. What could be the best syntax I could use?','padding-top: 50px;','padding-right: 30px;','padding-bottom: 50px;','padding-left: 80px;','c',27,10,1),
(37,'How many types of constructors are available, in general, in any language?','2','3','4','5','B',38,8,1),
(39,'Which constructor is called while assigning some object with another?','Default','Parameterized','Copy','Direct assignment is used','C',38,8,1),
(40,'Which specifier applies only to the constructors?','Public','Protected','Implicit','Explicit','D',38,8,1),
(41,'Which type of constructor can’t have a return type?','Default','Parameterized','Copy','Constructors don’t have a return type','D',38,8,1),
(42,'Within a class, only one static constructor can be created.','True','False','trulse','none of the above','A',38,8,1),
(43,'How can you created rounded corners using CSS3?','border[round]: 30px;','corner-effect: round;','border-radius: 30px;','alpha-effect: round-corner;','c',27,10,1),
(44,'How do you add shadow to elements in CSS3?','box-shadow: 10px 10px 5px grey;','shadow-right: 10px shadow-bottom: 10px;','shadow-color: grey','alpha-effect[shadow]: 10px 10px 5px grey;','A',27,10,1),
(70,'What is PHP','Hypertext Preprocessor','JavaScript','HyperText Markup','None of the above','A',39,10,1),
(71,'What is the attack technique used to exploit web sites by altering backend database queries through inputting manipulated queries?','LDAP Injection','XML Injection','SQL Injection','OS Commanding','A',40,10,1),
(72,'What happens when an application takes user-inputted data and sends it to a web browser without proper validation and escaping?','Security Misconfiguration','Cross Site Scripting','Insecure Direct Object References','Broken Authentication and Session Management','B',40,10,1),
(73,'What flaw arises from session tokens having poor randomness across a range of values?','Insecure Direct Object References','Session Replay','Session Fixation','Session Hijacking','C',40,10,1);

/*Table structure for table `quizzes` */

DROP TABLE IF EXISTS `quizzes`;

CREATE TABLE `quizzes` (
  `quiz_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `access_code` varchar(20) NOT NULL DEFAULT '',
  `quiz_title` varchar(50) DEFAULT '',
  `quiz_desc` varchar(100) DEFAULT '',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `updated_at` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  PRIMARY KEY (`quiz_id`),
  KEY `category_id` (`category_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `quizzes_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `quizzes_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;

/*Data for the table `quizzes` */

insert  into `quizzes`(`quiz_id`,`user_id`,`category_id`,`access_code`,`quiz_title`,`quiz_desc`,`created_at`,`updated_at`) values 
(25,19,23,'','F1 QUIZ TITLE 1','F1 QUIZ TITLE 1','2020-06-24 21:57:44','2020-06-24 13:57:44'),
(27,17,26,'','CSS','CSS','2020-10-04 01:22:56','2020-10-04 01:22:56'),
(38,17,45,'','1ST QUIZ OOP','nnnn','2020-10-24 19:07:45','2020-10-24 11:07:45'),
(39,22,46,'','TURBO C','TURBO C','2020-10-25 12:06:41','2020-10-25 12:06:41'),
(40,22,46,'ec1d17','TEST2','TEST2','2020-12-15 22:31:47','2020-12-15 14:31:47');

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

insert  into `room_students`(`room_student_id`,`room_id`,`user_id`,`created_at`,`updated_at`) values 
(1,7,18,'2020-06-07 07:27:51','2020-06-07 07:27:51'),
(2,7,18,'2020-06-07 07:28:32','2020-06-07 07:28:32');

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

insert  into `rooms`(`room_id`,`room`,`room_desc`,`access_code`,`quiz_id`,`isStart`,`created_at`,`updated_at`) values 
(10,'F1 QUIZ 1 ROOM 1','F1 QUIZ 1 ROOM 1','1bbb2a',25,0,'2020-06-24 13:58:34','2020-06-24 13:58:34'),
(11,'QUIZ CSS 1','QUIZ CSS 1','2e117e',27,0,'2020-10-04 01:31:10','2020-10-04 01:31:10'),
(12,'ROOM 102','QUIZ BLOCK 1','c24866',38,0,'2020-10-24 11:08:36','2020-10-24 11:08:36'),
(13,'PROGRAMMING 1 QUIZ','PROGRAMMING 1 QUIZ','c1acc0',39,0,'2020-10-25 12:08:49','2020-10-25 12:08:49'),
(14,'TEST ROOM','TEST ROOM','643a5f',39,0,'2020-12-07 03:31:29','2020-12-07 03:31:29');

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
  `total_score` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`student_quiz_id`),
  KEY `user_id` (`user_id`),
  KEY `room_id` (`quiz_id`),
  CONSTRAINT `student_quizzes_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_quizzes_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_quizzes_ibfk_4` FOREIGN KEY (`quiz_id`) REFERENCES `quizzes` (`quiz_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;

/*Data for the table `student_quizzes` */

insert  into `student_quizzes`(`student_quiz_id`,`user_id`,`quiz_id`,`total_score`,`created_at`) values 
(41,24,40,2,'2020-12-17 09:51:19'),
(42,24,40,0,'2020-12-17 11:32:06');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `student_id` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT '',
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
  UNIQUE KEY `users_email_unique` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `users` */

insert  into `users`(`user_id`,`student_id`,`username`,`lname`,`fname`,`mname`,`email`,`classification`,`email_verified_at`,`password`,`remember_token`,`created_at`,`updated_at`,`apwd`) values 
(15,'','admin','Administrator','Administrator','Administrator','admin@yahoo.com','ADMINISTRATOR',NULL,'$2y$10$YjaJIEK15ouZFJCAOONZa.sRBl0jg6w5S9AyX68TInfNsuebCR/Tm',NULL,'2019-10-26 16:41:35','2019-10-26 16:41:35','admin'),
(17,'','wayne','Amparado','Etienne',NULL,'et88@yahoo.com','FACULTY',NULL,'$2y$10$wWJB2EsF3o/463.JpOyKtuL0ReSob/Y8.M4iauDNGawgwJtnJyp8O',NULL,'2020-05-03 06:18:11','2020-10-25 06:25:11','a'),
(19,'','f1','f1','f1','f1','f1@gmail.com','FACULTY',NULL,'$2y$10$mhSA9WsuhuETFmxwCuCbpOrv4m9Z7tI6u7mc5I2e0.e4CryChPLha',NULL,'2020-06-24 13:07:12','2020-06-24 13:07:12','a'),
(20,'','test','test','test','test','test@gmail.com','STUDENT',NULL,'$2y$10$kRO3S72i3ckxUGGhfSha.uer9/lXreubGITJnRfVpbLcUGRCGE9IK',NULL,'2020-06-24 14:03:39','2020-06-24 14:41:22','a'),
(21,'','jfloriza','Floriza','Jade Ann','E','jadeann@gmail.com','FACULTY',NULL,'$2y$10$XfB1O4WXX6htCF24n7.ifO0Cpi7dUAkQgZtpGYn1iM7gz7eBW0Spy',NULL,'2020-10-25 05:59:48','2020-10-25 06:04:27','1234'),
(22,'','jrey','Santarita','Jun Rey','M','junrey12345@gmail.com','FACULTY',NULL,'$2y$10$Jm9JbUlhK6mh2oIRw6J4nO9GLWCn1XCUVBooey/GnAQmX6T/KS.w2',NULL,'2020-10-25 06:03:02','2020-10-25 06:24:56','1234'),
(23,'','jalcala','Alcala','Jacqueline',NULL,'jalcala@gadtc.edu.ph','STUDENT',NULL,'$2y$10$z1.Gn2DaagGep.t1zcqghunh0/Fj7mQ3/jtFm4LkcrdH9nL1Gtbhy',NULL,'2020-10-25 06:26:03','2020-10-25 06:26:03','1234'),
(24,'','halgadipe','Algadipe','Hezzle',NULL,'halgadipe@gadtc.edu.ph','STUDENT',NULL,'$2y$10$ErDELbZlnlCC411mQp60w.xGfwkTIp0aTEaPEgwkCZCKAVgcEDNau',NULL,'2020-10-25 06:26:36','2020-10-25 06:26:36','1234'),
(25,'','raljas','Aljas','Roselyn',NULL,'raljas@gadtc.edu.ph','STUDENT',NULL,'$2y$10$TQqhnVw5hD4qM.xBqUx5q.enqIM5hnAoqII/i/coylRlG8r5bvRsS',NULL,'2020-10-25 06:27:13','2020-10-25 06:27:13','1234'),
(26,'','zauxtero','Auxtero','Zyra Jean',NULL,'zauxtero@gadtc.edu.ph','STUDENT',NULL,'$2y$10$S2DynBLaXd6ro6hgd4EYv.ZaJPrc/lEBtBS0davVsni.d2T/Xq9Ge',NULL,'2020-10-25 06:28:37','2020-10-25 06:28:37','1234'),
(27,'','jbalayong','Balayong','Justine Red',NULL,'jbalayong@gadtc.edu.ph','STUDENT',NULL,'$2y$10$Efg4cNUyBxC0LwjQy0epI.nVQedDNihvLDCuaTpPKJWnPYwrD0AwW',NULL,'2020-10-25 06:29:29','2020-10-25 06:29:29','1234'),
(28,'','scapundag','Capundag','Sweethy Mae',NULL,'scapundag@gadtc.edu.ph','STUDENT',NULL,'$2y$10$rZMEAAviL5J.8Z.q5F3GKOCJ.bANHmCD3v0hetwZDUPFy8Egl.QzK',NULL,'2020-10-25 06:30:35','2020-10-25 06:32:32','1234'),
(29,'','mechavez','Echavez','Milacel',NULL,'mechavez@gadtc.edu.ph','STUDENT',NULL,'$2y$10$HMvSlIlGyexVrkzIOoU3f.DCqEHAL9ozEK.E4Y3pL6xUxOV3YOgzm',NULL,'2020-10-25 06:31:01','2020-10-25 06:33:12','1234'),
(30,'','lenero','Enero','Lyka',NULL,'lenero@gadtc.edu.ph','STUDENT',NULL,'$2y$10$JiXda8Aqi/ysMmOIgaNdLOA/0DdsajoJjXzlrrHQ73XSgpG8OgP46',NULL,'2020-10-25 06:31:26','2020-10-25 06:33:41','1234'),
(31,'','eesin','Esin','Enjil',NULL,'eesin@gadtc.edu.ph','STUDENT',NULL,'$2y$10$lS2Qfizv/rZwnFSDQmmYG.04M8Pn/cj82cQfM.3WKRAovnOhDd5ty',NULL,'2020-10-25 06:31:47','2020-10-25 06:33:28','1234'),
(32,'','jofloriza','Floriza','Joshua',NULL,'jofloriza@gadtc.edu.ph','STUDENT',NULL,'$2y$10$EzPeRZ22hEo3er/KapI3UuM6rj/gpOYZuzDHc42M9xbPM8VEph306',NULL,'2020-10-25 06:32:26','2020-10-25 06:32:26','1234'),
(33,'','janfloriza','Floriza','Jansen',NULL,'janfloriza@gadtc.edu.ph','STUDENT',NULL,'$2y$10$Q6cZK.IRO86v3hXSLd9lz.0.RcRm6IH/d/JiL1AOqV60S5zeho7v6',NULL,'2020-10-25 06:33:13','2020-10-25 06:33:13','1234'),
(34,'','jhamis','Hamis','Joseph Riel',NULL,'jhamis@gadtc.edu.ph','STUDENT',NULL,'$2y$10$80DwnPvmoCJwyvcQDpCncONNWstsU7Ecc3xajPCC9pQeNZ/P7bNQe',NULL,'2020-10-25 06:33:42','2020-10-25 06:33:42','1234'),
(35,'','alopez','Lopez','Angel',NULL,'alopez@gadtc.edu.ph','STUDENT',NULL,'$2y$10$5wBuMk31Hica5qCrT88CmulmutZ35h9pNnJ4k5DE0Nzvq0WaZM4yi',NULL,'2020-10-25 06:34:02','2020-10-25 06:34:02','1234'),
(36,'','jmacahidhid','Macahidhid','Jessa',NULL,'jmacahidhid@gadtc.edu.ph','STUDENT',NULL,'$2y$10$0nB0K/JqPqJHD5wv8muGaumnVcbrqav8YZCOTTG1dL1GuTBAGyby6',NULL,'2020-10-25 06:40:16','2020-10-25 06:40:16','1234'),
(37,'','rmaglangit','Maglangit','Reche',NULL,'rmaglangit@gadtc.edu.ph','STUDENT',NULL,'$2y$10$ZAqCcGHGSfxzIzP1TYtAteuY8EZHwCh17oVSSVBRKSl3VQVslASkW',NULL,'2020-10-25 06:40:37','2020-10-25 06:45:35','1234'),
(38,'','jmellejor','Mellejor','Jerecho',NULL,'jmellejor@gadtc.edu.ph','STUDENT',NULL,'$2y$10$IhqCLPyA4Gq0s1sTwvwMQ.PM0mKfNIozrC2OMH1c7u62DtKsbhU8O',NULL,'2020-10-25 06:41:01','2020-10-25 06:41:01','1234'),
(39,'','amentopa','Mentopa','Albert',NULL,'amentopa@gadtc.edu.ph','STUDENT',NULL,'$2y$10$C0PwW6yuXvb3A6mBrXTOfe2nrsQAzVQD/vxiDC93UfTAGqPOsFh8e',NULL,'2020-10-25 06:41:21','2020-10-25 06:41:21','1234'),
(40,'','nobrial','Obrial','Nova Mae',NULL,'nobrial@gadtc.edu.ph','STUDENT',NULL,'$2y$10$hT7i4QJRlSDOqYBZdEX/lehxD64Cxo3Q/pqLEfBWWxQUpDdhkp4ia',NULL,'2020-10-25 06:41:48','2020-10-25 06:41:48','1234'),
(41,'','jpagaran','Pagaran','Jeepril',NULL,'jpagaran@gadtc.edu.ph','STUDENT',NULL,'$2y$10$stMrh8bK6IeUC4fLjO5CzOC4L8WUFlbDsfNlqSP59qb3HRo5pLn0i',NULL,'2020-10-25 06:42:38','2020-10-25 06:42:38','1234'),
(42,'','dsagun','Sagun','Dexter',NULL,'dsagun@gadtc.edu.ph','STUDENT',NULL,'$2y$10$2vwiyFITuvsSQomB8qXzXuEQSDzBtrDIHhMU/nm8hAWeavGEYY14q',NULL,'2020-10-25 06:43:41','2020-10-25 12:03:58','1234'),
(43,'','bsollano','Sollano','Beverly',NULL,'bsollano@gadtc.edu.ph','STUDENT',NULL,'$2y$10$2rMQOdHQNhPgQqEWdtK.iOvWpim1QVwyoSyuH4/r6aJQtKqIxpHRK',NULL,'2020-10-25 06:44:05','2020-10-25 06:44:05','1234'),
(44,'','vveloso','Veloso','Virgel',NULL,'vveloso@gadtc.edu.ph','STUDENT',NULL,'$2y$10$.rB1Spc.gh8ptyjpQtRnDeTNkgk6HA09EvII357gc0q4DQAuxW3I.',NULL,'2020-10-25 06:44:27','2020-10-25 06:44:27','1234'),
(45,'','jvilla','Villa','John Paul',NULL,'jvilla@gadtc.edu.ph','STUDENT',NULL,'$2y$10$65/B2PTibU62vsz8jiQrpeKKbiUyBaP7K6wvvxtOEIAIsKgY16ndy',NULL,'2020-10-25 06:45:10','2020-10-25 06:45:10','1234');

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
