-- MySQL dump 10.13  Distrib 8.0.17, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: linkage
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `add_friend_request`
--

DROP TABLE IF EXISTS `add_friend_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `add_friend_request` (
                                      `id` int(11) NOT NULL AUTO_INCREMENT,
                                      `username` varchar(32) NOT NULL,
                                      `targetName` varchar(32) NOT NULL,
                                      `selfIntro` text,
                                      `readStatus` smallint(6) NOT NULL,
                                      `acceptStatus` smallint(6) DEFAULT NULL,
                                      `replyStatus` smallint(6) DEFAULT NULL,
                                      `requestTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                      PRIMARY KEY (`id`),
                                      KEY `add_friend_request_user_username_fk` (`username`),
                                      KEY `add_friend_request_user_username_fk_2` (`targetName`),
                                      CONSTRAINT `add_friend_request_user_username_fk` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
                                      CONSTRAINT `add_friend_request_user_username_fk_2` FOREIGN KEY (`targetName`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `add_friend_request`
--

LOCK TABLES `add_friend_request` WRITE;
/*!40000 ALTER TABLE `add_friend_request` DISABLE KEYS */;
INSERT INTO `add_friend_request` VALUES (7,'skr','zzj','hello',1,1,0,'2019-11-10 07:46:25');
/*!40000 ALTER TABLE `add_friend_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_lock_moment_operation`
--

DROP TABLE IF EXISTS `admin_lock_moment_operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_lock_moment_operation` (
                                               `id` int(11) NOT NULL AUTO_INCREMENT,
                                               `momentId` int(11) NOT NULL,
                                               `reason` text,
                                               `executeTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                               `operationType` text NOT NULL,
                                               `adminName` varchar(32) NOT NULL,
                                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_lock_moment_operation`
--

LOCK TABLES `admin_lock_moment_operation` WRITE;
/*!40000 ALTER TABLE `admin_lock_moment_operation` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_lock_moment_operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_lock_post_operation`
--

DROP TABLE IF EXISTS `admin_lock_post_operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_lock_post_operation` (
                                             `id` int(11) NOT NULL AUTO_INCREMENT,
                                             `postId` int(11) NOT NULL,
                                             `reason` text,
                                             `executeTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                             `operationType` text NOT NULL,
                                             `adminName` varchar(32) NOT NULL,
                                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_lock_post_operation`
--

LOCK TABLES `admin_lock_post_operation` WRITE;
/*!40000 ALTER TABLE `admin_lock_post_operation` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_lock_post_operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_lock_user_operation`
--

DROP TABLE IF EXISTS `admin_lock_user_operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_lock_user_operation` (
                                             `id` int(11) NOT NULL AUTO_INCREMENT,
                                             `adminName` varchar(32) NOT NULL,
                                             `username` varchar(32) DEFAULT NULL,
                                             `operationType` text NOT NULL,
                                             `reason` text,
                                             `executeTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                             PRIMARY KEY (`id`),
                                             KEY `admin_lock_user_operation_user_username_fk` (`username`),
                                             KEY `admin_lock_user_operation_user_username_fk_2` (`adminName`),
                                             CONSTRAINT `admin_lock_user_operation_user_username_fk` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
                                             CONSTRAINT `admin_lock_user_operation_user_username_fk_2` FOREIGN KEY (`adminName`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_lock_user_operation`
--

LOCK TABLES `admin_lock_user_operation` WRITE;
/*!40000 ALTER TABLE `admin_lock_user_operation` DISABLE KEYS */;
INSERT INTO `admin_lock_user_operation` VALUES (11,'admin','zzj_1','lockUser',' This man is fake','2019-11-13 11:13:28'),(13,'admin','zzj_1','unlockUser',' This man is not fake','2019-11-13 11:19:30');
/*!40000 ALTER TABLE `admin_lock_user_operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `local_username` varchar(32) NOT NULL,
                          `global_username` varchar(32) NOT NULL,
                          PRIMARY KEY (`id`),
                          KEY `follow_global_user_username_fk` (`global_username`),
                          KEY `follow_user_username_fk` (`local_username`),
                          CONSTRAINT `follow_global_user_username_fk` FOREIGN KEY (`global_username`) REFERENCES `global_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
                          CONSTRAINT `follow_user_username_fk` FOREIGN KEY (`local_username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (4,'skr','ThinkAboutOurTrivialBeing');
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend`
--

DROP TABLE IF EXISTS `friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friend` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `my_username` varchar(32) DEFAULT NULL,
                          `friend_username` varchar(32) DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          KEY `friend_user_username_fk` (`my_username`),
                          KEY `friend_user_username_fk_2` (`friend_username`),
                          CONSTRAINT `friend_user_username_fk` FOREIGN KEY (`my_username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
                          CONSTRAINT `friend_user_username_fk_2` FOREIGN KEY (`friend_username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend`
--

LOCK TABLES `friend` WRITE;
/*!40000 ALTER TABLE `friend` DISABLE KEYS */;
INSERT INTO `friend` VALUES (1,'zzj','admin'),(2,'admin','zzj'),(5,'zzj','lym'),(6,'lym','zzj'),(7,'zzj','skr'),(8,'skr','zzj'),(9,'zzj','zzj_1'),(10,'zzj_1','zzj'),(11,'zzj','zzj_2'),(12,'zzj_2','zzj'),(13,'zzj','zzj_3'),(14,'zzj_3','zzj'),(15,'zzj','zzj_4'),(16,'zzj_4','zzj'),(17,'zzj','omg'),(18,'omg','zzj');
/*!40000 ALTER TABLE `friend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `global_user`
--

DROP TABLE IF EXISTS `global_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `global_user` (
                               `id` int(11) NOT NULL AUTO_INCREMENT,
                               `username` varchar(32) NOT NULL,
                               `locked` smallint(6) NOT NULL DEFAULT '0',
                               `iconUrl` text,
                               `description` text,
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `global_user_username_uindex` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `global_user`
--

LOCK TABLES `global_user` WRITE;
/*!40000 ALTER TABLE `global_user` DISABLE KEYS */;
INSERT INTO `global_user` VALUES (3,'zzjBigUnser',0,'https://cn.bing.com/images/search?q=北部战区空军组织跨昼夜飞行训练&FORM=ISTRTH&id=8A6C203801386A7B15F74187723C090CFAE363C1&cat=今日热图&lpversion=',NULL),(5,'ThinkAboutOurTrivialBeing',0,'','hhh i also have a big account');
/*!40000 ALTER TABLE `global_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `local_global_user`
--

DROP TABLE IF EXISTS `local_global_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `local_global_user` (
                                     `id` int(11) NOT NULL AUTO_INCREMENT,
                                     `local_username` varchar(32) NOT NULL,
                                     `global_username` varchar(32) DEFAULT NULL,
                                     `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                     PRIMARY KEY (`id`),
                                     KEY `local_global_user_global_user_username_fk` (`global_username`),
                                     KEY `local_global_user_user_username_fk` (`local_username`),
                                     CONSTRAINT `local_global_user_global_user_username_fk` FOREIGN KEY (`global_username`) REFERENCES `global_user` (`username`) ON UPDATE CASCADE,
                                     CONSTRAINT `local_global_user_user_username_fk` FOREIGN KEY (`local_username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `local_global_user`
--

LOCK TABLES `local_global_user` WRITE;
/*!40000 ALTER TABLE `local_global_user` DISABLE KEYS */;
INSERT INTO `local_global_user` VALUES (2,'skr','zzjBigUnser','2019-11-11 11:32:52'),(8,'zzj','ThinkAboutOurTrivialBeing','2019-11-13 12:28:43');
/*!40000 ALTER TABLE `local_global_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `name` varchar(32) NOT NULL,
                           `content` text,
                           `to` varchar(32) NOT NULL,
                           `status` int(11) DEFAULT NULL,
                           `sendTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           PRIMARY KEY (`id`),
                           KEY `message_user_username_fk` (`name`),
                           KEY `message_user_username_fk_2` (`to`),
                           CONSTRAINT `message_user_username_fk` FOREIGN KEY (`name`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
                           CONSTRAINT `message_user_username_fk_2` FOREIGN KEY (`to`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,'zzj','你哈皮','zzj',1,'2019-11-10 04:47:08'),(2,'zzj','你哈皮','admin',1,'2019-11-10 04:47:08'),(3,'zzj','你牛皮','admin',1,'2019-11-10 04:47:08'),(4,'admin','哈哈哈 我看到了','zzj',1,'2019-11-10 04:47:08'),(5,'admin','你好 ','zzj',1,'2019-11-10 04:47:08'),(6,'zzj','你好你好','admin',1,'2019-11-10 04:47:08'),(13,'zzj','asdfg','zzj',1,'2019-11-10 07:29:34'),(14,'zzj','kldglfng','skr',1,'2019-11-10 07:29:45'),(15,'skr','nihao','zzj',1,'2019-11-10 07:35:19'),(16,'skr','有蒂娜那时的风','zzj',1,'2019-11-10 07:35:29'),(17,'skr','safasdf','zzj',1,'2019-11-10 07:38:32'),(18,'skr','阿斯顿发 v','zzj',1,'2019-11-10 07:38:37'),(19,'skr','从不曾 v','skr',1,'2019-11-10 07:39:41'),(20,'skr','阿斯顿发 v','zzj',1,'2019-11-10 07:39:52'),(21,'skr','阿斯顿发 v','zzj',1,'2019-11-10 07:40:25');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moment`
--

DROP TABLE IF EXISTS `moment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `moment` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `poster_name` varchar(32) NOT NULL,
                          `text` text NOT NULL,
                          `picture_num` int(2) DEFAULT NULL,
                          `video_num` int(2) DEFAULT NULL,
                          `time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          PRIMARY KEY (`id`),
                          KEY `weblog_global_user_username_fk` (`poster_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moment`
--

LOCK TABLES `moment` WRITE;
ALTER TABLE moment AUTO_INCREMENT=1;
/*!40000 ALTER TABLE `moment` DISABLE KEYS */;
/*!40000 ALTER TABLE `moment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moment_comment`
--

DROP TABLE IF EXISTS `moment_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `moment_comment` (
                                  `id` int(11) NOT NULL AUTO_INCREMENT,
                                  `moment_id` int(11) DEFAULT NULL,
                                  `content` text,
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moment_comment`
--

LOCK TABLES `moment_comment` WRITE;
ALTER TABLE moment_comment AUTO_INCREMENT=1;
/*!40000 ALTER TABLE `moment_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `moment_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `post_name` varchar(32) DEFAULT NULL,
                        `text` text,
                        `picture_num` int(2) DEFAULT NULL,
                        `video_num` int(2) DEFAULT NULL,
                        `time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
ALTER TABLE post AUTO_INCREMENT=1;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_comment`
--

DROP TABLE IF EXISTS `post_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_comment` (
                                `id` int(11) NOT NULL AUTO_INCREMENT,
                                `post_id` int(11) DEFAULT NULL,
                                `content` text,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_comment`
--

LOCK TABLES `post_comment` WRITE;
ALTER TABLE post_comment AUTO_INCREMENT=1;
/*!40000 ALTER TABLE `post_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `post_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `name` varchar(32) NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_admin'),(2,'ROLE_user');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `username` varchar(32) NOT NULL,
                        `password` varchar(255) NOT NULL,
                        `enabled` tinyint(1) NOT NULL,
                        `locked` tinyint(1) NOT NULL,
                        `phoneNumber` varchar(30) NOT NULL,
                        `description` text,
                        `sex` smallint(6) DEFAULT NULL,
                        `lastLogIn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        `iconUrl` varchar(100) DEFAULT NULL,
                        `address` text,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `user_username_uindex` (`username`),
                        UNIQUE KEY `user_id_uindex` (`id`),
                        KEY `user_phoneNumber_index` (`phoneNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','$2y$10$aMK1CNHNz16C9C1JwNjtHuWYaDluNufZpGHb4qXIiQPQCK67W7feC',1,0,'',NULL,1,'2019-11-13 11:47:02',NULL,NULL),(2,'zzj','$2y$10$aMK1CNHNz16C9C1JwNjtHuWYaDluNufZpGHb4qXIiQPQCK67W7feC',1,0,'','How handsome I am!',1,'2019-11-13 13:24:36',NULL,'Chengdu'),(7,'skr','$2a$10$zapaTNnBIf.VA0FE3.JxmevLEBNXt9v9lr/Q5aDsMJXwxwr60z2/6',1,0,'18280096128','i hate zzj',0,'2019-11-13 12:33:27',NULL,NULL),(13,'lym','$2a$10$dDwIKrenHzns0uY6ZMcN4OJXVtzp1LOW3ieZaBEv2rJmnPEZD64ie',1,0,'18621062280','? why i am here',0,'2019-11-10 05:37:43',NULL,NULL),(14,'zzj_1','1',1,0,'12345678908','zzj No.2',1,'2019-11-10 05:37:43',NULL,NULL),(15,'zzj_2','2',1,0,'12345678901','zzj zzj No.3',0,'2019-11-11 09:07:13',NULL,NULL),(16,'zzj_3','3',1,0,'123','zzj No.4',1,'2019-11-11 09:07:54',NULL,NULL),(17,'zzj_4','4',1,0,'345','zzj No.5',0,'2019-11-11 09:08:30',NULL,NULL),(18,'omg','5',1,0,'4367','zzj No.6',1,'2019-11-11 09:10:44',NULL,NULL),(19,'godfather','6',1,0,'2355','zzj No.7',0,'2019-11-11 09:18:07',NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `UserLogInSetAllRead` AFTER UPDATE ON `user` FOR EACH ROW begin
    update message set status=1 where (sendTime between OLD.lastLogIn and NEW.lastLogIn) and (`to`=NEW.username);
    update add_friend_request set readStatus=1, replyStatus=1 where (requestTime between OLD.lastLogIn and NEW.lastLogIn)
                                                 and (username=NEW.username or targetName = NEW.username);

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `uid` int(11) NOT NULL,
                             `rid` int(11) NOT NULL,
                             PRIMARY KEY (`id`),
                             KEY `user_role_role_id_fk` (`rid`),
                             KEY `user_role_user_id_fk` (`uid`),
                             CONSTRAINT `user_role_role_id_fk` FOREIGN KEY (`rid`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                             CONSTRAINT `user_role_user_id_fk` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (2,2,2),(14,1,1),(15,1,2),(16,7,2),(22,13,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `who_should_see_moment`
--

DROP TABLE IF EXISTS `who_should_see_moment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `who_should_see_moment` (
                                         `id` int(11) NOT NULL AUTO_INCREMENT,
                                         `moment_id` int(11) DEFAULT NULL,
                                         `username` varchar(32) DEFAULT NULL,
                                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

ALTER TABLE who_should_see_moment AUTO_INCREMENT=1;
--
-- Table structure for table `who_should_see_post`
--

DROP TABLE IF EXISTS `who_should_see_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `who_should_see_post` (
                                       `id` int(11) NOT NULL AUTO_INCREMENT,
                                       `post_id` int(11) DEFAULT NULL,
                                       `global_username` varchar(32) DEFAULT NULL,
                                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `who_should_see_post`
--

LOCK TABLES `who_should_see_post` WRITE;
ALTER TABLE who_should_see_post AUTO_INCREMENT=1;
/*!40000 ALTER TABLE `who_should_see_post` DISABLE KEYS */;
/*!40000 ALTER TABLE `who_should_see_post` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-13 22:14:41
