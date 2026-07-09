-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: localhost    Database: inventorydb
-- ------------------------------------------------------
-- Server version	8.0.46

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
-- Table structure for table `bom_item`
--

DROP TABLE IF EXISTS `bom_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bom_item` (
  `bom_id` int NOT NULL,
  `cost_per_component` double NOT NULL,
  `quantity_required` int NOT NULL,
  `component_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`bom_id`),
  KEY `FK93e9cqlpcecl51xpwc1r79kkh` (`component_id`),
  KEY `FKnjv78g697vkvl8x1jfhf8c9dw` (`product_id`),
  CONSTRAINT `FK93e9cqlpcecl51xpwc1r79kkh` FOREIGN KEY (`component_id`) REFERENCES `component` (`component_id`),
  CONSTRAINT `FKnjv78g697vkvl8x1jfhf8c9dw` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bom_item`
--

LOCK TABLES `bom_item` WRITE;
/*!40000 ALTER TABLE `bom_item` DISABLE KEYS */;
INSERT INTO `bom_item` VALUES (1,0.1,2,1001,1),(2,0.25,1,1002,1),(3,3,1,1003,1),(4,3,1,1004,1),(5,0.5,1,1005,1),(6,0.75,1,1006,1),(7,0.1,3,1001,2),(8,0.25,1,1002,2),(9,0.35,1,1003,2),(10,3,1,1004,2),(11,0.1,5,1001,3),(12,0.25,3,1002,3),(13,3,1,1004,3),(14,0.5,1,1005,3),(15,0.75,1,1006,3);
/*!40000 ALTER TABLE `bom_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `component`
--

DROP TABLE IF EXISTS `component`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `component` (
  `component_id` int NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `low_stock_limit` int NOT NULL,
  `quantity` int NOT NULL,
  `supplier_id` int DEFAULT NULL,
  `description` varchar(3000) DEFAULT NULL,
  `item` int NOT NULL,
  `location_list` varchar(2000) DEFAULT NULL,
  `manufacturer` varchar(255) DEFAULT NULL,
  `manufacturer_pn` varchar(255) DEFAULT NULL,
  `part` varchar(255) DEFAULT NULL,
  `pcb_footprint` varchar(255) DEFAULT NULL,
  `reference_list` varchar(2000) DEFAULT NULL,
  `stock_details` varchar(3000) DEFAULT NULL,
  PRIMARY KEY (`component_id`),
  KEY `FK8o9oy97ii60dnb484cnamudif` (`supplier_id`),
  CONSTRAINT `FK8o9oy97ii60dnb484cnamudif` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `component`
--

LOCK TABLES `component` WRITE;
/*!40000 ALTER TABLE `component` DISABLE KEYS */;
INSERT INTO `component` VALUES (1001,'Resistor',50,225,101,'10K Resistor ',100,'ab-130','Stackpole Electronics Inc','RMCF0603FT10K0','10K','R0603','R1, R2, R4, R5, R7','Loose Stock 10000'),(1002,'Capacitor',10,125,102,'0.1 µF 50V Ceramic Capacitor X7R 0805',9,'ab-170, S.R. 151 - S.R. 156','KEMET','C0805C104K5RACAUTO','0.1uF/50V','C0805','C38,C39,C45,C46,C51,C52,C8,C9,C16,C25,C78,C83,C84,C85,C2,C3,C31,C39','Sealed Stock 4000, 6 packs x 4000'),(1003,'Diode',10,100,103,'19V Clamp 5A TVS Diode Surface Mount SOD-323',40,'ab-13,ab-15,ab-18,S.R.259,S.R.355-S.R.366,S.R.370-S.R.372','ON Semiconductor','SD12CT1G','SD12CT1G','SOD-323','D3,D4,D5,D6','Loose Stock 3000,500,1000,2500 | Sealed Packs 12x3000,3x3000'),(1004,'IC',5,5,104,'Linear Voltage Regulator IC Positive Fixed 1 Output 250mA SOT-23-3',126,'S.R. 265','Microchip Technology','MCP1700T-2802E/TT','MCP1700T-2802E/TT','SOT23','U5','Quantity 1500'),(1005,'Inductor',5,75,105,'60 Ohms @ 100 MHz Power Line Ferrite Bead 0805',68,'ab-101','Murata Electronics','BLM21PG600SN1D','BLM21PG600SN1D','L0805','L4','Loose Stock 2000,2200'),(1006,'Connectors/Switches',50,875,106,'A200 Switch',0,'N/A','CTS Corporation','PTS645SM43SMTR 92 LFS','PTS645SM43SMTR 92 LFS','N/A','N/A','Quantity 900');
/*!40000 ALTER TABLE `component` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Breath Alcohol Analyser','Breathlyzer','Current Production'),(2,'Sensor module used inside breath alcohol detection device','Alcohol Sensor Module','Planned'),(3,'PCB control board used in electronic measurement devices','Control Board Assembly','Current Production');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `production_plan`
--

DROP TABLE IF EXISTS `production_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `production_plan` (
  `plan_id` int NOT NULL,
  `production_quantity` int NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`plan_id`),
  KEY `FK1iqm77sgn2w7gsgsg25jefy6q` (`product_id`),
  CONSTRAINT `FK1iqm77sgn2w7gsgsg25jefy6q` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `production_plan`
--

LOCK TABLES `production_plan` WRITE;
/*!40000 ALTER TABLE `production_plan` DISABLE KEYS */;
INSERT INTO `production_plan` VALUES (1,100,'Current Production',1),(2,50,'Inventory Verified',2),(3,25,'Inventory Verified',3);
/*!40000 ALTER TABLE `production_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `supplier_id` int NOT NULL,
  `contact_number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `purchase_link` varchar(255) DEFAULT NULL,
  `supplier_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (101,'1234567890','digikey@test.com','https://www.digikey.com','DigiKey'),(102,'1234567890','kemet@test.com','https://www.kemet.com','KEMET'),(103,'1234567890','onsemi@test.com','https://www.onsemi.com','ON Semiconductor'),(104,'1234567890','microchip@test.com','https://www.microchip.com','Microchip Technology'),(105,'1234567890','murata@test.com','https://www.murata.com','Murata Electronics'),(106,'1234567890','cts@test.com','https://www.ctscorp.com','CTS Corporation');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-07-09 11:48:04
