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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `chat` */

insert  into `chat`(`chat_id`,`sender_id`,`receiver_id`,`message`,`date`) values 
(1,8,2,'hhh','2022-03-06'),
(2,2,8,'hyyy','2022-03-06');

/*Table structure for table `cultitechnique` */

DROP TABLE IF EXISTS `cultitechnique`;

CREATE TABLE `cultitechnique` (
  `technique_id` int(11) NOT NULL AUTO_INCREMENT,
  `plant` varchar(100) DEFAULT NULL,
  `details` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`technique_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `cultitechnique` */

insert  into `cultitechnique`(`technique_id`,`plant`,`details`) values 
(1,'dfsd','sfsdf');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `feedback` */

insert  into `feedback`(`feedback_id`,`user_id`,`feedback`,`date`) values 
(1,1,'fdsdf','12/5/22'),
(2,2,'gg','2022-03-05'),
(3,2,'hy','2022-03-05');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `usertype` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login_id`,`username`,`password`,`usertype`) values 
(1,'admin','admin','admin'),
(2,'anna','anna','Researcher'),
(4,'rio','rio','doctor'),
(6,'anu','anu','user'),
(7,'jinu','jinu','Sales Manager'),
(8,'jj','jj','user');

/*Table structure for table `materials` */

DROP TABLE IF EXISTS `materials`;

CREATE TABLE `materials` (
  `material_id` int(11) NOT NULL AUTO_INCREMENT,
  `researcher_id` int(11) DEFAULT NULL,
  `materials` varchar(100) DEFAULT NULL,
  `details` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`material_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `materials` */

insert  into `materials`(`material_id`,`researcher_id`,`materials`,`details`,`status`) values 
(1,1,'dfsdf','dfsdfdsf','Accepted');

/*Table structure for table `medicalplant` */

DROP TABLE IF EXISTS `medicalplant`;

CREATE TABLE `medicalplant` (
  `plant_id` int(11) NOT NULL AUTO_INCREMENT,
  `researcher_id` int(11) DEFAULT NULL,
  `plant` varchar(100) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`plant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `medicalplant` */

insert  into `medicalplant`(`plant_id`,`researcher_id`,`plant`,`image`) values 
(2,1,'fedfwefds','static/daf41551-06cb-4443-bc7d-d740032e8e75Screenshot (6).png');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `medicine` */

insert  into `medicine`(`medicine_id`,`sales_id`,`medicine_name`,`quantity`,`amount`,`date`) values 
(1,1,'wewe','10','100','2022-02-11'),
(2,1,'ererere','5','50','2022-02-11');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `order` */

insert  into `order`(`order_id`,`user_id`,`sales_id`,`amount`,`date`,`status`) values 
(3,2,1,'200','2022-03-06','Buy');

/*Table structure for table `order_detail` */

DROP TABLE IF EXISTS `order_detail`;

CREATE TABLE `order_detail` (
  `orderdetail_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `medicine_id` int(11) DEFAULT NULL,
  `quantity` varchar(50) DEFAULT NULL,
  `amount` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`orderdetail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `order_detail` */

insert  into `order_detail`(`orderdetail_id`,`order_id`,`medicine_id`,`quantity`,`amount`) values 
(3,3,2,'2','100'),
(4,3,1,'1','100');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `plant_for_sale` */

insert  into `plant_for_sale`(`plantsale_id`,`plant`,`quantity`,`amount`) values 
(1,'dfdfd','100','100'),
(2,'rtyr','20','50');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `purchase` */

insert  into `purchase`(`purchase_id`,`plantsale_id`,`user_id`,`quantity`,`amount`,`date`,`status`) values 
(1,1,1,'2','200','12/5/22','paid'),
(2,1,2,'1','100','2022-03-06','pending');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `research` */

insert  into `research`(`researcher_id`,`login_id`,`first_name`,`last_name`,`place`,`phone`,`email`) values 
(1,2,'anna','rose','kochi','7034589624','anna@gmail.com');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `sales` */

insert  into `sales`(`sales_id`,`login_id`,`first_name`,`last_name`,`place`,`phone`,`email`) values 
(1,7,'jinu','mary','ernakulam','4444455415454','jinu@gmail.com');

/*Table structure for table `uploadvideo` */

DROP TABLE IF EXISTS `uploadvideo`;

CREATE TABLE `uploadvideo` (
  `uploadvideo_id` int(11) NOT NULL AUTO_INCREMENT,
  `file` varchar(1000) DEFAULT NULL,
  `out` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`uploadvideo_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `uploadvideo` */

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`user_id`,`login_id`,`first_name`,`last_name`,`place`,`phone`,`email`) values 
(1,6,'anu','mary','kochi','552585555','anu@gmail.com'),
(2,8,'hdh','dhhd','gsgs@fjjd.fjdj','5966852555','hdhd');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
