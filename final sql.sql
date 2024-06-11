/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.9 : Database - herbal_medicine_predictor
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`herbal_medicine_predictor` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `herbal_medicine_predictor`;

/*Table structure for table `booking` */

DROP TABLE IF EXISTS `booking`;

CREATE TABLE `booking` (
  `booking_id` int(11) NOT NULL AUTO_INCREMENT,
  `doctor_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`booking_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `booking` */

insert  into `booking`(`booking_id`,`doctor_id`,`user_id`,`date`,`status`) values 
(1,1,1,'12/3/22','Accepted'),
(2,1,2,'2022-03-05','pending');

/*Table structure for table `chat` */

DROP TABLE IF EXISTS `chat`;

CREATE TABLE `chat` (
  `chat_id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` int(11) DEFAULT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  `message` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`chat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

/*Data for the table `chat` */

insert  into `chat`(`chat_id`,`sender_id`,`receiver_id`,`message`,`date`) values 
(1,8,2,'hhh','2022-03-06'),
(2,2,8,'hyyy','2022-03-06'),
(3,6,11,'dghhjjhh','2023-08-16'),
(4,6,11,'dghhjjhh','2023-08-16'),
(5,11,6,'hjjk','2023-08-16 12:09:50'),
(6,6,14,'asfgjkmbbnjmnhjkk','2023-08-16'),
(7,6,14,'vgghhjjjj','2023-08-16'),
(8,6,11,'dfgh','2023-08-16'),
(9,11,6,'hjkluiioo','2023-08-16 12:26:09'),
(10,6,15,'dfghjjj','2023-08-16'),
(11,15,6,'ok','2023-08-16 12:51:40'),
(12,19,9,'any problem ','2023-08-17'),
(13,19,9,'any problem ','2023-08-17'),
(14,19,9,'any problem ','2023-08-17'),
(15,19,9,'any problem ','2023-08-17'),
(16,11,6,'nothing','2023-08-17 12:09:53'),
(17,11,6,'ok','2023-08-17 12:10:54');

/*Table structure for table `cultitechnique` */

DROP TABLE IF EXISTS `cultitechnique`;

CREATE TABLE `cultitechnique` (
  `technique_id` int(11) NOT NULL AUTO_INCREMENT,
  `plant` varchar(100) DEFAULT NULL,
  `details` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`technique_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `cultitechnique` */

insert  into `cultitechnique`(`technique_id`,`plant`,`details`) values 
(1,'dfsd','sfsdf'),
(2,'hjjk','tyyu'),
(3,'hjjk','tyyu'),
(4,'ert','hjkkl');

/*Table structure for table `doctor` */

DROP TABLE IF EXISTS `doctor`;

CREATE TABLE `doctor` (
  `doctor_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `doctor` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`doctor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `doctor` */

insert  into `doctor`(`doctor_id`,`login_id`,`doctor`,`place`,`phone`,`email`) values 
(1,4,'rio','ernakulam','8784545454','rio@gmail.com');

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `feedback_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `feedback` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`feedback_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `feedback` */

insert  into `feedback`(`feedback_id`,`user_id`,`feedback`,`date`) values 
(1,1,'fdsdf','12/5/22'),
(2,2,'gg','2022-03-05'),
(3,2,'hy','2022-03-05'),
(4,1,'good date: 17/6/22','2023-07-29');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `usertype` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login_id`,`username`,`password`,`usertype`) values 
(1,'admin','admin','admin'),
(2,'anna','anna','Researcher'),
(4,'rio','rio','doctor'),
(6,'anu','anu','user'),
(7,'jinu','jinu','Sales Manager'),
(8,'jj','jj','user'),
(9,'kriscor','jerin','Researcher'),
(10,'arpitha','thomas','Sales Manager'),
(11,'adarsh','adarsh123','Researcher'),
(12,'rahul','rahul123','Sales Manager'),
(13,'','','Researcher'),
(14,'adarsh','adarsh123','Researcher'),
(15,'aparna','aparna123','Researcher'),
(16,'anju ','anju','user'),
(17,'aparna','aparna123','Researcher'),
(18,'rahul','rahul123','Sales Manager'),
(19,'rohit','rohit','user');

/*Table structure for table `materials` */

DROP TABLE IF EXISTS `materials`;

CREATE TABLE `materials` (
  `material_id` int(11) NOT NULL AUTO_INCREMENT,
  `researcher_id` int(11) DEFAULT NULL,
  `materials` varchar(100) DEFAULT NULL,
  `details` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`material_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `materials` */

insert  into `materials`(`material_id`,`researcher_id`,`materials`,`details`,`status`) values 
(1,1,'dfsdf','dfsdfdsf','Accepted'),
(2,2,'ghjjj','rttyy','Accepted'),
(3,3,'thulasi','reduce stress and blood pressure','Accepted'),
(4,3,'aloe vera','eye irritations and injuries','Rejected');

/*Table structure for table `medicalplant` */

DROP TABLE IF EXISTS `medicalplant`;

CREATE TABLE `medicalplant` (
  `plant_id` int(11) NOT NULL AUTO_INCREMENT,
  `researcher_id` int(11) DEFAULT NULL,
  `plant` varchar(100) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`plant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `medicalplant` */

insert  into `medicalplant`(`plant_id`,`researcher_id`,`plant`,`image`) values 
(2,1,'fedfwefds','static/daf41551-06cb-4443-bc7d-d740032e8e75Screenshot (6).png'),
(3,3,'turmeric','static/7ee1a99c-cd41-4700-ae8f-10917e85ae41turmeric.jpg'),
(4,3,'aloe vera','static/128d169b-bdc8-414e-ae90-2dad20cab73baloe vera.jpeg'),
(5,3,'','static/cec85781-b3a9-4c17-99fb-b701a4c932bd');

/*Table structure for table `medicine` */

DROP TABLE IF EXISTS `medicine`;

CREATE TABLE `medicine` (
  `medicine_id` int(11) NOT NULL AUTO_INCREMENT,
  `sales_id` int(11) DEFAULT NULL,
  `medicine_name` varchar(100) DEFAULT NULL,
  `quantity` varchar(100) DEFAULT NULL,
  `amount` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`medicine_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `medicine` */

insert  into `medicine`(`medicine_id`,`sales_id`,`medicine_name`,`quantity`,`amount`,`date`) values 
(1,1,'wewe','10','100','2022-02-11'),
(2,1,'ererere','5','50','2022-02-11'),
(3,3,'rttyy','3','100','2023-07-29'),
(5,3,'uiiiooojj','3','800','2023-08-16'),
(6,3,'thulasi','5','200','2023-08-16');

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `sales_id` int(11) DEFAULT NULL,
  `amount` varchar(50) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `order` */

insert  into `order`(`order_id`,`user_id`,`sales_id`,`amount`,`date`,`status`) values 
(3,2,1,'200','2022-03-06','Buy'),
(4,1,3,'100','2023-08-02','Buy'),
(5,1,3,'2500','2023-08-16','Buy'),
(6,1,3,'2600','2023-08-16','Buy'),
(7,3,3,'200','2023-08-16','Buy'),
(8,3,3,'200','2023-08-17','Buy');

/*Table structure for table `order_detail` */

DROP TABLE IF EXISTS `order_detail`;

CREATE TABLE `order_detail` (
  `orderdetail_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `medicine_id` int(11) DEFAULT NULL,
  `quantity` varchar(50) DEFAULT NULL,
  `amount` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`orderdetail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `order_detail` */

insert  into `order_detail`(`orderdetail_id`,`order_id`,`medicine_id`,`quantity`,`amount`) values 
(3,3,2,'2','100'),
(4,3,1,'1','100'),
(5,4,3,'1','100'),
(6,5,3,'1','100'),
(7,5,5,'3','2400'),
(8,6,5,'2','1600'),
(9,6,6,'5','1000'),
(10,7,6,'1','200'),
(11,8,6,'1','200');

/*Table structure for table `payment` */

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `payed_id` int(11) DEFAULT NULL,
  `payed_for` varchar(100) DEFAULT NULL,
  `amount` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `payment` */

insert  into `payment`(`payment_id`,`payed_id`,`payed_for`,`amount`,`date`) values 
(1,1,'medicine','100','12/2/22'),
(2,1,'plants','200','22/2/22');

/*Table structure for table `plant_for_sale` */

DROP TABLE IF EXISTS `plant_for_sale`;

CREATE TABLE `plant_for_sale` (
  `plantsale_id` int(11) NOT NULL AUTO_INCREMENT,
  `plant` varchar(100) DEFAULT NULL,
  `quantity` varchar(100) DEFAULT NULL,
  `amount` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`plantsale_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `plant_for_sale` */

insert  into `plant_for_sale`(`plantsale_id`,`plant`,`quantity`,`amount`) values 
(1,'dfdfd','100','100'),
(2,'rtyr','20','50'),
(3,'thulasi','2','100'),
(4,'ghhjk','2','677');

/*Table structure for table `purchase` */

DROP TABLE IF EXISTS `purchase`;

CREATE TABLE `purchase` (
  `purchase_id` int(11) NOT NULL AUTO_INCREMENT,
  `plantsale_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `quantity` varchar(100) DEFAULT NULL,
  `amount` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`purchase_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `purchase` */

insert  into `purchase`(`purchase_id`,`plantsale_id`,`user_id`,`quantity`,`amount`,`date`,`status`) values 
(1,1,1,'2','200','12/5/22','paid'),
(2,1,2,'1','100','2022-03-06','pending'),
(3,3,1,'1','100','2023-08-03','pending'),
(4,4,1,'1','677','2023-08-16','pending'),
(5,4,1,'1','677','2023-08-16','pending'),
(6,4,1,'1','677','2023-08-16','pending'),
(7,4,1,'1','677','2023-08-16','pending'),
(8,4,3,'1','677','2023-08-16','pending');

/*Table structure for table `question_answers` */

DROP TABLE IF EXISTS `question_answers`;

CREATE TABLE `question_answers` (
  `question_answer_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `research_id` int(11) DEFAULT NULL,
  `question` varchar(100) DEFAULT NULL,
  `answer` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`question_answer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `question_answers` */

/*Table structure for table `research` */

DROP TABLE IF EXISTS `research`;

CREATE TABLE `research` (
  `researcher_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`researcher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `research` */

insert  into `research`(`researcher_id`,`login_id`,`first_name`,`last_name`,`place`,`phone`,`email`) values 
(1,2,'anna','rose','kochi','7034589624','anna@gmail.com'),
(2,9,'krishnendhu','jerin','kollam','9674538345','kris@gmail.com'),
(3,11,'adarsh','biju','thrissur','9549345678','adarsh@gmail.com'),
(4,13,'','','','',''),
(5,14,'adarsh','biju','kollam','09549345678','adarsh@gmail.com'),
(6,15,'aparna','murali','kozhikode','8967567894','aparna@gmail.com'),
(7,17,'aparna','murali','kannur','08967567894','aparna@gmail.com');

/*Table structure for table `sales` */

DROP TABLE IF EXISTS `sales`;

CREATE TABLE `sales` (
  `sales_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`sales_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `sales` */

insert  into `sales`(`sales_id`,`login_id`,`first_name`,`last_name`,`place`,`phone`,`email`) values 
(1,7,'jinu','mary','ernakulam','4444455415454','jinu@gmail.com'),
(2,10,'arpitha','thomas','kannur','9453895678','arpitha@gmail.com'),
(3,12,'rahul','rajan','ernakulam','5690456789','rahul@gmail.com'),
(4,18,'rahul','rajan','kollam','05690456789','rahul@gmail.com');

/*Table structure for table `uploadvideo` */

DROP TABLE IF EXISTS `uploadvideo`;

CREATE TABLE `uploadvideo` (
  `uploadvideo_id` int(11) NOT NULL AUTO_INCREMENT,
  `file` varchar(1000) DEFAULT NULL,
  `out` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`uploadvideo_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `uploadvideo` */

insert  into `uploadvideo`(`uploadvideo_id`,`file`,`out`,`date`) values 
(1,'static/imagec1e5e4e7-84b9-463f-8dcc-5600b5f8d66fabc.jpg','2','2023-07-28'),
(2,'static/image42ba17af-ca70-44e1-9755-a28f83e118d0abc.jpg','6','2023-07-28'),
(3,'static/image9b8b8fb9-dcd6-4bf9-ada8-c15348261c25abc.jpg','1','2023-07-28'),
(4,'static/imaged556934f-8892-4f79-a168-c0dc7d806f32abc.jpg','6','2023-07-29'),
(5,'static/image93b8b023-0156-4565-8bd4-bab4e7002191abc.jpg','0','2023-08-16'),
(6,'static/imageb667f815-72df-41c2-a122-ddb5e31a8605abc.jpg','Asthma Plant','2023-08-17');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`user_id`,`login_id`,`first_name`,`last_name`,`place`,`phone`,`email`) values 
(1,6,'anu','mary','kochi','552585555','anu@gmail.com'),
(2,8,'hdh','dhhd','gsgs@fjjd.fjdj','5966852555','hdhd'),
(3,16,'anju','santhosh ','anju@gmail.com','8963236589','kollam'),
(4,19,'rohit','satheesh','rohit@gmail.com','6958473218','Thrissur ');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
