-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: kamervankrypto
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `asset`
--

LOCK TABLES `asset` WRITE;
/*!40000 ALTER TABLE `asset` DISABLE KEYS */;
/*!40000 ALTER TABLE `asset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `bankaccount`
--

LOCK TABLES `bankaccount` WRITE;
/*!40000 ALTER TABLE `bankaccount` DISABLE KEYS */;
INSERT INTO `bankaccount` VALUES (1,5000,'2022-08-25T14:24:24','NL23KVKB356981234'),(2,1253,'2022-08-24T14:24:24','NL23KVKB356985759'),(3,40,'2022-08-23T14:24:24','NL23KVKB356984321'),(4,235689,'2022-08-22T14:24:24','NL23KVKB356981111'),(5,123456,'2022-08-26T17:04:30','NL15KVKB881051422'),(5,120000,'2022-08-29T10:21:23','NL15KVKB881051422'),(5,123456,'2022-08-29T10:21:46','NL15KVKB881051422');
/*!40000 ALTER TABLE `bankaccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `bankfee`
--

LOCK TABLES `bankfee` WRITE;
/*!40000 ALTER TABLE `bankfee` DISABLE KEYS */;
/*!40000 ALTER TABLE `bankfee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `rate`
--

LOCK TABLES `rate` WRITE;
/*!40000 ALTER TABLE `rate` DISABLE KEYS */;
/*!40000 ALTER TABLE `rate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `trader`
--

LOCK TABLES `trader` WRITE;
/*!40000 ALTER TABLE `trader` DISABLE KEYS */;
INSERT INTO `trader` VALUES (1,'password','Paul',NULL,'McCartney',298791823,'1942-05-15','Maple St','15','83291MA','Liverpool','macca@hotmail.com',1),(2,'wachtwoord','John',NULL,'Lennon',298791822,'31-09-1941','Oak St','42','83291LI','Liverpool','johnnieboy@yahoo.com',1),(3,'pw','Ringo',NULL,'Starr',292391822,'31-10-1944','Tree St','8','82191LI','Liverpool','starr@gmail.com',1),(4,'ww','George',NULL,'Harrisson',298791222,'14-03-1945','Fig St','9A','83291PL','Liverpool','awesomeguitarguy@live.nl',1),(5,'password2','Paul2',NULL,'McCartney2',298791824,'1984-05-15','Maple St','15','83291MA','Liverpool2','macca2@hotmail.com',1),(6,'pinky','Chad','mc','Monster',147259963,'1962-2-28','Loser lane','13','1313NL','Rotterdam','misterlittlewillie@useless.nl',1);
/*!40000 ALTER TABLE `trader` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `wallet`
--

LOCK TABLES `wallet` WRITE;
/*!40000 ALTER TABLE `wallet` DISABLE KEYS */;
/*!40000 ALTER TABLE `wallet` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-29 16:53:23
