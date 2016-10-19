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
) ENGINE=InnoDB AUTO_INCREMENT=233 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `whiteList`
--

LOCK TABLES `whiteList` WRITE;
/*!40000 ALTER TABLE `whiteList` DISABLE KEYS */;
INSERT INTO `whiteList` VALUES (1,NULL,'parfor',NULL,NULL),(2,NULL,'war',NULL,NULL),(3,NULL,'guerra',NULL,NULL),(4,NULL,'russia',NULL,NULL),(5,NULL,'violenc',NULL,NULL),(6,NULL,'violência',NULL,NULL),(7,NULL,'breaking',NULL,NULL),(8,NULL,'peace',NULL,NULL),(9,NULL,'riot',NULL,NULL),(10,NULL,'technology',NULL,NULL),(11,NULL,'tecnologia',NULL,NULL),(12,NULL,'protesto',NULL,NULL),(13,NULL,'morte',NULL,NULL),(14,NULL,'acidente',NULL,NULL),(15,NULL,'murder',NULL,NULL),(16,NULL,'dead',NULL,NULL),(17,NULL,'crime',NULL,NULL),(18,NULL,'strategic',NULL,NULL),(19,NULL,'estratégia',NULL,NULL),(21,NULL,'perigo',NULL,NULL),(22,NULL,'ameaça',NULL,NULL),(23,NULL,'global',NULL,NULL),(24,NULL,'crash',NULL,NULL),(25,NULL,'clash',NULL,NULL),(26,NULL,'ISIS',NULL,NULL),(27,NULL,'daesh',NULL,NULL),(28,NULL,'Dilma',NULL,NULL),(29,NULL,'Lula',NULL,NULL),(30,NULL,'Delação',NULL,NULL),(31,NULL,'Prisão',NULL,NULL),(32,NULL,'Operação',NULL,NULL),(33,NULL,'Bilhão',NULL,NULL),(34,NULL,'Terror',NULL,NULL),(35,NULL,'Left-wing',NULL,NULL),(36,NULL,'Right-wing',NULL,NULL),(37,NULL,'eleições',NULL,NULL),(38,NULL,'elections',NULL,NULL),(39,NULL,'attack',NULL,NULL),(40,NULL,'ataque',NULL,NULL),(42,NULL,'atentado',NULL,NULL),(43,NULL,'imposto',NULL,NULL),(44,NULL,'brics',NULL,NULL),(45,NULL,'hostage',NULL,NULL),(46,NULL,'refem',NULL,NULL),(47,NULL,'petrobras',NULL,NULL),(48,NULL,'reféns',NULL,NULL),(49,NULL,'snowden',NULL,NULL),(50,NULL,'wikileaks',NULL,NULL),(51,NULL,'threats',NULL,NULL),(52,NULL,'obama',NULL,NULL),(53,NULL,'clinton',NULL,NULL),(54,NULL,'president',NULL,NULL),(55,NULL,'senator',NULL,NULL),(56,NULL,'senador',NULL,NULL),(57,NULL,'deputado',NULL,NULL),(58,NULL,'engenharia',NULL,NULL),(59,NULL,' engineering',NULL,NULL),(60,NULL,'protesters',NULL,NULL),(61,NULL,'gaza',NULL,NULL),(62,NULL,'palestine',NULL,NULL),(63,NULL,'israel',NULL,NULL),(64,NULL,'syria',NULL,NULL),(65,NULL,'siria',NULL,NULL),(66,NULL,'ceasefire',NULL,NULL),(67,NULL,'pentagon',NULL,NULL),(68,NULL,'assange',NULL,NULL),(69,NULL,'exonera',NULL,NULL),(70,NULL,'manaus',NULL,NULL),(71,NULL,'earthquake',NULL,NULL),(72,NULL,'terremoto',NULL,NULL),(73,NULL,'catastrofe',NULL,NULL),(74,NULL,'cessar-fogo',NULL,NULL),(75,NULL,'bolsonaro',NULL,NULL),(76,NULL,'estudo',NULL,NULL),(77,NULL,'study',NULL,NULL),(78,NULL,'tiroteio',NULL,NULL),(79,NULL,'shootout',NULL,NULL),(80,NULL,'rebel',NULL,NULL),(81,NULL,'FHC',NULL,NULL),(82,NULL,' AI',NULL,NULL),(83,NULL,'discover',NULL,NULL),(105,NULL,'danger',NULL,NULL),(107,NULL,'warning',NULL,NULL),(113,NULL,'battle',NULL,NULL),(114,NULL,'iraq',NULL,NULL),(115,NULL,'violence',NULL,NULL),(116,NULL,'crimea',NULL,NULL),(117,NULL,'ukraine',NULL,NULL),(118,NULL,'sigilo',NULL,NULL),(119,NULL,'aécio',NULL,NULL),(121,NULL,'ministro',NULL,NULL),(122,NULL,'bovespa',NULL,NULL),(123,NULL,'valorização',NULL,NULL),(125,NULL,'trump',NULL,NULL),(127,NULL,'china',NULL,NULL),(128,NULL,' usa ',NULL,NULL),(129,NULL,' eua ',NULL,NULL),(130,NULL,' ee.uu ',NULL,NULL),(131,NULL,'europ',NULL,NULL),(132,NULL,'dengue',NULL,NULL),(133,NULL,'zika',NULL,NULL),(135,NULL,' enem ',NULL,NULL),(136,NULL,' fenomeno ',NULL,NULL),(137,NULL,' nobel ',NULL,NULL),(138,NULL,'bolívia',NULL,NULL),(140,NULL,'venezuela',NULL,NULL),(141,NULL,'brazil',NULL,NULL),(142,NULL,'coronel',NULL,NULL),(143,NULL,'juiz',NULL,NULL),(144,NULL,'hospital',NULL,NULL),(145,NULL,' marte ',NULL,NULL),(146,NULL,' mars ',NULL,NULL),(147,NULL,' templo ',NULL,NULL),(148,NULL,' temple ',NULL,NULL),(149,NULL,'sinagoga',NULL,NULL),(150,NULL,'mesquita',NULL,NULL),(151,NULL,'synagogue',NULL,NULL),(152,NULL,'mesquite',NULL,NULL),(153,NULL,'islamic',NULL,NULL),(154,NULL,'jewish',NULL,NULL),(155,NULL,'judaism',NULL,NULL),(157,NULL,'vítima',NULL,NULL),(159,NULL,'yemen',NULL,NULL),(160,NULL,'ibope',NULL,NULL),(161,NULL,'datafolha',NULL,NULL),(162,NULL,'pesquisa',NULL,NULL),(163,NULL,' oil ',NULL,NULL),(164,NULL,'impostos',NULL,NULL),(165,NULL,'educação',NULL,NULL),(166,NULL,'saúde',NULL,NULL),(167,NULL,'ministra',NULL,NULL),(168,NULL,'STF',NULL,NULL),(169,NULL,' PEC ',NULL,NULL),(170,NULL,'golpe',NULL,NULL),(172,NULL,'india',NULL,NULL),(173,NULL,'laboratório',NULL,NULL),(174,NULL,'segurança',NULL,NULL),(175,NULL,'falha',NULL,NULL),(176,NULL,'hacker',NULL,NULL),(177,NULL,'milhões',NULL,NULL),(178,NULL,'forças armadas',NULL,NULL),(179,NULL,'previdência',NULL,NULL),(180,NULL,'forças nacional',NULL,NULL),(181,NULL,'propina',NULL,NULL),(182,NULL,'roubo',NULL,NULL),(183,NULL,'reino unido',NULL,NULL),(184,NULL,'projeto',NULL,NULL),(185,NULL,'emergência',NULL,NULL),(186,NULL,'emergence',NULL,NULL),(187,NULL,'ministério',NULL,NULL),(189,NULL,'Gilmar',NULL,NULL),(190,NULL,'banco',NULL,NULL),(191,NULL,'confronto',NULL,NULL),(192,NULL,'manifestantes',NULL,NULL),(193,NULL,'legislativ',NULL,NULL),(194,NULL,'judiciário',NULL,NULL),(195,NULL,' CNJ ',NULL,NULL),(196,NULL,'matou',NULL,NULL),(198,NULL,'morre',NULL,NULL),(199,NULL,'bomb',NULL,NULL),(200,NULL,'artificial intelligence',NULL,NULL),(201,NULL,'theory',NULL,NULL),(202,NULL,'teoria',NULL,NULL),(203,NULL,'nasa ',NULL,NULL),(204,NULL,'radar',NULL,NULL),(205,NULL,'warship',NULL,NULL),(206,NULL,'censorship',NULL,NULL),(207,NULL,'promotor',NULL,NULL),(208,NULL,'justiça',NULL,NULL),(209,NULL,'FBI',NULL,NULL),(210,NULL,'KGB',NULL,NULL),(211,NULL,' CIA ',NULL,NULL),(212,NULL,'submarine',NULL,NULL),(213,NULL,'investigation',NULL,NULL),(214,NULL,'investigação',NULL,NULL),(215,NULL,'refugee',NULL,NULL),(216,NULL,'refugiado',NULL,NULL),(217,NULL,'wall st',NULL,NULL),(218,NULL,'backtrack',NULL,NULL),(219,NULL,'establishment',NULL,NULL),(220,NULL,'propaganda',NULL,NULL),(221,NULL,' bias ',NULL,NULL),(222,NULL,' spy ',NULL,NULL),(223,NULL,'bank',NULL,NULL),(224,NULL,'pobre',NULL,NULL),(225,NULL,'rico',NULL,NULL),(226,NULL,'classe',NULL,NULL),(227,NULL,'censor',NULL,NULL),(228,NULL,'sensor',NULL,NULL),(230,NULL,'robo',NULL,NULL),(231,NULL,' bot ',NULL,NULL),(232,NULL,'insurgen',NULL,NULL);
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

-- Dump completed on 2016-10-19  0:16:49
