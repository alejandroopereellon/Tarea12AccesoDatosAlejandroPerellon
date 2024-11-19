CREATE DATABASE  IF NOT EXISTS `alumnos02` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `alumnos02`;
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: alumnos02
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `alumno`
--

DROP TABLE IF EXISTS `alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alumno` (
  `NIA` int NOT NULL,
  `Nombre` varchar(45) NOT NULL,
  `Apellidos` varchar(45) NOT NULL,
  `Genero` char(1) NOT NULL,
  `FechaNacimiento` date NOT NULL,
  `Ciclo` varchar(45) NOT NULL,
  `Curso` varchar(45) NOT NULL,
  `Grupo` varchar(1) NOT NULL,
  PRIMARY KEY (`NIA`),
  KEY `FK_AlumnoGrupo_idx` (`Grupo`),
  CONSTRAINT `FK_alumno_grupo` FOREIGN KEY (`Grupo`) REFERENCES `grupo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumno`
--

LOCK TABLES `alumno` WRITE;
/*!40000 ALTER TABLE `alumno` DISABLE KEYS */;
INSERT INTO `alumno` VALUES (11,'PAOLA','MARTIN AVELLANEDA','M','2004-04-01','CAS','1','A'),(12,'ANDREA','RAMIREZ GIL','M','2002-09-10','DAM','1','B'),(23,'LAURA','MARTINEZ LOPEZ','M','2001-10-05','DAM','2','C'),(24,'IGNACIO','RAMOS MORENO','H','2001-04-17','ASIR','2','A'),(34,'ALEJANDRO','PERELLON LOPEZ','H','2000-05-09','DAM','2','A'),(35,'JAVIER','MARTINEZ LARA','H','2003-05-22','DAW','2','C'),(45,'LUCIA','GOMEZ SANCHEZ','M','2003-08-12','ASIR','1','B'),(46,'SOFIA','GUTIERREZ PEREZ','M','2001-11-30','CAS','2','A'),(54,'DAVID','SANCHEZ RODRIGUEZ','H','2000-03-15','CAS','1','A'),(55,'BEATRIZ','NAVARRO MARTINEZ','M','2004-01-10','DAM','1','A'),(56,'CARLOS','DIAZ MORALES','H','2001-12-20','DAW','2','A'),(57,'MANUEL','PEREZ TORRES','H','2004-03-19','ASIR','1','A'),(66,'PABLO','FERRER GOMEZ','H','2000-06-25','DAW','2','B'),(67,'MARIANA','LOPEZ HERNANDEZ','M','2002-06-15','DAM','1','C'),(68,'ELENA','SERRANO LOPEZ','M','2002-08-05','DAM','1','B'),(78,'SERGIO','PEREZ RUIZ','H','2000-11-03','ASIR','2','B'),(79,'RUBEN','ORTEGA DIAZ','H','2000-12-12','DAW','2','C'),(89,'ANA','JIMENEZ NAVARRO','M','2004-02-28','CAS','1','A'),(90,'RAUL','FERNANDEZ GARCIA','H','2003-07-18','DAW','1','B'),(91,'MARTA','CASTRO FERNANDEZ','M','2003-07-21','CAS','1','C'),(101,'MARIO','GARCIA PEREZ','H','2002-05-23','DAM','1','B'),(102,'CARMEN','RUIZ MARTINEZ','M','2003-09-15','DAW','2','C'),(103,'JUAN','LOPEZ TORRES','H','2001-11-02','ASIR','1','A'),(104,'ROSA','HERNANDEZ DIAZ','M','2000-08-25','CAS','2','B'),(105,'ANDRES','MORENO GOMEZ','H','2004-04-18','DAM','1','A'),(106,'ELENA','MARTIN SERRANO','M','2002-12-29','DAW','1','C'),(107,'ANGEL','FERNANDEZ LOPEZ','H','2003-03-07','ASIR','2','B'),(108,'INES','ROMERO PEREZ','M','2001-01-14','CAS','1','A'),(109,'RAFAEL','JIMENEZ RUIZ','H','2004-06-30','DAM','2','C'),(110,'LAURA','MOLINA GARCIA','M','2000-02-20','DAW','1','B'),(111,'RAMON','LOPEZ RUBIO','H','1999-03-23','CAS','2','D');
/*!40000 ALTER TABLE `alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo`
--

DROP TABLE IF EXISTS `grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupo` (
  `id` varchar(1) NOT NULL,
  `Nombre` varchar(45) NOT NULL,
  `Descripcion` varchar(45) NOT NULL,
  `Tutor` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo`
--

LOCK TABLES `grupo` WRITE;
/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
INSERT INTO `grupo` VALUES ('A','Grupo A','Grupo básico','Carla Martínez'),('B','Grupo B','Grupo medio','Fernando García'),('C','Grupo C','Grupo superior','Lucía Herrera'),('D','Grupo D','Grupo avanzado','Javier Torres');
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-19 23:33:52
