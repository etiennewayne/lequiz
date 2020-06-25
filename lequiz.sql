/*
SQLyog Ultimate v12.14 (64 bit)
MySQL - 10.1.37-MariaDB : Database - lequiz
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
  `active` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`academic_year_id`),
  KEY `semesterID` (`semester_id`),
  CONSTRAINT `academicyears_ibfk_1` FOREIGN KEY (`semester_id`) REFERENCES `semesters` (`semester_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `academicyears` */

insert  into `academicyears`(`academic_year_id`,`ay_code`,`ay`,`semester_id`,`created_at`,`updated_at`,`active`) values 
(1,'191','2019-2020',1,'2019-10-11 05:01:26','2019-10-11 02:55:18',0),
(2,'192','2019-2020',2,'2019-10-11 05:03:01','2019-10-11 02:55:28',0),
(4,'181','2018-2019',1,'2019-10-11 03:03:46','2019-10-11 03:03:46',1),
(5,'182','2018-2019',2,'2019-10-11 03:04:04','2019-10-11 03:04:04',0),
(6,'173','2017-2018',3,'2019-10-11 03:04:04','2020-05-10 14:33:59',0),
(7,NULL,'test',NULL,'2020-05-10 14:37:25','2020-05-10 14:37:25',0),
(8,NULL,'test',NULL,'2020-05-10 14:38:05','2020-05-10 14:38:05',0);

/*Table structure for table `categories` */

DROP TABLE IF EXISTS `categories`;

