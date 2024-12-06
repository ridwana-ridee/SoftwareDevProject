-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: employeedata
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

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
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `empID` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `division` varchar(100) NOT NULL,
  `jobTitle` varchar(100) NOT NULL,
  `ssn` char(9) NOT NULL,
  `salary` decimal(10,2) NOT NULL,
  PRIMARY KEY (`empID`),
  UNIQUE KEY `ssn` (`ssn`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'John','Doe','Finance','Accountant','123456789',58000.00),(2,'Jane','Smith','IT','Software Engineer','987654321',95000.00),(3,'Robert','Brown','HR','HR Manager','555666777',78000.00),(4,'Emily','Clark','Finance','Financial Analyst','111222333',62000.00),(5,'Michael','Davis','IT','Network Engineer','444555666',87000.00),(6,'Sarah','Wilson','Sales','Sales Manager','777888999',72000.00),(7,'David','Moore','Sales','Sales Associate','000111222',50000.00),(8,'Anna','Taylor','Marketing','Marketing Specialist','333444555',65000.00),(9,'James','Anderson','Marketing','SEO Expert','888999000',70000.00),(10,'Laura','Martin','HR','Recruiter','112233445',59000.00),(11,'Daniel','Harris','Finance','Auditor','667788990',76000.00),(12,'Sophia','Jackson','IT','Data Analyst','123123123',82000.00),(13,'William','White','Sales','Sales Executive','456456456',68000.00),(14,'Olivia','Green','Marketing','Content Creator','789789789',54000.00),(15,'Chris','Hall','Finance','Budget Manager','101010101',87000.00),(16,'Grace','Lee','HR','HR Assistant','202020202',47000.00),(17,'Ethan','Young','IT','System Administrator','303030303',88000.00),(18,'Mia','Hernandez','Sales','Regional Manager','404040404',97000.00),(19,'Alexander','King','Marketing','Brand Manager','505050505',89000.00),(20,'Charlotte','Scott','Finance','Investment Analyst','606060606',74000.00);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paystatement`
--

DROP TABLE IF EXISTS `paystatement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paystatement` (
  `payID` int(11) NOT NULL AUTO_INCREMENT,
  `empID` int(11) NOT NULL,
  `date` date NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  PRIMARY KEY (`payID`),
  KEY `empID` (`empID`),
  CONSTRAINT `paystatement_ibfk_1` FOREIGN KEY (`empID`) REFERENCES `employee` (`empID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paystatement`
--

LOCK TABLES `paystatement` WRITE;
/*!40000 ALTER TABLE `paystatement` DISABLE KEYS */;
INSERT INTO `paystatement` VALUES (1,1,'2024-01-31',4833.33),(2,1,'2024-02-28',4833.33),(3,2,'2024-01-31',7916.67),(4,2,'2024-02-28',7916.67),(5,3,'2024-01-31',6500.00),(6,3,'2024-02-28',6500.00),(7,4,'2024-01-31',5166.67),(8,4,'2024-02-28',5166.67),(9,5,'2024-01-31',7250.00),(10,5,'2024-02-28',7250.00),(11,6,'2024-01-31',6000.00),(12,6,'2024-02-28',6000.00),(13,7,'2024-01-31',4166.67),(14,7,'2024-02-28',4166.67),(15,8,'2024-01-31',5416.67),(16,8,'2024-02-28',5416.67),(17,9,'2024-01-31',5833.33),(18,9,'2024-02-28',5833.33),(19,10,'2024-01-31',4916.67),(20,10,'2024-02-28',4916.67),(21,11,'2024-01-31',6333.33),(22,11,'2024-02-28',6333.33),(23,12,'2024-01-31',6833.33),(24,12,'2024-02-28',6833.33),(25,13,'2024-01-31',5666.67),(26,13,'2024-02-28',5666.67),(27,14,'2024-01-31',4500.00),(28,14,'2024-02-28',4500.00),(29,15,'2024-01-31',7250.00),(30,15,'2024-02-28',7250.00),(31,16,'2024-01-31',3916.67),(32,16,'2024-02-28',3916.67),(33,17,'2024-01-31',7333.33),(34,17,'2024-02-28',7333.33),(35,18,'2024-01-31',8083.33),(36,18,'2024-02-28',8083.33),(37,19,'2024-01-31',7416.67),(38,19,'2024-02-28',7416.67),(39,20,'2024-01-31',6166.67),(40,20,'2024-02-28',6166.67);
/*!40000 ALTER TABLE `paystatement` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-02 13:42:03
