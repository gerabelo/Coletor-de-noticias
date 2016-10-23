-- MySQL dump 10.13  Distrib 5.7.15, for Linux (x86_64)
--
-- Host: localhost    Database: newscrawler
-- ------------------------------------------------------
-- Server version	5.7.15-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `blackList`
--

DROP TABLE IF EXISTS `blackList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blackList` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `description` varchar(16) DEFAULT NULL,
  `word` varchar(32) DEFAULT NULL,
  `dateCreate` varchar(18) DEFAULT NULL,
  `lastUpDate` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blackList`
--

LOCK TABLES `blackList` WRITE;
/*!40000 ALTER TABLE `blackList` DISABLE KEYS */;
/*!40000 ALTER TABLE `blackList` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `description` varchar(16) DEFAULT NULL,
  `recurrenceInterval` int(9) DEFAULT NULL,
  `dateCreate` varchar(18) DEFAULT NULL,
  `lastUpDate` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `duplicates`
--

DROP TABLE IF EXISTS `duplicates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `duplicates` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `newsId` int(9) DEFAULT NULL,
  `hash` varchar(32) DEFAULT NULL,
  `dateCreate` varchar(18) DEFAULT NULL,
  `lastUpDate` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `duplicates`
--

LOCK TABLES `duplicates` WRITE;
/*!40000 ALTER TABLE `duplicates` DISABLE KEYS */;
/*!40000 ALTER TABLE `duplicates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `followLists`
--

DROP TABLE IF EXISTS `followLists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `followLists` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `categoryId` int(9) DEFAULT NULL,
  `sourceId` int(9) DEFAULT NULL,
  `description` varchar(16) DEFAULT NULL,
  `dateCreate` varchar(18) DEFAULT NULL,
  `lastUpDate` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `followLists`
--

LOCK TABLES `followLists` WRITE;
/*!40000 ALTER TABLE `followLists` DISABLE KEYS */;
/*!40000 ALTER TABLE `followLists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ignoreds`
--

DROP TABLE IF EXISTS `ignoreds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ignoreds` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `word` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `word` (`word`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ignoreds`
--

LOCK TABLES `ignoreds` WRITE;
/*!40000 ALTER TABLE `ignoreds` DISABLE KEYS */;
/*!40000 ALTER TABLE `ignoreds` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moda`
--

DROP TABLE IF EXISTS `moda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `moda` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `word` varchar(32) DEFAULT NULL,
  `counter` int(9) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `dateCreate` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60049 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moda`
--

LOCK TABLES `moda` WRITE;
/*!40000 ALTER TABLE `moda` DISABLE KEYS */;
INSERT INTO `moda` VALUES (60013,'china',1,0,'2016/10/23'),(60014,'nba',1,0,'2016/10/23'),(60015,'player',1,0,'2016/10/23'),(60016,'vandalizes',1,0,'2016/10/23'),(60017,'great',1,0,'2016/10/23'),(60018,'wall',1,0,'2016/10/23'),(60019,'signature',1,0,'2016/10/23'),(60020,'southwest',1,0,'2016/10/23'),(60021,'start',1,0,'2016/10/23'),(60022,'flying',1,0,'2016/10/23'),(60023,'cuba',1,0,'2016/10/23'),(60024,'november',1,0,'2016/10/23'),(60025,'developers',1,0,'2016/10/23'),(60026,'consider',1,0,'2016/10/23'),(60027,'turning',1,0,'2016/10/23'),(60028,'sunken',1,0,'2016/10/23'),(60029,'titanic',1,0,'2016/10/23'),(60030,'sister',1,0,'2016/10/23'),(60031,'ship',1,0,'2016/10/23'),(60032,'divers`',1,0,'2016/10/23'),(60033,'theme',1,0,'2016/10/23'),(60034,'park',1,0,'2016/10/23'),(60035,'wacky',1,0,'2016/10/23'),(60036,'things',1,0,'2016/10/23'),(60037,'haven`t',1,0,'2016/10/23'),(60038,'tried',1,0,'2016/10/23'),(60039,'walt',1,0,'2016/10/23'),(60040,'disney',1,0,'2016/10/23'),(60041,'world',1,0,'2016/10/23'),(60042,'yet',1,0,'2016/10/23'),(60043,'diabetics',1,0,'2016/10/23'),(60044,'have',1,0,'2016/10/23'),(60045,'worsening',1,0,'2016/10/23'),(60046,'blood',1,0,'2016/10/23'),(60047,'sugar',1,0,'2016/10/23'),(60048,'control',1,0,'2016/10/23');
/*!40000 ALTER TABLE `moda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `sourceId` int(9) DEFAULT NULL,
  `url` varchar(512) DEFAULT NULL,
  `text` varchar(512) DEFAULT NULL,
  `value` int(9) DEFAULT NULL,
  `hash` varchar(32) DEFAULT NULL,
  `dateCreate` varchar(17) DEFAULT NULL,
  `lastUpDate` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `hash` (`hash`)
) ENGINE=InnoDB AUTO_INCREMENT=948921 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `newsTemp`
--

DROP TABLE IF EXISTS `newsTemp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `newsTemp` (
  `id` int(9) DEFAULT NULL,
  `sourceId` int(9) DEFAULT NULL,
  `url` varchar(512) DEFAULT NULL,
  `text` varchar(512) DEFAULT NULL,
  `value` int(9) DEFAULT NULL,
  `hash` varchar(32) DEFAULT NULL,
  `dateCreate` varchar(18) DEFAULT NULL,
  `lastUpDate` varchar(18) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `newsTemp`
--

LOCK TABLES `newsTemp` WRITE;
/*!40000 ALTER TABLE `newsTemp` DISABLE KEYS */;
/*!40000 ALTER TABLE `newsTemp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sources`
--

DROP TABLE IF EXISTS `sources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sources` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `description` varchar(16) DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL,
  `dateCreate` varchar(18) DEFAULT NULL,
  `lastUpDate` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `url` (`url`)
) ENGINE=InnoDB AUTO_INCREMENT=222 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sources`
--

LOCK TABLES `sources` WRITE;
/*!40000 ALTER TABLE `sources` DISABLE KEYS */;
/*!40000 ALTER TABLE `sources` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stats`
--

DROP TABLE IF EXISTS `stats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stats` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `runTime` varchar(18) DEFAULT NULL,
  `crawlingTime` varchar(18) DEFAULT NULL,
  `totalSources` int(9) DEFAULT NULL,
  `totalKeyWords` int(9) DEFAULT NULL,
  `totalNews` int(9) DEFAULT NULL,
  `dateCreate` varchar(18) DEFAULT NULL,
  `lastUpDate` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5968 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stats`
--

LOCK TABLES `stats` WRITE;
/*!40000 ALTER TABLE `stats` DISABLE KEYS */;
/*!40000 ALTER TABLE `stats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `whiteList`
--

DROP TABLE IF EXISTS `whiteList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `whiteList` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `description` varchar(16) DEFAULT NULL,
  `word` varchar(32) DEFAULT NULL,
  `dateCreate` varchar(18) DEFAULT NULL,
  `lastUpDate` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `word` (`word`)
) ENGINE=InnoDB AUTO_INCREMENT=234 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `whiteList`
--

LOCK TABLES `whiteList` WRITE;
/*!40000 ALTER TABLE `whiteList` DISABLE KEYS */;
/*!40000 ALTER TABLE `whiteList` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-23 13:14:46
