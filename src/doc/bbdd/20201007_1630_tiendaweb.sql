-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: tiendaweb
-- ------------------------------------------------------
-- Server version	5.7.28-log

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
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `ID_CATEGORIA` int(11) NOT NULL AUTO_INCREMENT,
  `ID_SECCION` int(11) DEFAULT NULL,
  `NOMBRE` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID_CATEGORIA`),
  KEY `FK_CATEGORIAS_SECCIONES` (`ID_SECCION`),
  CONSTRAINT `FK_CATEGORIAS_SECCIONES` FOREIGN KEY (`ID_SECCION`) REFERENCES `secciones` (`ID_SECCION`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,1,'Charcutería'),(2,1,'Pollería'),(3,1,'Carnicería');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `ID_CLIENTE` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `APELLIDOS` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `TELEFONO` varchar(9) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DNI` varchar(9) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DIRECCION` varchar(300) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_CLIENTE`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'Isabel','Antón Pinz','656774421','1234567C','Avda. de la Albufera 245, 7º G'),(2,'Manuela','Colorado Pérez','654654654','22223333Q','Calle del Amparo 35, 2º B'),(3,'Pepe','Perez Pérez','655787878','22334455H','La calle del cliente'),(4,'Alfredo','Santos Marín','646778899','12121212R','Calle Puerto de Canfranc 36, 2º A'),(5,'Marta','Sanz Montes','912342233','333444L','Avda. del Puerto 88, 7º C'),(6,'Lorena','Martínez Lorca','666777888','55667788L','Calle Puerto de la Bonaigua 33, 7º G'),(7,'Jose Antonio','Mar de Cuenca','677889900','123123F','Paseo de los Melancólicos 219, Bj H'),(8,'Paula','Miranda Melero','912342244','22335566J','Arroyo de Fontarrón 36, 3º D'),(9,'Segismundo','Porlier Pérez','666557788','55442211L','c/ María Benítes 25, 4º C'),(10,'Pepe','Perez Pérez','655787878','22334455H','La calle del cliente'),(12,'Pedro','Libanés Dorado','912223344','22332233J','Camino viejo de Leganés 232, 8º A'),(16,'Pilar','Grande Sáinz','911111911','50900111H','Avda. del Mediterráneo 222, 4º F'),(17,'Carmen','Cabrera Velez','660876543','66031317L','AAA'),(18,'Juana','Palacios Cobos','660876543','26070278P','AAA'),(19,'Milagros','Suarez Lujan','660876543','47510331J','AAA'),(20,'Alba','Andres Echeverria','660876543','78624177B','AAA'),(21,'Maria Jesus','Exposito Lago','660876543','34042541B','AAA');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalles`
--

DROP TABLE IF EXISTS `detalles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalles` (
  `ID_DETALLE` int(11) NOT NULL AUTO_INCREMENT,
  `ID_PRODUCTO` int(11) DEFAULT NULL,
  `ID_PEDIDO` int(11) DEFAULT NULL,
  `PRECIO` double(4,2) DEFAULT NULL,
  `CANTIDAD` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_DETALLE`),
  KEY `FK_DETALLES_PRODUCTOS` (`ID_PRODUCTO`),
  KEY `FK_DETALLES_PEDIDOS` (`ID_PEDIDO`),
  CONSTRAINT `FK_DETALLES_PEDIDOS` FOREIGN KEY (`ID_PEDIDO`) REFERENCES `pedidos` (`ID_PEDIDO`),
  CONSTRAINT `FK_DETALLES_PRODUCTOS` FOREIGN KEY (`ID_PRODUCTO`) REFERENCES `productos` (`ID_PRODUCTO`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalles`
--

LOCK TABLES `detalles` WRITE;
/*!40000 ALTER TABLE `detalles` DISABLE KEYS */;
INSERT INTO `detalles` VALUES (116,17,49,15.95,1),(117,18,49,21.50,1),(118,20,50,9.95,1),(119,27,51,15.75,2),(120,29,51,15.99,1),(121,34,51,9.99,2);
/*!40000 ALTER TABLE `detalles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ficheros`
--

DROP TABLE IF EXISTS `ficheros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ficheros` (
  `ID_FICHERO` int(6) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `CONTENT_TYPE` varchar(200) COLLATE utf8_bin NOT NULL,
  `DATA_BYTES` mediumblob NOT NULL,
  PRIMARY KEY (`ID_FICHERO`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ficheros`
--

LOCK TABLES `ficheros` WRITE;
/*!40000 ALTER TABLE `ficheros` DISABLE KEYS */;
INSERT INTO `ficheros` VALUES (1,'denegar.png','image/png',_binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0\0\0\0\0\0\0\�w=�\0\03IDATx^��OhU\�?\�\�\�f\�5\�֋^z�RTZ��T\�Q\���E\n6X4^�\�c�J=\�emAH)�\�!�\�K����X�BA(��(E�\�6\�\�d\��\�ϙ}d�eY?�ex�\�\�;�\�3��1�RiP�\'���@�\�H�o�zq̩K	@\�\�bu\�~��F����M��#\�\0\� ���צP�\�w��7?�z\�DCW7;}�{w��y!��\�\�\�\0&�Dv�`yy�z\�h��ǀĨ*��\�_�p�\�{ry�*C\n���ka�O�\�U{\�\��`s-��{\�I?~�z�m��|ܦ�5� ��\�[���*=��\�;\�5�\�\�ޛ?\�\�G\�,�,IZo�Pp\�N/PA���4��*i\��P\�$�4)_\�\�W\n�\�Z�\���p���\��+p�\�e)������\���\n�T\�^A��,H��?g(���Ń\'\�\�`\rCSgu\�\�\�\rR\�R0{�\n�_S���r��j\�\�f��;r\�I�,�>E�x\���.�ӌ\�\�8|�m\�X��cT|�W�*5qe��qH�P3�(R�:��\�<\�C,pi��l&xՊܣx�����\�P\�H\�\���*ԭ�`l�\��`�\� �\�3\�Ѝ��\��7[�\��\�h��Si��/��\�\0M\�\�θ_�g�\�V/\�l��ƥ\�J\�qE#Q\r.�\�\���x!�%�\�\�\�2�\�,�\��\�V$]�֥5Y\�6�w�E$�D`\�}xa/��\�%/p!���\�ܑ�rg1�w�\�\��\�s��v\�����I&&&؉��^�ۙ��\�E�˝E��ys\�}�e79�\�{\�\��\�=\�\�#%\��yE�:�\�n\�|x\0����|�\0\�&�w�?�߶\�\Z\n�O�6�\Z�\�N\�\�\0\0\0\0IEND�B`�');
/*!40000 ALTER TABLE `ficheros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos` (
  `ID_PEDIDO` int(11) NOT NULL AUTO_INCREMENT,
  `ID_CLIENTE` int(11) DEFAULT NULL,
  `TOTAL` double(4,2) DEFAULT NULL,
  `FECHA` date DEFAULT NULL,
  `NUM_ARTICULOS` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_PEDIDO`),
  KEY `FK_PEDIDOS_CLIENTES` (`ID_CLIENTE`),
  CONSTRAINT `FK_PEDIDOS_CLIENTES` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `clientes` (`ID_CLIENTE`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (49,1,37.45,'2020-10-03',2),(50,5,9.95,'2020-10-03',1),(51,8,67.47,'2020-10-03',5);
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `ID_PRODUCTO` int(11) NOT NULL AUTO_INCREMENT,
  `ID_CATEGORIA` int(11) DEFAULT NULL,
  `NOMBRE` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PRECIO` double(4,2) DEFAULT NULL,
  `NOMBRE_FOTO` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID_PRODUCTO`),
  KEY `FK_PRODUCTOS_CATEGORIAS` (`ID_CATEGORIA`),
  CONSTRAINT `FK_PRODUCTOS_CATEGORIAS` FOREIGN KEY (`ID_CATEGORIA`) REFERENCES `categorias` (`ID_CATEGORIA`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (16,2,'Las tinieblas y el alba (Ken Follet)',19.99,'1'),(17,1,'El poder del ahora (Eckhart Tolle)',15.95,'2'),(18,1,'El monje que vendió su Ferrari (Robin Sharma)',21.50,'3'),(19,1,'La legión perdida (Santiago Posteguillo)',11.85,'4'),(20,1,'El laberinto de los espíritus (Carlos Ruíz Zafón)',9.95,'5'),(21,3,'No llores por un beso (Mary Higgins Clark)',8.95,'6'),(22,3,'El mensaje de Pandora (Javier Sierra)',15.89,'7'),(23,3,'Patria  <br> (Fernando Aramburu)',24.90,'8'),(24,2,'SIDI (Arturo Pérez-Reverte)',13.95,'9'),(25,3,'Terra alta (Javier Cercas)',20.99,'10'),(26,2,'La madre de Frankenstein (Almudena Grandes)',21.99,'11'),(27,3,'La buena suerte (Rosa Montero)',15.75,'12'),(28,3,'El latido de la tierra (Luz Gabas)',12.99,'13'),(29,3,'El último barco (Domingo Villar)',15.99,'14'),(30,3,'El beso del ángel (Andrés Pascual)',12.00,'15'),(31,3,'La oscuridad que conoces (Amy Engel)',16.59,'16'),(32,3,'El palacio de media noche (Carlos Ruíz Zafón)',22.99,'17'),(33,1,'Sapiens <br> (Yuvul Noah Harari)',13.75,'18'),(34,1,'Reina roja (Juan Gómez-Jurado)',9.99,'19'),(35,2,'La vida desnuda (Mónica Carrillo)',11.90,'20');
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `secciones`
--

DROP TABLE IF EXISTS `secciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `secciones` (
  `ID_SECCION` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID_SECCION`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `secciones`
--

LOCK TABLES `secciones` WRITE;
/*!40000 ALTER TABLE `secciones` DISABLE KEYS */;
INSERT INTO `secciones` VALUES (1,'Alimentación');
/*!40000 ALTER TABLE `secciones` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-07 16:30:15
