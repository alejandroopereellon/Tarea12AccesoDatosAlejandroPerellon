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
  `Grupo` varchar(5) NOT NULL,
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
INSERT INTO `alumno` VALUES (11,'PAOLA','PAOLA','P','2004-04-01','CAS','1','CASA'),(12,'ANDREA','ANDREA','A','2002-09-10','DAM','1','DAMB'),(23,'LAURA','LAURA','L','2001-10-05','DAM','2','DAMC'),(24,'IGNACIO','IGNACIO','I','2001-04-17','ASIR','2','ASIRA'),(34,'ALEJANDRO','ALEJANDRO','A','2000-05-09','DAM','2','DAMA'),(35,'JAVIER','JAVIER','J','2003-05-22','DAW','2','DAWC'),(45,'LUCIA','LUCIA','L','2003-08-12','ASIR','1','ASIRB'),(46,'SOFIA','SOFIA','S','2001-11-30','CAS','2','CASA'),(54,'DAVID','DAVID','D','2000-03-15','CAS','1','CASA'),(55,'BEATRIZ','BEATRIZ','B','2004-01-10','DAM','1','DAMA'),(56,'CARLOS','CARLOS','C','2001-12-20','DAW','2','DAWA'),(57,'MANUEL','MANUEL','M','2004-03-19','ASIR','1','ASIRA'),(66,'PABLO','PABLO','P','2000-06-25','DAW','2','DAWB'),(67,'MARIANA','MARIANA','M','2002-06-15','DAM','1','DAMC'),(68,'ELENA','ELENA','E','2002-08-05','DAM','1','DAMB'),(78,'SERGIO','SERGIO','S','2000-11-03','ASIR','2','ASIRB'),(79,'RUBEN','RUBEN','R','2000-12-12','DAW','2','DAWC'),(89,'ANA','ANA','A','2004-02-28','CAS','1','CASA'),(90,'RAUL','RAUL','R','2003-07-18','DAW','1','DAWB'),(91,'MARTA','MARTA','M','2003-07-21','CAS','1','CASC'),(101,'MARIO','MARIO','M','2002-05-23','DAM','1','DAMB'),(102,'CARMEN','CARMEN','C','2003-09-15','DAW','2','DAWC'),(103,'JUAN','JUAN','J','2001-11-02','ASIR','1','ASIRA'),(104,'ROSA','ROSA','R','2000-08-25','CAS','2','CASB'),(105,'ANDRES','ANDRES','A','2004-04-18','DAM','1','DAMA'),(106,'ELENA','ELENA','E','2002-12-29','DAW','1','DAWC'),(107,'ANGEL','ANGEL','A','2003-03-07','ASIR','2','ASIRB'),(108,'INES','INES','I','2001-01-14','CAS','1','CASA'),(109,'RAFAEL','RAFAEL','R','2004-06-30','DAM','2','DAMC'),(110,'LAURA','LAURA','L','2000-02-20','DAW','1','DAWB'),(111,'RAMON','RAMON','R','1999-03-23','CAS','2','CASD'),(117,'MIRIAM','MIRIAM','M','1999-11-07','DAM','D','DAMD');
/*!40000 ALTER TABLE `alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo`
--

DROP TABLE IF EXISTS `grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupo` (
  `id` varchar(6) NOT NULL,
  `Nombre` varchar(45) NOT NULL,
  `Descripcion` varchar(120) NOT NULL,
  `Tutor` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo`
--

LOCK TABLES `grupo` WRITE;
/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
INSERT INTO `grupo` VALUES ('ASIRA','ASIRA','Primer grupo de Administración de Sistemas Informáticos en Red','Andrés Navarro'),('ASIRB','ASIRB','Segundo grupo de Administración de Sistemas Informáticos en Red','Sofía Ramírez'),('ASIRC','ASIRC','Tercer grupo de Administración de Sistemas Informáticos en Red','Diego Torres'),('ASIRD','ASIRD','Cuarto grupo de Administración de Sistemas Informáticos en Red','Elena Morales'),('CASA','CASA','Primer grupo de Comercio y Administración','Raquel Gómez'),('CASB','CASB','Segundo grupo de Comercio y Administración','Pablo Castillo'),('CASC','CASC','Tercer grupo de Comercio y Administración','Lucía Serrano'),('CASD','CASD','Cuarto grupo de Comercio y Administración','Alberto Jiménez'),('DAMA','DAMA','Primer grupo de Desarrollo de Aplicaciones Multiplataforma','Luis Fernández'),('DAMB','DAMB','Segundo grupo de Desarrollo de Aplicaciones Multiplataforma','María Sánchez'),('DAMC','DAMC','Tercer grupo de Desarrollo de Aplicaciones Multiplataforma','Carlos López'),('DAMD','DAMD','Cuarto grupo de Desarrollo de Aplicaciones Multiplataforma','Ana Pérez'),('DAME','DAME','Cuarto grupo de Desarrollo de Aplicaciones Multiplataforma','Juan Luis Talamo'),('DAWA','DAWA','Primer grupo de Desarrollo de Aplicaciones Web','Jorge Martínez'),('DAWB','DAWB','Segundo grupo de Desarrollo de Aplicaciones Web','Laura Romero'),('DAWC','DAWC','Tercer grupo de Desarrollo de Aplicaciones Web','Francisco Ruiz'),('DAWD','DAWD','Cuarto grupo de Desarrollo de Aplicaciones Web','Patricia Gómez');
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

-- Dump completed on 2024-11-24 11:19:02
