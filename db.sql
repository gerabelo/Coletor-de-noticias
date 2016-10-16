-- MySQL dump 10.13  Distrib 5.7.15, for Linux (x86_64)
--
-- Host: localhost    Database: webbot
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
-- Table structure for table `sources`
--

DROP TABLE IF EXISTS `sources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sources` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `description` varchar(16) DEFAULT NULL,
  `dateCreate` varchar(18) DEFAULT NULL,
  `lastUpDate` varchar(18) DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sources`
--

LOCK TABLES `sources` WRITE;
/*!40000 ALTER TABLE `sources` DISABLE KEYS */;
INSERT INTO `sources` VALUES (1,NULL,NULL,NULL,'https://sputniknews.com'),(2,NULL,NULL,NULL,'https://rt.com'),(3,NULL,NULL,NULL,'https://www.theguardian.com/international'),(4,NULL,NULL,NULL,'http://www.dailymail.co.uk/home/index.html'),(5,NULL,NULL,NULL,'http://www.nytimes.com/'),(6,NULL,NULL,NULL,'http://www.latimes.com/'),(7,NULL,NULL,NULL,'https://www.washingtonpost.com/'),(8,NULL,NULL,NULL,'http://brasil.elpais.com/'),(9,NULL,NULL,NULL,'http://edition.cnn.com/'),(10,NULL,NULL,NULL,'http://www.bbc.com'),(11,NULL,NULL,NULL,'http://g1.globo.com/'),(12,NULL,NULL,NULL,'http://www.ig.com.br/'),(13,NULL,NULL,NULL,'http://www.r7.com/'),(14,NULL,NULL,NULL,'https://br.noticias.yahoo.com/'),(15,NULL,NULL,NULL,'http://www.estadao.com.br/'),(16,NULL,NULL,NULL,'http://www.folha.uol.com.br/'),(17,NULL,NULL,NULL,'http://www.cartacapital.com.br/'),(18,NULL,NULL,NULL,'http://imasters.com.br/'),(19,NULL,NULL,NULL,'http://www.clubedohardware.com.br/'),(20,NULL,NULL,NULL,'http://olhardigital.uol.com.br/'),(21,NULL,NULL,NULL,'https://tecnoblog.net/'),(22,NULL,NULL,NULL,'http://www.cartacapital.com.br/'),(23,NULL,NULL,NULL,'http://www.france24.com/en/'),(24,NULL,NULL,NULL,'http://www.independent.co.uk/topic/France'),(25,NULL,NULL,NULL,'http://www.telegraph.co.uk'),(26,NULL,NULL,NULL,'http://www.reuters.com'),(27,NULL,NULL,NULL,'http://www.aljazeera.com'),(28,NULL,NULL,NULL,'http://www.foxnews.com'),(29,NULL,NULL,NULL,'http://www.technewsworld.com/'),(30,NULL,NULL,NULL,'https://www.cnet.com/news/'),(31,NULL,NULL,NULL,'http://www.theverge.com/tech'),(32,NULL,NULL,NULL,'http://www.spiegel.de/international/'),(33,NULL,NULL,NULL,'http://www.dw.com'),(34,NULL,NULL,NULL,'http://portal.imprensanacional.gov.br/'),(35,NULL,NULL,NULL,'http://elpais.com'),(36,NULL,NULL,NULL,'https://news.google.com'),(37,NULL,NULL,NULL,'https://www.news.com.au'),(38,NULL,NULL,NULL,'https://www.yahoo.com/news/'),(39,NULL,NULL,NULL,'http://www.news.com.au'),(40,NULL,NULL,NULL,'http://www.usatoday.com/news/'),(41,NULL,NULL,NULL,'http://indiatoday.intoday.in'),(42,NULL,NULL,NULL,'http://www.bloomberg.com'),(43,NULL,NULL,NULL,'http://www.forbes.com'),(44,NULL,NULL,NULL,'http://www.ft.com'),(45,NULL,NULL,NULL,'https://www.ft.com'),(46,NULL,NULL,NULL,'www.un.org/news/'),(47,NULL,NULL,NULL,'bandnewstv.band.uol.com.br'),(48,NULL,NULL,NULL,'http://www.ufam.edu.br/'),(49,NULL,NULL,NULL,'www.pragmatismopolitico.com.br'),(50,NULL,NULL,NULL,'www.otempo.com.br'),(51,NULL,NULL,NULL,'www.redebrasilatual.com.br'),(52,NULL,NULL,NULL,'www.viomundo.com.br');
/*!40000 ALTER TABLE `sources` ENABLE KEYS */;
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
  `dateCreate` varchar(18) DEFAULT NULL,
  `lastUpDate` varchar(18) DEFAULT NULL,
  `word` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `whiteList`
--

LOCK TABLES `whiteList` WRITE;
/*!40000 ALTER TABLE `whiteList` DISABLE KEYS */;
INSERT INTO `whiteList` VALUES (1,NULL,NULL,NULL,'parfor'),(2,NULL,NULL,NULL,'war'),(3,NULL,NULL,NULL,'guerra'),(4,NULL,NULL,NULL,'russia'),(5,NULL,NULL,NULL,'violenc'),(6,NULL,NULL,NULL,'violência'),(7,NULL,NULL,NULL,'breaking'),(8,NULL,NULL,NULL,'peace'),(9,NULL,NULL,NULL,'riot'),(10,NULL,NULL,NULL,'technology'),(11,NULL,NULL,NULL,'tecnologia'),(12,NULL,NULL,NULL,'protesto'),(13,NULL,NULL,NULL,'morte'),(14,NULL,NULL,NULL,'acidente'),(15,NULL,NULL,NULL,'murder'),(16,NULL,NULL,NULL,'dead'),(17,NULL,NULL,NULL,'crime'),(18,NULL,NULL,NULL,'strategic'),(19,NULL,NULL,NULL,'estratégia'),(20,NULL,NULL,NULL,'estrategia'),(21,NULL,NULL,NULL,'perigo'),(22,NULL,NULL,NULL,'danger'),(23,NULL,NULL,NULL,'ameaça'),(25,NULL,NULL,NULL,'global'),(26,NULL,NULL,NULL,'crash'),(27,NULL,NULL,NULL,'clash'),(28,NULL,NULL,NULL,'ISIS'),(29,NULL,NULL,NULL,'daesh'),(30,NULL,NULL,NULL,'Dilma'),(31,NULL,NULL,NULL,'Lula'),(32,NULL,NULL,NULL,'Delação'),(33,NULL,NULL,NULL,'Prisão'),(34,NULL,NULL,NULL,'Operação'),(35,NULL,NULL,NULL,'Bilhão'),(36,NULL,NULL,NULL,'Terror'),(37,NULL,NULL,NULL,'Left-wing'),(38,NULL,NULL,NULL,'Right-wing'),(39,NULL,NULL,NULL,'eleições'),(40,NULL,NULL,NULL,'elections'),(41,NULL,NULL,NULL,'attack'),(42,NULL,NULL,NULL,'ataque'),(43,NULL,NULL,NULL,'bomb'),(44,NULL,NULL,NULL,'atentado'),(45,NULL,NULL,NULL,'imposto'),(46,NULL,NULL,NULL,'brics'),(47,NULL,NULL,NULL,'hostage'),(48,NULL,NULL,NULL,'refem'),(49,NULL,NULL,NULL,'petrobras'),(50,NULL,NULL,NULL,'reféns'),(51,NULL,NULL,NULL,'snowden'),(52,NULL,NULL,NULL,'wikileaks'),(53,NULL,NULL,NULL,'threats'),(54,NULL,NULL,NULL,'obama'),(55,NULL,NULL,NULL,'clinton'),(56,NULL,NULL,NULL,'president'),(57,NULL,NULL,NULL,'senator'),(58,NULL,NULL,NULL,'senador'),(59,NULL,NULL,NULL,'deputado'),(60,NULL,NULL,NULL,'engenharia'),(61,NULL,NULL,NULL,'engineering'),(62,NULL,NULL,NULL,'protesters'),(63,NULL,NULL,NULL,'gaza'),(64,NULL,NULL,NULL,'palestine'),(65,NULL,NULL,NULL,'israel'),(66,NULL,NULL,NULL,'syria'),(67,NULL,NULL,NULL,'siria'),(68,NULL,NULL,NULL,'ceasefire'),(69,NULL,NULL,NULL,'pentagon'),(70,NULL,NULL,NULL,'assange'),(71,NULL,NULL,NULL,'exonera'),(72,NULL,NULL,NULL,'manaus'),(73,NULL,NULL,NULL,'earthquake'),(74,NULL,NULL,NULL,'terremoto'),(75,NULL,NULL,NULL,'catastrofe'),(76,NULL,NULL,NULL,'cessar-fogo'),(77,NULL,NULL,NULL,'bolsonaro'),(78,NULL,NULL,NULL,'estudo'),(79,NULL,NULL,NULL,'study'),(80,NULL,NULL,NULL,'tiroteio'),(81,NULL,NULL,NULL,'shootout'),(83,NULL,NULL,NULL,'rebel');
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

-- Dump completed on 2016-10-16 17:38:40