CREATE TABLE `categories` (
  `category_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `category` varchar(100) DEFAULT NULL,
  `category_desc` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

/*Data for the table `categories` */

insert  into `categories`(`category_id`,`user_id`,`category`,`category_desc`,`created_at`,`updated_at`) values 
(9,NULL,'oop','object oriented','2020-06-06 13:39:39','2020-06-06 13:39:39'),
(10,NULL,'Programming 1','Programming 1 - C++','2020-06-06 13:47:13','2020-06-06 13:47:13'),
(11,15,'test','test','2020-06-24 12:02:18','2020-06-24 12:02:18'),
(13,17,'test','test','2020-06-24 12:11:20','2020-06-24 12:11:20'),
(22,17,'test1','test1','2020-06-24 13:06:49','2020-06-24 13:06:49'),
(23,19,'F1 CATEGORY 1','F1 CATEGORY 1','2020-06-24 21:21:43','2020-06-24 13:21:43'),
(25,19,'F1 CATEGORY 2','F1 CATEGORY 2','2020-06-24 21:21:46','2020-06-24 13:21:46');

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
  `failed_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
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
  `set_time` int(11) DEFAULT '10',
  `equiv_score` int(11) DEFAULT '1',
  PRIMARY KEY (`question_id`),
  KEY `quiz_id` (`quiz_id`),
  CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`quiz_id`) REFERENCES `quizzes` (`quiz_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

/*Data for the table `questions` */

insert  into `questions`(`question_id`,`question`,`opt_a`,`opt_b`,`opt_c`,`opt_d`,`ans`,`quiz_id`,`set_time`,`equiv_score`) values 
(8,'Which is private member functions access scope?','Member functions which can only be used within the class','Member functions which can used outside the class','Member functions which are accessible in derived class','Member functions which can’t be accessed inside the class','A',23,10,1),
(9,'Which of the following is the correct syntax of including a user defined header files in C++?','#include <userdefined.h>','#include <userdefined>','#include “userdefined”','#include [userdefined]','C',24,10,1),
(10,'Which of the following is a correct identifier in C++?','7var_name','7VARNAME','VAR_1234','var_name','C',24,10,1),
(11,'Which of the following is called address operator?','*','_','%','x','C',24,10,1),
(12,'How many types of constructors are available, in general, in any language?','1','2','3','4','C',23,10,1),
(13,'Which constructor is called while assigning some object with another?','Default','Parameterized','Copy','Direct assignment is used','C',23,10,1),
(14,'It’s necessary to pass object by reference in copy constructor because','Constructor is not called in pass by reference','Constructor is called in pass by reference only','It passes the address of new constructor to be created','It passes the address of new object to be created','A',23,10,1),
(15,'Which specifier applies only to the constructors?','Public','Protected','Implicit','Explicit','D',23,10,1),
(16,'Which among the following is true?','Default constructor can’t be defined by the programmer','Default parameters constructor isn’t equivalent to the default constructor','Default constructor can be called explicitly','Default constructor is and always called implicitly only','C',23,10,1),
(17,'Which type of constructor can’t have a return type?','Default','Parameterized','Copy','Constructors don’t have a return type','D',23,10,1),
(18,'When and how many times a static constructor is called?','Created at time of object destruction','Called at first time when an object is created and only one time','Called at first time when an object is created and called with every new object creation','Called whenever an object go out of scope','B',23,10,1),
(19,'Within a class, only one static constructor can be created.','True','False','Can create many','No answer','A',23,10,1),
(20,'When is the static constructor called?','After the first instance is created','Before default constructor call of first instance','Before first instance is created','At time of creation of first instance','C',23,10,1),
(21,'By default the members of the structure are','Private','Protected','Public','Access specifiers not applicable for structures.','C',24,10,1),
(22,'#include<iostream>\r\n\r\nusing namespace std;\r\nmain() { \r\n   int r, x = 2;\r\n   \r\n   float y = 5;\r\n  \r\n   r = y%x;\r\n   cout<<r; \r\n}','1','0','3','Compile Error','D',24,10,1),
(23,'A single line comment in C++ language source code can begin with',';','.','\\\\','//','D',24,10,1),
(24,'Which of the following correctly declares an array?','int array[10];','int array;','array{10};','array array[10];','A',24,10,1),
(25,'What is the index number of the last element of an array with 9 elements?','9','8','1','0','D',24,10,1),
(26,'What is the correct definition of an array?','An array is a series of elements of the same type in contiguous memory locations','An array is a series of element','An array is a series of elements of the same type placed in non-contiguous memory locations','An array is an element of the different type','A',24,10,1),
(27,'Which of the following accesses the seventh element stored in an array?','array[6];','array[7];','array(7);','array;','A',24,10,1),
(28,'qustion 1','a','b','c','d','A',25,10,1),
(29,'question 2','a','b','c','d','B',25,10,1),
(30,'question 3','a','b','c','d','C',25,10,1);

/*Table structure for table `quizzes` */

DROP TABLE IF EXISTS `quizzes`;

CREATE TABLE `quizzes` (
  `quiz_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `quiz_title` varchar(50) DEFAULT '',
  `quiz_desc` varchar(100) DEFAULT '',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`quiz_id`),
  KEY `category_id` (`category_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `quizzes_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `quizzes_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

/*Data for the table `quizzes` */

insert  into `quizzes`(`quiz_id`,`user_id`,`category_id`,`quiz_title`,`quiz_desc`,`created_at`,`updated_at`) values 
(23,15,9,'First Quiz - Midterm','Midterm Quiz - Objects','2020-06-06 13:48:09','2020-06-06 13:48:09'),
(24,15,10,'1st quiz midterm','1st quiz midterm','2020-06-06 13:51:32','2020-06-06 13:51:32'),
(25,19,23,'F1 QUIZ TITLE 1','F1 QUIZ TITLE 1','2020-06-24 21:57:44','2020-06-24 13:57:44'),
(26,19,25,'F1 QUIZ TITLE 2','F1 QUIZ TITLE 2','2020-06-24 21:57:51','2020-06-24 13:57:51');

/*Table structure for table `room_students` */

DROP TABLE IF EXISTS `room_students`;

CREATE TABLE `room_students` (
  `room_student_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `room_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`room_student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `room_students` */

insert  into `room_students`(`room_student_id`,`room_id`,`user_id`,`created_at`,`updated_at`) values 
(1,7,18,'2020-06-07 07:27:51','2020-06-07 07:27:51'),
(2,7,18,'2020-06-07 07:28:32','2020-06-07 07:28:32');

/*Table structure for table `rooms` */

DROP TABLE IF EXISTS `rooms`;

CREATE TABLE `rooms` (
  `room_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `room` varchar(50) DEFAULT NULL,
  `room_desc` varchar(255) DEFAULT NULL,
  `access_code` varchar(10) DEFAULT '',
  `quiz_id` bigint(11) NOT NULL,
  `isStart` tinyint(4) DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`room_id`),
  KEY `quiz_id` (`quiz_id`),
  CONSTRAINT `rooms_ibfk_1` FOREIGN KEY (`quiz_id`) REFERENCES `quizzes` (`quiz_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `rooms` */

insert  into `rooms`(`room_id`,`room`,`room_desc`,`access_code`,`quiz_id`,`isStart`,`created_at`,`updated_at`) values 
(7,'001','test room','e412ef',23,1,'2020-06-06 15:29:04','2020-06-07 15:27:46'),
(8,'002','002','83d8e3',24,0,'2020-06-14 06:18:02','2020-06-14 14:23:43'),
(10,'F1 QUIZ 1 ROOM 1','F1 QUIZ 1 ROOM 1','1bbb2a',25,0,'2020-06-24 13:58:34','2020-06-24 13:58:34');

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

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lname` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fname` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `classification` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `email_verified_at` timestamp NULL DEFAULT NULL,
  `password` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `remember_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `apwd` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `users_email_unique` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `users` */

insert  into `users`(`user_id`,`username`,`lname`,`fname`,`mname`,`email`,`classification`,`email_verified_at`,`password`,`remember_token`,`created_at`,`updated_at`,`apwd`) values 
(15,'admin','Administrator','Administrator','Administrator','admin@yahoo.com','ADMINISTRATOR',NULL,'$2y$10$YjaJIEK15ouZFJCAOONZa.sRBl0jg6w5S9AyX68TInfNsuebCR/Tm',NULL,'2019-10-26 16:41:35','2019-10-26 16:41:35','admin'),
(17,'f','Amparado','Etienne',NULL,'et88@yahoo.com','FACULTY',NULL,'$2y$10$wWJB2EsF3o/463.JpOyKtuL0ReSob/Y8.M4iauDNGawgwJtnJyp8O',NULL,'2020-05-03 06:18:11','2020-06-24 20:04:51','a'),
(18,'s','amparado','etienne',NULL,'etiennewayne@gmail.com','STUDENT',NULL,'$2y$10$gh3ziHDEuIpcAoEttTMjxuY5r.taDww39vzy/zUcLICXhZ4GIQsae',NULL,'2020-05-30 15:55:34','2020-06-24 20:04:53','a'),
(19,'f1','f1','f1','f1','f1@gmail.com','FACULTY',NULL,'$2y$10$mhSA9WsuhuETFmxwCuCbpOrv4m9Z7tI6u7mc5I2e0.e4CryChPLha',NULL,'2020-06-24 13:07:12','2020-06-24 13:07:12','a'),
(20,'test','test','test','test','test@gmail.com','STUDENT',NULL,'$2y$10$kRO3S72i3ckxUGGhfSha.uer9/lXreubGITJnRfVpbLcUGRCGE9IK',NULL,'2020-06-24 14:03:39','2020-06-24 14:41:22','a');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
