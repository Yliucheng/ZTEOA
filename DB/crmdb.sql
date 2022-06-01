/*
SQLyog  v12.2.6 (64 bit)
MySQL - 8.0.28 : Database - object
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`object` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `object`;

/*Table structure for table `marketclass` */

DROP TABLE IF EXISTS `marketclass`;

CREATE TABLE `marketclass` (
  `mkClassId` int NOT NULL AUTO_INCREMENT,
  `className` varchar(50) NOT NULL,
  `classType` varchar(20) NOT NULL,
  PRIMARY KEY (`mkClassId`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `marketclass` */

insert  into `marketclass`(`mkClassId`,`className`,`classType`) values 
(30,'2021年4月份web训练营','训练营'),
(31,'南昌工学院A1771班实训','高校'),
(32,'2021年4月份java训练营','训练营'),
(33,'南昌工学院1804班实训','高校'),
(35,'2021年4月份ui训练营','训练营'),
(36,'南昌大学1804班实训','高校'),
(37,'南昌大学1904班实训','高校');

/*Table structure for table `marketstudent` */

DROP TABLE IF EXISTS `marketstudent`;

CREATE TABLE `marketstudent` (
  `id` int NOT NULL AUTO_INCREMENT,
  `studentName` varchar(10) NOT NULL,
  `mkClassId` int NOT NULL,
  `fromschool` varchar(20) NOT NULL,
  `education` varchar(10) NOT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `qq` varchar(50) DEFAULT NULL,
  `xinge` varchar(30) NOT NULL,
  `beizhu` varchar(100) DEFAULT NULL,
  `willtrain` varchar(50) NOT NULL,
  `createTime` date DEFAULT NULL,
  `createauthorID` varchar(20) DEFAULT NULL,
  `modifyauthor` varchar(20) DEFAULT NULL,
  `modifyauthorTime` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `marketstudent` */

insert  into `marketstudent`(`id`,`studentName`,`mkClassId`,`fromschool`,`education`,`phone`,`qq`,`xinge`,`beizhu`,`willtrain`,`createTime`,`createauthorID`,`modifyauthor`,`modifyauthorTime`) values 
(1,'李四清',36,'南昌大学','本科','12345','5454','主动性人格','该生技术很好，希望先去面试看看再来培训','确定培训','2022-05-18','admin','李四喜','2022-05-26'),
(2,'张三',31,'南昌工学院','本科','123','23456','冷淡性','该生技术很好，希望先去面试看看再来培训','不确定','2022-05-18','admin','张三','2022-05-18'),
(3,'张飞',30,'南昌职业学院','本科','123','23456','被动型人格','该生技术很好，希望先去面试看看再来培训','确定培训','2022-05-18','admin','张飞','2022-05-18'),
(4,'翔飞',36,'南昌大学','本科','12345','5454','冷淡性','该生技术很好，希望先去面试看看再来培训','确定培训','2022-05-18','admin','翔飞','2022-05-18'),
(5,'李八',32,'南昌工学院','本科','123','23456','主动性人格','该生技术很好，希望先去面试看看再来培训','不确定','2022-05-18','admin','李八','2022-05-18'),
(6,'张天',32,'南昌职业学院','本科','123','23456','被动型人格','该生技术很好，希望先去面试看看再来培训','确定培训','2022-05-18','admin',NULL,'2022-05-26'),
(7,'小天',32,'江西软件大学','专科','123','23456','主动性人格','该生技术很好，希望先去面试看看再来培训','不确定','2022-05-18','admin','小天','2022-05-18'),
(8,'李龙',35,'江西软件大学','专科','123','23456','被动型人格','该生技术很好，希望先去面试看看再来培训','确定不培训','2022-05-18','admin','李龙','2022-05-18'),
(9,'刘晨鑫',30,'清华大学','专科','18888888888','1555588888','主动性人格','帅','确定培训','2022-05-26','admin',NULL,NULL);

/*Table structure for table `zteadmin` */

DROP TABLE IF EXISTS `zteadmin`;

CREATE TABLE `zteadmin` (
  `id` int NOT NULL,
  `logincode` varchar(10) NOT NULL,
  `roleId` int NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `createTime` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `zteadmin` */

insert  into `zteadmin`(`id`,`logincode`,`roleId`,`password`,`createTime`) values 
(1,'admin',0,'sa123','0000-00-00');

/*Table structure for table `ztecheckjob` */

DROP TABLE IF EXISTS `ztecheckjob`;

CREATE TABLE `ztecheckjob` (
  `id` int NOT NULL AUTO_INCREMENT,
  `studentNo` int NOT NULL,
  `ckTime` date NOT NULL,
  `ckstatu` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `ztecheckjob` */

insert  into `ztecheckjob`(`id`,`studentNo`,`ckTime`,`ckstatu`) values 
(1,1001,'2022-05-26','完成'),
(2,1002,'2022-05-26','完成'),
(3,1003,'2022-05-26','完成'),
(4,1004,'2022-05-26','完成'),
(5,1006,'2022-05-26','完成'),
(7,1017,'2022-05-26','未完成'),
(8,1022,'2022-05-26','完成'),
(9,1023,'2022-05-26','未完成'),
(11,1007,'2022-05-26','未完成'),
(12,1008,'2022-05-26','未完成'),
(13,1009,'2022-05-26','未完成'),
(14,1010,'2022-05-26','未完成'),
(15,1011,'2022-05-26','未完成'),
(16,1012,'2022-05-26','未全部完成'),
(17,1013,'2022-05-26','未全部完成'),
(18,1014,'2022-05-26','未全部完成'),
(19,1015,'2022-05-26','未全部完成'),
(20,1018,'2022-05-26','未全部完成'),
(21,1025,'2022-05-26','请假未完成'),
(22,1026,'2022-05-26','请假未完成'),
(23,1027,'2022-05-26','请假未完成'),
(24,1028,'2022-05-26','请假未完成'),
(25,1029,'2022-05-26','请假未完成');

/*Table structure for table `ztecheckwork` */

DROP TABLE IF EXISTS `ztecheckwork`;

CREATE TABLE `ztecheckwork` (
  `id` int NOT NULL AUTO_INCREMENT,
  `studentNo` int NOT NULL,
  `ckTime` date NOT NULL,
  `ckstatu` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `ztecheckwork` */

insert  into `ztecheckwork`(`id`,`studentNo`,`ckTime`,`ckstatu`) values 
(1,1003,'2022-05-25','迟到'),
(3,1009,'2022-05-26','旷课'),
(4,1013,'2022-05-26','旷课'),
(6,1003,'2022-05-26','早退'),
(7,1007,'2022-05-26','早退'),
(8,1025,'2022-05-26','早退'),
(9,1026,'2022-05-26','早退'),
(10,1031,'2022-05-26','早退'),
(11,1041,'2022-05-26','正常'),
(12,1036,'2022-05-26','迟到'),
(13,1057,'2022-05-26','迟到'),
(14,1066,'2022-05-26','迟到');

/*Table structure for table `zteclassinfo` */

DROP TABLE IF EXISTS `zteclassinfo`;

CREATE TABLE `zteclassinfo` (
  `classId` int NOT NULL AUTO_INCREMENT,
  `className` varchar(50) NOT NULL,
  `studyType` int NOT NULL,
  PRIMARY KEY (`classId`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `zteclassinfo` */

insert  into `zteclassinfo`(`classId`,`className`,`studyType`) values 
(11,'web&%$^^班',2),
(13,'java5班',1),
(22,'web1班',2),
(24,'web2班',2),
(33,'ui2班',3),
(39,'ui3班',3),
(42,'java7班',1);

/*Table structure for table `zteexam` */

DROP TABLE IF EXISTS `zteexam`;

CREATE TABLE `zteexam` (
  `examid` int NOT NULL AUTO_INCREMENT,
  `examname` varchar(30) NOT NULL,
  PRIMARY KEY (`examid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `zteexam` */

insert  into `zteexam`(`examid`,`examname`) values 
(1,'晨考'),
(2,'周考');

/*Table structure for table `ztemajor` */

DROP TABLE IF EXISTS `ztemajor`;

CREATE TABLE `ztemajor` (
  `id` int NOT NULL,
  `major` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `ztemajor` */

insert  into `ztemajor`(`id`,`major`) values 
(1,'java'),
(2,'web'),
(3,'ui');

/*Table structure for table `zteresult` */

DROP TABLE IF EXISTS `zteresult`;

CREATE TABLE `zteresult` (
  `id` int NOT NULL AUTO_INCREMENT,
  `studentid` int NOT NULL,
  `examdate` date NOT NULL,
  `examType` int NOT NULL,
  `studentresult` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `examtype` (`examType`),
  CONSTRAINT `examtype` FOREIGN KEY (`examType`) REFERENCES `zteexam` (`examid`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `zteresult` */

insert  into `zteresult`(`id`,`studentid`,`examdate`,`examType`,`studentresult`) values 
(1,1003,'2022-05-25',2,'中等'),
(3,1009,'2022-05-25',2,'中等'),
(4,1013,'2022-05-25',2,'中等'),
(5,1015,'2022-05-25',2,'中等'),
(7,1013,'2022-05-25',1,'非常好'),
(9,1017,'2022-05-26',1,'非常好'),
(10,1022,'2022-05-26',1,'非常好'),
(11,1023,'2022-05-26',1,'非常好'),
(12,1024,'2022-05-26',1,'非常好'),
(13,1030,'2022-05-26',1,'好'),
(14,1016,'2022-05-26',2,'好'),
(15,1017,'2022-05-26',2,'好'),
(16,1022,'2022-05-26',2,'好'),
(17,1023,'2022-05-26',2,'好'),
(18,1024,'2022-05-26',2,'好'),
(19,1002,'2022-05-26',2,'差'),
(20,1008,'2022-05-26',2,'好'),
(21,1014,'2022-05-26',2,'好'),
(22,1027,'2022-05-26',2,'好'),
(23,1028,'2022-05-26',2,'好'),
(24,1016,'2022-05-27',2,'好'),
(25,1017,'2022-05-27',2,'好'),
(26,1022,'2022-05-27',2,'好'),
(27,1023,'2022-05-27',2,'好'),
(28,1024,'2022-05-27',2,'好');

/*Table structure for table `ztestudent` */

DROP TABLE IF EXISTS `ztestudent`;

CREATE TABLE `ztestudent` (
  `studentid` int NOT NULL AUTO_INCREMENT,
  `studentName` varchar(15) NOT NULL,
  `classId` int NOT NULL,
  `fromSchool` varchar(50) NOT NULL,
  `education` varchar(10) DEFAULT NULL,
  `loginCode` varchar(20) NOT NULL,
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`studentid`)
) ENGINE=InnoDB AUTO_INCREMENT=1072 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `ztestudent` */

insert  into `ztestudent`(`studentid`,`studentName`,`classId`,`fromSchool`,`education`,`loginCode`,`password`) values 
(1001,'大可',24,'南昌工学院','专科','dake','sa123'),
(1002,'菁林园',22,'江西理工','专科','jinliyuan','sa123'),
(1003,'古进',11,'江西先锋学院','专科','gujin','sa123'),
(1004,'张飞',24,'南昌工学院','专科','zhangfie','sa123'),
(1006,'关羽',24,'南昌工学院','专科','guanyu','sa123'),
(1007,'刘亦菲',11,'清华大学','本科','liuyifei','sa123'),
(1008,'刘诗诗',22,'北京大学','本科','liushishi','sa123'),
(1009,'彭于晏',11,'南昌大学','本科','pengyuyan','sa123'),
(1010,'胡歌',24,'南京大学','本科','huge','sa123'),
(1011,'刘德华',33,'浙江大学','本科','liudehua','sa123'),
(1012,'周星驰',24,'南昌职业大学','本科','zhouxingchi','sa123'),
(1013,'李知恩',11,'清华大学','专科','liuyifei','sa123'),
(1014,'后羿',22,'北京大学','本科','houyi','sa123'),
(1015,'女娲',11,'南昌大学','本科','nvwa','sa123'),
(1016,'许洪煜',13,'南京大学','专科','xuhongyu','sa123'),
(1017,'夏洪彪',13,'浙江大学','专科','xiahonbiao','sa123'),
(1018,'高凯乐',24,'南昌职业大学','专科','gaokaile','sa123'),
(1022,'南浔易',13,'南昌大学','本科','nanxunyi','sa123'),
(1023,'腾血冥',13,'福州大学','本科','gaxxx','sa123'),
(1024,'月倾城',13,'南树大学','专科','gaoile','sa123'),
(1025,'君墨寒',11,'深圳大学','专科','gaoile','sa123'),
(1026,'林动',11,'沈阳药科大学','专科','gaoile','sa123'),
(1027,'林青璇',22,'江苏大学','本科','gaoile','sa123'),
(1028,'卫鸢尾',22,'西南政法大学','本科','gaxxx','sa123'),
(1029,'慕瑾',22,'陕西师范大学','专科','gaoile','sa123'),
(1030,'凤玥离',13,'南京农业大学','专科','gaoile','sa123'),
(1031,'黎墨影',11,'暨南大学','专科','gaoile','sa123'),
(1032,'雪儿',33,'北京化工大学','本科','gaoile','sa123'),
(1033,'李扬',33,'北京中医药大学','本科','gaxxx','sa123'),
(1034,'姚跃',33,'重庆大学','专科','gaoile','sa123'),
(1035,'龙月儿',22,'西安电子科技大学','专科','gaoile','sa123'),
(1036,'紫馨蓝',11,'湖南大学','专科','gaoile','sa123'),
(1037,'叶枫',24,'东北大学','本科','gaoile','sa123'),
(1038,'白玉',24,'中国海洋大学','本科','gaxxx','sa123'),
(1039,'项莘栯',24,'北京交通大学','专科','gaoile','sa123'),
(1040,'隐邪',33,'重庆大学','专科','gaoile','sa123'),
(1041,'王凯',11,'上海交通大学','本科','wangkai','sa123'),
(1056,'李四喜',22,'南昌大学','专科','lisixi','sa123'),
(1057,'焦武清',11,'南昌大学','本科','jiaowuqing','sa123'),
(1063,'张三思',22,'南昌大学','专科','zhangsansi','sdsdsdsd'),
(1064,'张三思',22,'南昌大学','专科','zhangsansi','f647e02a69ab0e51780373f86f89a12a'),
(1065,'张三思',24,'南昌大学','本科','zhangsansi','saasa'),
(1066,'焦武清',11,'南昌大学','本科','jiaowuqing','sa123'),
(1068,'张三思',13,'南昌大学','专科','zhangsansi','sa123'),
(1069,'大可乐',13,'清华大学','专科','dakele','sa123'),
(1070,'张三思',13,'南昌大学','本科','zhangsansi','sdsddd'),
(1071,'',11,'南昌大学','本科','nanxunyi','saerre');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
